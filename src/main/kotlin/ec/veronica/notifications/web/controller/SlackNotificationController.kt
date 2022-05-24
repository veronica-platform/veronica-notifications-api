package ec.veronica.notifications.web.controller

import ec.veronica.notifications.dto.SlackNotificationDto
import ec.veronica.notifications.service.SlackNotificationService
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/notifications/slack")
class SlackNotificationController(
    private val slackNotificationService: SlackNotificationService
) {

    @PostMapping
    fun save(@Valid @RequestBody slackNotificationDto: SlackNotificationDto) =
        ResponseEntity(slackNotificationService.sendSlackMessage(slackNotificationDto), HttpStatus.CREATED)

}
