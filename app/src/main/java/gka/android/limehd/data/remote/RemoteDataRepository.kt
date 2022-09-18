package gka.android.limehd.data.remote

import gka.android.limehd.data.remote.entities.ChannelsResponse

interface RemoteDataRepository {
    suspend fun getChannels(): Resource<ChannelsResponse>
}