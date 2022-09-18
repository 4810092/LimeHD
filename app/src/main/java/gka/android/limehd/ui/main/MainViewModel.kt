package gka.android.limehd.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gka.android.limehd.data.DataRepository
import gka.android.limehd.data.models.ChannelModel
import gka.android.limehd.data.remote.Resource
import gka.android.limehd.data.remote.entities.ChannelsResponse
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class MainViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    val channelsLoadingLiveData = MutableLiveData<Resource<ChannelsResponse>>()

    init {
        loadChannels()
    }

    fun loadChannels() {
        viewModelScope.launch {
            try {
                dataRepository.loadChannels()
                    .collect {
                        if (it is Resource.Success) {
                            it.data?.channels?.let { channelsList ->
                                dataRepository.clearChannels().collect()
                                dataRepository.saveChannels(channelsList).collect()
                            }
                        }
                        channelsLoadingLiveData.postValue(it)
                    }
            } catch (e: UnknownHostException) {
                channelsLoadingLiveData.postValue(
                    Resource.Error(2, "Нет интернета или сервер не доступен")
                )
            } catch (e: Exception) {
                channelsLoadingLiveData.postValue(Resource.Error(3, "Ошибка"))
            }
        }
    }

    fun setFilter(filter: String?) {
        filterFlow.value = filter
    }

    private val filterFlow: MutableStateFlow<String?> = MutableStateFlow(null)

    fun filteredAllChannels(onlyFavorites: Boolean): Flow<List<ChannelModel>> {
        return dataRepository.getAllChannels()
            .map { if (onlyFavorites) it.filter { it.isFavorite } else it }
            .combine(filterFlow) { channels, filter ->
                return@combine if (filter.isNullOrEmpty()) channels
                else channels.filter { it.name.lowercase().contains(filter.lowercase()) }
            }
    }

    fun addFavorite(channelId: Int) {
        viewModelScope.launch {
            dataRepository.addFavoriteChannel(channelId).collect()
        }
    }

    fun deleteFavorite(channelId: Int) {
        viewModelScope.launch {
            dataRepository.deleteFavoriteChannel(channelId).collect()
        }
    }
}