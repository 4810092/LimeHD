package gka.android.limehd.data.remote.entities

import gka.android.limehd.data.local.ChannelCurrentData
import gka.android.limehd.data.local.ChannelData

fun CurrentEntity.toData(channelId: Int) =
    ChannelCurrentData(timeStart, timeStop, title, desc, cdnVideo, rating, channelId)

fun ChannelEntity.toData() = ChannelData(
    id,
    epgId,
    nameRu,
    nameEn,
    vitrinaEventsUrl,
    isFederal,
    address,
    cdn,
    url,
    urlSound,
    image,
    hasEpg,
    regionCode,
    tz,
    isForeign,
    number,
    drmStatus,
    owner,
    foreignPlayerKey
)