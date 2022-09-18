package gka.android.limehd.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gka.android.limehd.data.local.ChannelCurrentData.Companion.TABLE_NAME


@Entity(tableName = TABLE_NAME)
data class ChannelCurrentData(
    @ColumnInfo(name = COLUMN_NAME_TIME_START) var timeStart: Long?,
    @ColumnInfo(name = COLUMN_NAME_TIME_STOP) var timeStop: Long?,
    @ColumnInfo(name = COLUMN_NAME_TITLE) var title: String?,
    @ColumnInfo(name = COLUMN_NAME_DESCRIPTION) var desc: String?,
    @ColumnInfo(name = COLUMN_NAME_CDN_VIDEO) var cdnVideo: Int?,
    @ColumnInfo(name = COLUMN_NAME_RATING) var rating: Int?,
    @ColumnInfo(name = COLUMN_NAME_CHANNEL_ID) var channelId: Int?,
    @ColumnInfo(name = COLUMN_NAME_ID)
    @PrimaryKey(autoGenerate = true)
    var id: Int=0
) {

    companion object {
        const val TABLE_NAME = "channel_current"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_CHANNEL_ID = "channelId"
        const val COLUMN_NAME_TIME_START = "timeStart"
        const val COLUMN_NAME_TIME_STOP = "timeStop"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_CDN_VIDEO = "cdnVideo"
        const val COLUMN_NAME_RATING = "rating"
    }


}