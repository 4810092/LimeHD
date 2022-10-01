package gka.android.limehd.data.local

import androidx.room.*
import gka.android.limehd.data.local.ChannelData.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class ChannelData(
    @ColumnInfo(name = COLUMN_NAME_ID) @PrimaryKey var id: Int,
    @ColumnInfo(name = COLUMN_NAME_EPG_ID) var epgId: Int?,
    @ColumnInfo(name = COLUMN_NAME_NAME_RU) var nameRu: String?,
    @ColumnInfo(name = COLUMN_NAME_NAME_EN) var nameEn: String?,
    @ColumnInfo(name = COLUMN_NAME_VITRINA_EVENTS_URL) var vitrinaEventsUrl: String?,
    @ColumnInfo(name = COLUMN_NAME_IS_FEDERAL) var isFederal: Boolean?,
    @ColumnInfo(name = COLUMN_NAME_ADDRESS) var address: String?,
    @ColumnInfo(name = COLUMN_NAME_CDN) var cdn: String?,
    @ColumnInfo(name = COLUMN_NAME_URL) var url: String?,
    @ColumnInfo(name = COLUMN_NAME_URL_SOUND) var urlSound: String?,
    @ColumnInfo(name = COLUMN_NAME_IMAGE) var image: String?,
    @ColumnInfo(name = COLUMN_NAME_HAS_EPG) var hasEpg: Boolean?,
    @ColumnInfo(name = COLUMN_NAME_REGION_CODE) var regionCode: Int?,
    @ColumnInfo(name = COLUMN_NAME_TZ) var tz: String?,
    @ColumnInfo(name = COLUMN_NAME_IS_FOREIGN) var isForeign: Boolean?,
    @ColumnInfo(name = COLUMN_NAME_NUMBER) var number: Int?,
    @ColumnInfo(name = COLUMN_NAME_DRM_STATUS) var drmStatus: Int?,
    @ColumnInfo(name = COLUMN_NAME_OWNER) var owner: String?,
    @ColumnInfo(name = COLUMN_NAME_FOREIGN_PLAYER_KEY) var foreignPlayerKey: Boolean?,
) {

    companion object {
        const val TABLE_NAME = "channel"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_EPG_ID = " epgId"
        const val COLUMN_NAME_NAME_RU = " nameRu"
        const val COLUMN_NAME_NAME_EN = " nameEn"
        const val COLUMN_NAME_VITRINA_EVENTS_URL = " vitrinaEventsUrl"
        const val COLUMN_NAME_IS_FEDERAL = " isFederal"
        const val COLUMN_NAME_ADDRESS = " address"
        const val COLUMN_NAME_CDN = " cdn"
        const val COLUMN_NAME_URL = " url"
        const val COLUMN_NAME_URL_SOUND = " urlSound"
        const val COLUMN_NAME_IMAGE = " image"
        const val COLUMN_NAME_HAS_EPG = " hasEpg"
        const val COLUMN_NAME_REGION_CODE = " regionCode"
        const val COLUMN_NAME_TZ = " tz"
        const val COLUMN_NAME_IS_FOREIGN = " isForeign"
        const val COLUMN_NAME_NUMBER = " number"
        const val COLUMN_NAME_DRM_STATUS = " drmStatus"
        const val COLUMN_NAME_OWNER = " owner"
        const val COLUMN_NAME_FOREIGN_PLAYER_KEY = " foreignPlayerKey"
    }
}


data class ChannelDataWithCurrentData(
    @Embedded
    val channelData: ChannelData,

    @Relation(
        entity = ChannelCurrentData::class,
        parentColumn = ChannelData.COLUMN_NAME_ID,
        entityColumn = ChannelCurrentData.COLUMN_NAME_CHANNEL_ID
    )
    val channelCurrentData: ChannelCurrentData?
)