package its.dart.com.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient {
        return HttpClient {
            install(DefaultRequest) {
                // Set your base URL or default headers if needed
                url("https://example.com/api/")
                header("Content-Type", "application/json")
            }
            install(ContentNegotiation) {
                // Add JSON serialization support
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            // Optionally add logging for debugging
            install(Logging) {
//                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }
}
