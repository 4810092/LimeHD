package gka.android.limehd.data.remote

import gka.android.limehd.data.remote.entities.ChannelsResponse
import retrofit2.Response
import retrofit2.http.GET

interface LimeHdApi {
    @GET("playlist/channels.json")
    suspend fun getChannels(): Response<ChannelsResponse>
}