package gka.android.limehd.data.remote.entities

import com.google.gson.annotations.SerializedName

data class CurrentEntity(
    @SerializedName("timestart") val timeStart: Long?,
    @SerializedName("timestop") val timeStop: Long?,
    @SerializedName("title") val title: String?,
    @SerializedName("desc") val desc: String?,
    @SerializedName("cdnvideo") val cdnVideo: Int?,
    @SerializedName("rating") val rating: Int?,
)