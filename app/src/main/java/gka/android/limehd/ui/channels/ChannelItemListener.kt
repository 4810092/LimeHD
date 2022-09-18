package gka.android.limehd.ui.channels

interface ChannelItemListener {
    fun onAddChannelFavorite(channelId: Int)

    fun onRemoveChannelFavorite(channelId: Int)
}