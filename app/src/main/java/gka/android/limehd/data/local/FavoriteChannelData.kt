package gka.android.limehd.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gka.android.limehd.data.local.FavoriteChannelData.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class FavoriteChannelData(
    @PrimaryKey @ColumnInfo(name = COLUMN_NAME_CHANNEL_ID) var channelId: Int,
) {

    companion object {
        const val TABLE_NAME = "favorite_channel"
        const val COLUMN_NAME_CHANNEL_ID = "channelId"
    }
}