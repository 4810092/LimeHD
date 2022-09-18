package gka.android.limehd.data

import gka.android.limehd.data.models.ChannelModel
import gka.android.limehd.data.remote.Resource
import gka.android.limehd.data.remote.entities.ChannelEntity
import gka.android.limehd.data.remote.entities.ChannelsResponse
import kotlinx.coroutines.flow.Flow

interface DataRepository {

    fun loadChannels(): Flow<Resource<ChannelsResponse>>

    fun saveChannels(channelsList: List<ChannelEntity>): Flow<Unit>
    fun clearChannels(): Flow<Unit>

    fun getAllChannels(): Flow<List<ChannelModel>>

    fun addFavoriteChannel(channelId: Int): Flow<Unit>
    fun deleteFavoriteChannel(channelId: Int): Flow<Unit>

}