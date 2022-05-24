package ec.veronica.notifications.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class RetrofitConfig(
    @Value("\${slack.webhook.url}")
    private val slackWebhookUrl: String
) {

    @Bean
    @Qualifier("slackRetrofit")
    fun retrofit(): Retrofit {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        return Retrofit.Builder()
            .baseUrl(slackWebhookUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

}
