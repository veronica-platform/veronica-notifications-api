package ec.veronica.notifications.repository.http

import ec.veronica.notifications.dto.SlackNotificationDto
import okhttp3.ResponseBody
import org.springframework.stereotype.Repository
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

@Repository
interface HttpClientDefinition {

    @POST(value = "chat.postMessage")
    fun sendSlackMessage(
        @Header("Authorization") slackApiKey: String,
        @Body payload: SlackNotificationDto
    ): Call<ResponseBody>

}
