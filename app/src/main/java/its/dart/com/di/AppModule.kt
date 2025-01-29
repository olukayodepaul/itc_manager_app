package its.dart.com.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import its.dart.com.data.repository.remote.LoginRemoteDataImpl
import its.dart.com.domain.repository.remote.LoginRemoteRepositoryDataInterface
import its.dart.com.domain.usecases.LoginUseCases
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

    //provide use case. use cases is depending on the local repository data
    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRemoteRep: LoginRemoteRepositoryDataInterface,
    ) : LoginUseCases {
        return LoginUseCases(loginRemoteRep)
    }

}