package gka.android.limehd.ui.channels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.squareup.picasso.Picasso
import gka.android.limehd.R
import gka.android.limehd.data.models.ChannelModel
import gka.android.limehd.databinding.ItemChannelBinding
import javax.inject.Inject

class ChannelsAdapter @Inject constructor(
    channelModelDiffCallback: ChannelModelDiffCallback,
    private val picasso: Picasso
) :
    RecyclerView.Adapter<ChannelsAdapter.ChannelsVH>() {

    private val asyncDiffer = AsyncListDiffer(this, channelModelDiffCallback)

    var items: List<ChannelModel>
        get() = asyncDiffer.currentList
        set(value) = asyncDiffer.submitList(value)

    var channelItemListener: ChannelItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChannelsVH(
        LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false),
        channelItemListener = channelItemListener
    )

    override fun onBindViewHolder(holder: ChannelsVH, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int = items.count()

    inner class ChannelsVH(itemView: View, var channelItemListener: ChannelItemListener?) :
        RecyclerView.ViewHolder(itemView) {
        private val binding: ItemChannelBinding by viewBinding(ItemChannelBinding::bind)

        fun onBind(channelModel: ChannelModel) {
            with(binding) {
                title.text = channelModel.name
                subTitle.text = channelModel.title

                picasso.load(channelModel.image).placeholder(R.drawable.ic_launcher_foreground)
                    .into(icon)

                favorite.isSelected = channelModel.isFavorite
                favorite.setOnClickListener {
                    if (favorite.isSelected)
                        channelItemListener?.onRemoveChannelFavorite(channelModel.id)
                    else
                        channelItemListener?.onAddChannelFavorite(channelModel.id)
                }
            }
        }
    }
}