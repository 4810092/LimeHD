package gka.android.limehd.data.local

import gka.android.limehd.data.models.ChannelModel

fun ChannelDataWithCurrentData.toModel(isFavorite: Boolean) =
    ChannelModel(
        channelData.id,
        channelData.nameRu ?: "",
        channelData.image ?: "",
        channelCurrentData?.title ?: "",
        isFavorite
    )