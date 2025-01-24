package its.dart.com.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import its.dart.com.data.repository.remote.LoginRemoteRepImpl
import its.dart.com.domain.repository.remote.LoginRemoteRep
import its.dart.com.domain.usecases.LoginUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoginRepositoryModule(
        httpClient: HttpClient
    ): LoginRemoteRep
    {
        return LoginRemoteRepImpl(httpClient);
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        loginRemoteRep: LoginRemoteRep
    ) : LoginUseCases {
        return LoginUseCases(loginRemoteRep)
    }

}