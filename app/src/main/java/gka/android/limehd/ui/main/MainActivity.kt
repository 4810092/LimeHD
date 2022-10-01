package gka.android.limehd.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import gka.android.limehd.R
import gka.android.limehd.appComponent
import gka.android.limehd.data.remote.Resource
import gka.android.limehd.databinding.ActivityMainBinding
import gka.android.limehd.ui.channels.ChannelsFragmentListener
import gka.android.limehd.ui.utils.syncTouchesWith

class MainActivity : AppCompatActivity(R.layout.activity_main), ChannelsFragmentListener {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val viewModel: MainViewModel by viewModels {
        appComponent.viewModelsFactory()
    }

    private var channelsPagerAdapter = ChannelsPagerAdapter(fragmentActivity = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        with(binding) {
            pages.adapter = channelsPagerAdapter

            TabLayoutMediator(tabs, pages) { tab, position ->
                tab.text = resources.getStringArray(R.array.tab_titles)[position]
            }.attach()


            fieldSearch.addTextChangedListener {
                viewModel.setFilter(it?.toString())
            }
            mainRoot.syncTouchesWith(listOf(fieldSearch))
        }
        observeViewModel()
    }


    private fun observeViewModel() {
        with(viewModel) {
            channelsLoadingLiveData.observe(this@MainActivity) {
                when (it) {
                    is Resource.Success -> {

                    }
                    is Resource.Error -> {
                        it.errorMessage
                        Snackbar.make(
                            binding.root,
                            it.errorMessage ?: "Ошибка",
                            Snackbar.LENGTH_INDEFINITE
                        ).setAction(R.string.btn_try_again) {
                            viewModel.loadChannels()
                        }.show()
                    }
                }
            }
        }
    }

    override fun getChannelsFlow(onlyFavorites: Boolean) =
        viewModel.filteredAllChannels(onlyFavorites)

    override fun addFavorite(channelId: Int) = viewModel.addFavorite(channelId)

    override fun deleteFavorite(channelId: Int) = viewModel.deleteFavorite(channelId)
}