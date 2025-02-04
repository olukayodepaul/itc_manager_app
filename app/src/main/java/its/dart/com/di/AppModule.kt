package its.dart.com.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import its.dart.com.Application
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.prefence.PreferenceImp
import its.dart.com.data.repository.remote.CustomerByRepImp
import its.dart.com.data.repository.remote.LoginRemoteDataImpl
import its.dart.com.domain.repository.PreferenceInt
import its.dart.com.domain.repository.remote.CustomerByRepInterface
import its.dart.com.domain.repository.remote.LoginRemoteRepositoryDataInterface
import its.dart.com.domain.usecases.CustomerByRepUseCases
import its.dart.com.domain.usecases.LoginUseCases
import its.dart.com.domain.usecases.OrderUseCases
import its.dart.com.domain.usecases.RepUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRemoteRepositoryData(
        httpClient: HttpClient,
    ): LoginRemoteRepositoryDataInterface
    {
        return LoginRemoteDataImpl(httpClient);
    }

    @Provides
    @Singleton
    fun provideCustomerRemoteRepositoryData(
        httpClient: HttpClient,
    ): CustomerByRepInterface
    {
        return CustomerByRepImp(httpClient);
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRemoteRep: LoginRemoteRepositoryDataInterface,
    ) : LoginUseCases {
        return LoginUseCases(loginRemoteRep)
    }

    @Provides
    @Singleton
    fun provideCustomerUseCase(
        rep : CustomerByRepInterface
    ) : CustomerByRepUseCases{
        return CustomerByRepUseCases(rep)
    }

    @Provides
    @Singleton
    fun provideRepUseCase(
        localCache: LocalDatabase
    ): RepUseCases {
        return RepUseCases(localCache)
    }

    @Provides
    @Singleton
    fun provideOrderUseCases(
        localCache: LocalDatabase
    ): OrderUseCases{
        return OrderUseCases(localCache)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): PreferenceInt {
        return PreferenceImp(sharedPreferences)
    }

}