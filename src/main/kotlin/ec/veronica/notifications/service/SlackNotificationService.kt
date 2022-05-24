package ec.veronica.notifications.service

import ec.veronica.notifications.dto.SlackNotificationDto
import ec.veronica.notifications.repository.http.HttpClientDefinition
import ec.veronica.notifications.exceptions.DomainException
import okhttp3.ResponseBody
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import retrofit2.Response
import retrofit2.Retrofit

@Service
class SlackNotificationService(
    @Qualifier("slackRetrofit")
    private var slackRetrofit: Retrofit,
    @Value("\${slack.webhook.api-key}")
    private val slackWebhookApiKey: String
) {

    fun sendSlackMessage(slackNotificationDto: SlackNotificationDto) {
        val response: Response<ResponseBody> = slackRetrofit
            .create(HttpClientDefinition::class.java)
            .sendSlackMessage(slackWebhookApiKey, slackNotificationDto)
            .execute()
        if (!response.isSuccessful) {
            handleError(response)
        }
    }

    private fun handleError(response: Response<*>) {
        throw DomainException(if (response.errorBody() != null) response.errorBody()!!.string() else "Unknown error")
    }

}
