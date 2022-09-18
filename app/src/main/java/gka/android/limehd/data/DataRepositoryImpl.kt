package gka.android.limehd.data

import gka.android.limehd.data.local.DatabaseDao
import gka.android.limehd.data.local.FavoriteChannelData
import gka.android.limehd.data.local.toModel
import gka.android.limehd.data.models.ChannelModel
import gka.android.limehd.data.remote.RemoteDataRepository
import gka.android.limehd.data.remote.Resource
import gka.android.limehd.data.remote.entities.ChannelEntity
import gka.android.limehd.data.remote.entities.ChannelsResponse
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepositoryImpl @Inject constructor(
    private val remoteDataRepository: RemoteDataRepository,
    private val databaseDao: DatabaseDao,
    private val ioDispatcher: CoroutineContext
) : DataRepository {

    override fun loadChannels(): Flow<Resource<ChannelsResponse>> = flow {
        emit(remoteDataRepository.getChannels())
    }.flowOn(ioDispatcher)

    override fun saveChannels(channelsList: List<ChannelEntity>): Flow<Unit> =
        flow { emit(databaseDao.saveChannels(channelsList)) }.flowOn(ioDispatcher)

    override fun clearChannels(): Flow<Unit> =
        flow { emit(databaseDao.clear()) }.flowOn(ioDispatcher)

    override fun getAllChannels(): Flow<List<ChannelModel>> =
        databaseDao.getChannelsData().distinctUntilChanged()
            .map { it.map { it.key.toModel(it.value != null) } }.flowOn(ioDispatcher)

    override fun addFavoriteChannel(channelId: Int): Flow<Unit> =
        flow { emit(databaseDao.insertFavoriteChannel(FavoriteChannelData(channelId))) }.flowOn(
            ioDispatcher
        )

    override fun deleteFavoriteChannel(channelId: Int): Flow<Unit> =
        flow { emit(databaseDao.deleteFavoriteChannel(channelId)) }.flowOn(ioDispatcher)
}
