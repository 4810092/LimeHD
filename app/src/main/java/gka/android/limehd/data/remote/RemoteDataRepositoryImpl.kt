package gka.android.limehd.data.remote

import gka.android.limehd.data.remote.entities.ChannelsResponse
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(private val api: LimeHdApi) :
    RemoteDataRepository {
    override suspend fun getChannels(): Resource<ChannelsResponse> {
        return checkResponse(api.getChannels())
    }
}