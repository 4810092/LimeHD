package gka.android.limehd.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [ChannelData::class, ChannelCurrentData::class, FavoriteChannelData::class],
    version = 1,
    views = [],
    exportSchema = false
)
abstract class DatabaseRoom : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao

    companion object {

        private const val DB_NAME = "data"

        fun create(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DatabaseRoom::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()

    }
}