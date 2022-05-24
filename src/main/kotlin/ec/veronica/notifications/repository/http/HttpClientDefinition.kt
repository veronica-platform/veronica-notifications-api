package ec.veronica.notifications.repository.http

import ec.veronica.notifications.dto.SlackNotificationDto
import okhttp3.ResponseBody
import org.springframework.stereotype.Repository
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

@Repository
interface HttpClientDefinition {

    @POST("{slack-api-key}")
    fun sendSlackMessage(
        @Path("slack-api-key") slackApiKey: String,
        @Body payload: SlackNotificationDto
    ): Call<ResponseBody>

}
