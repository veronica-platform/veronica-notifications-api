package ec.veronica.notifications.controller

import ec.veronica.notifications.dto.SlackNotificationDto
import ec.veronica.notifications.service.SlackNotificationService
import javax.validation.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("slack")
class SlackNotificationController(
    private val slackNotificationService: SlackNotificationService
) {

    @PostMapping
    fun sendMessage(@Valid @RequestBody slackNotificationDto: SlackNotificationDto) {
        CoroutineScope(Dispatchers.IO).launch {
            slackNotificationService.sendMessage(slackNotificationDto)
        }
    }

}
