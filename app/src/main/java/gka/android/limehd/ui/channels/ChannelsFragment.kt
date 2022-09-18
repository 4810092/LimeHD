package gka.android.limehd.ui.channels

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import gka.android.limehd.R
import gka.android.limehd.appComponent
import gka.android.limehd.databinding.FragmentChannelsBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChannelsFragment(private var onlyFavorites: Boolean) :
    Fragment(R.layout.fragment_channels) {

    private val binding: FragmentChannelsBinding by viewBinding(FragmentChannelsBinding::bind)

    @Inject
    lateinit var channelsAdapter: ChannelsAdapter

    private val mainActivity: ChannelsFragmentListener by lazy {
        requireActivity() as? ChannelsFragmentListener
            ?: throw TypeCastException("ChannelsFragmentListener")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().appComponent.inject(this)

        channelsAdapter.channelItemListener = object : ChannelItemListener {
            override fun onAddChannelFavorite(channelId: Int) {
                mainActivity.addFavorite(channelId)
            }

            override fun onRemoveChannelFavorite(channelId: Int) {
                mainActivity.deleteFavorite(channelId)
            }
        }

        with(binding) {
            textEmpty.setText(if (onlyFavorites) R.string.hint_no_favorite else R.string.hint_no_channel)
            recycler.adapter = channelsAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainActivity.getChannelsFlow(onlyFavorites).collect {
                        channelsAdapter.items = it

                        binding.textEmpty.isVisible = it.isEmpty()
                    }
                }
            }
        }
    }
}