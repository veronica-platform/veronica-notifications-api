package ec.veronica.notifications.service.fallback

import ec.veronica.notifications.dto.EmailNotificationDto
import java.util.Base64
import javax.mail.util.ByteArrayDataSource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailNotificationFallBackService(
    private var javaMailSender: JavaMailSender
) {

    fun sendMessage(request: EmailNotificationDto) {
        val msg = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(msg, true)
        helper.setFrom(request.name, request.name)
        helper.setTo(request.tos.toTypedArray())
        helper.setSubject(request.subject)
        helper.setText(request.content, true)
        request.attachments.forEach { attachment ->
            helper.addAttachment(
                attachment.filename,
                ByteArrayDataSource(Base64.getDecoder().decode(attachment.content), attachment.type)
            )
        }
        javaMailSender.send(msg)
    }

}
