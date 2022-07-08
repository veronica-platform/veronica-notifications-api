package ec.veronica.notifications.controller

import ec.veronica.notifications.dto.EmailNotificationDto
import ec.veronica.notifications.service.EmailNotificationService
import javax.validation.Valid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("email")
class EmailNotificationController(
    private val emailNotificationService: EmailNotificationService
) {

    @PostMapping
    fun sendMessage(@Valid @RequestBody emailNotificationDto: EmailNotificationDto) {
        CoroutineScope(Dispatchers.IO).launch {
            emailNotificationService.sendMessage(emailNotificationDto)
        }
    }

}
