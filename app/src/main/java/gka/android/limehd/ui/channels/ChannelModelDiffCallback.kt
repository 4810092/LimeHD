package gka.android.limehd.ui.channels

import androidx.recyclerview.widget.DiffUtil
import gka.android.limehd.data.models.ChannelModel
import javax.inject.Inject

class ChannelModelDiffCallback @Inject constructor() : DiffUtil.ItemCallback<ChannelModel>() {
    override fun areItemsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChannelModel, newItem: ChannelModel): Boolean {
        return oldItem == newItem
    }
}