package gka.android.limehd.data.remote.entities

import com.google.gson.annotations.SerializedName

data class ChannelEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("epg_id") val epgId: Int?,
    @SerializedName("name_ru") val nameRu: String?,
    @SerializedName("name_en") val nameEn: String?,
    @SerializedName("vitrina_events_url") val vitrinaEventsUrl: String?,
    @SerializedName("is_federal") val isFederal: Boolean?,
    @SerializedName("address") val address: String?,
    @SerializedName("cdn") val cdn: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("url_sound") val urlSound: String?,
    @SerializedName("image") val image: String?,
    @SerializedName("hasEpg") val hasEpg: Boolean?,
    @SerializedName("current") val current: CurrentEntity?,
    @SerializedName("region_code") val regionCode: Int?,
    @SerializedName("tz") val tz: String?,
    @SerializedName("is_foreign") val isForeign: Boolean?,
    @SerializedName("number") val number: Int?,
    @SerializedName("drm_status") val drmStatus: Int?,
    @SerializedName("owner") val owner: String?,
    @SerializedName("foreign_player_key") val foreignPlayerKey: Boolean?,
)