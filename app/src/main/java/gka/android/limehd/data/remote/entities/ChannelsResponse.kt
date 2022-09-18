package gka.android.limehd.data.remote.entities

import com.google.gson.annotations.SerializedName

data class ChannelsResponse(
    @SerializedName("channels") val channels: List<ChannelEntity>?,
    @SerializedName("valid") val valid: Long?,
    @SerializedName("ckey") val cKey: String?
)