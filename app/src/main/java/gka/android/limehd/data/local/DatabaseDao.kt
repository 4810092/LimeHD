package gka.android.limehd.data.local

import androidx.room.*
import gka.android.limehd.data.remote.entities.ChannelEntity
import gka.android.limehd.data.remote.entities.toData
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertChannel(channelData: ChannelData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertChannelCurrent(channelCurrentData: ChannelCurrentData)

    @Transaction
    open suspend fun saveChannels(channelEntities: List<ChannelEntity>?) {
        channelEntities?.forEach { channel ->
            insertChannel(channel.toData())
            channel.current?.let { current ->
                insertChannelCurrent(current.toData(channel.id))
            }
        }
    }

    @Transaction
    @Query("SELECT * FROM ${ChannelData.TABLE_NAME} LEFT JOIN ${FavoriteChannelData.TABLE_NAME} ON ${FavoriteChannelData.TABLE_NAME}.${FavoriteChannelData.COLUMN_NAME_CHANNEL_ID} = ${ChannelData.TABLE_NAME}.${ChannelData.COLUMN_NAME_ID}")
    abstract fun getChannelsData(): Flow<Map<ChannelDataWithCurrentData, FavoriteChannelData?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertFavoriteChannel(favoriteChannelData: FavoriteChannelData)

    @Transaction
    @Query("DELETE FROM ${FavoriteChannelData.TABLE_NAME} WHERE ${FavoriteChannelData.COLUMN_NAME_CHANNEL_ID} = :channelId")
    abstract suspend fun deleteFavoriteChannel(channelId: Int)

    @Query("DELETE FROM ${ChannelData.TABLE_NAME}")
    abstract suspend fun deleteChannelData()

    @Query("DELETE FROM ${ChannelCurrentData.TABLE_NAME}")
    abstract suspend fun deleteChannelCurrentData()

    @Transaction
    open suspend fun clear() {
        deleteChannelData()
        deleteChannelCurrentData()
    }
}