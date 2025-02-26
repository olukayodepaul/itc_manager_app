package its.dart.com.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import its.dart.com.Application
import its.dart.com.data.repository.local.OtherCustomerImpl
import its.dart.com.data.repository.local.TasksRepositoryImpl
import its.dart.com.data.repository.local.database.LocalDatabase
import its.dart.com.data.repository.prefence.PreferenceImp
import its.dart.com.data.repository.remote.AddCustomerImpl
import its.dart.com.data.repository.remote.CustomerByRepImp
import its.dart.com.data.repository.remote.DailyActivityImpl
import its.dart.com.data.repository.remote.LoginRemoteDataImpl
import its.dart.com.data.repository.remote.OrderImpl
import its.dart.com.data.repository.remote.PackPlacementImp
import its.dart.com.data.repository.remote.SurveyImpl
import its.dart.com.data.repository.remote.TaskRemoteImpl
import its.dart.com.domain.preference.PreferenceInt
import its.dart.com.domain.repository.remote.PackPlacement
import its.dart.com.domain.repository.local.OtherCustomer
import its.dart.com.domain.repository.remote.TaskRemote
import its.dart.com.domain.repository.local.TasksRepository
import its.dart.com.domain.repository.remote.AddCustomer
import its.dart.com.domain.repository.remote.CustomerByRepInterface
import its.dart.com.domain.repository.remote.DailyActivityInt
import its.dart.com.domain.repository.remote.LoginRemoteRepositoryDataInterface
import its.dart.com.domain.repository.remote.OrderInt
import its.dart.com.domain.repository.remote.SurveyInt
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
        httpClient: HttpClient
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

    @Provides
    @Singleton
    fun provideTasks(
        localCache: LocalDatabase,
    ) : TasksRepository {
        return TasksRepositoryImpl(localCache)
    }

    @Provides
    @Singleton
    fun provideRemote(
        httpClient: HttpClient,
    ): TaskRemote {
        return TaskRemoteImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideAddCustomer(
        httpClient: HttpClient
    ) : AddCustomer{
        return AddCustomerImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideOtherCustomer(
        localCache: LocalDatabase,
    ) : OtherCustomer {
        return OtherCustomerImpl(localCache)
    }

    @Provides
    @Singleton
    fun provideSurvey(
        httpClient: HttpClient
    ) : SurveyInt {
        return SurveyImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideOrder(
        httpClient: HttpClient
    ) : OrderInt {
        return OrderImpl(httpClient)
    }

    @Provides
    @Singleton
    fun providePackPlacement(
        httpClient: HttpClient
    ): PackPlacement {
        return PackPlacementImp(httpClient)
    }

    @Provides
    @Singleton
    fun provideDailyActivity(
        httpClient: HttpClient
    ): DailyActivityInt
    {
        return DailyActivityImpl(httpClient)
    }
}