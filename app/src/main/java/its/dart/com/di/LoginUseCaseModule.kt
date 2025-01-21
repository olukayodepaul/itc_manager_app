package its.dart.com.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import its.dart.com.domain.repository.remote.LoginRemoteRep
import its.dart.com.domain.use_cases_impl.LoginUseCasesImp
import its.dart.com.domain.use_cases_intr.LoginUserCases
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUserCase(
        loginRemoteRep: LoginRemoteRep
    ): LoginUserCases {
        return LoginUseCasesImp(loginRemoteRep)
    }

}