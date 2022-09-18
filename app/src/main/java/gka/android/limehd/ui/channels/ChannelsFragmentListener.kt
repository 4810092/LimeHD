package gka.android.limehd.ui.channels

import gka.android.limehd.data.models.ChannelModel
import kotlinx.coroutines.flow.Flow

interface ChannelsFragmentListener {
    fun getChannelsFlow(onlyFavorites: Boolean): Flow<List<ChannelModel>>

    fun addFavorite(channelId: Int)

    fun deleteFavorite(channelId: Int)
}