package its.dart.com.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import its.dart.com.Application
import its.dart.com.data.repository.local.configuration.RoomDatabaseTable
import its.dart.com.data.repository.local.configuration.RoomDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Dao{

    @Provides
    @Singleton
    fun provideDatabase(app: Application): RoomDatabase {
        return Room.databaseBuilder(
            app,
            RoomDatabase::class.java,
            RoomDatabase.DARTSPATIALBUILDER
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(db: RoomDatabase): RoomDatabaseTable {
        return db.doa
    }

}
