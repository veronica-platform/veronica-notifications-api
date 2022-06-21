package ec.veronica.notifications.service

import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Attachments
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import com.sendgrid.helpers.mail.objects.Personalization
import ec.veronica.notifications.dto.AttachmentDto
import ec.veronica.notifications.dto.EmailNotificationDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class EmailNotificationService(
    @Value("\${sendgrid.api.token}")
    private val sendgridApiToken: String,
    @Value("\${veronica.email.no-reply}")
    private val noReplyEmailAddress: String
) {

    private val logger by lazy { LoggerFactory.getLogger(EmailNotificationService::class.java) }

    fun sendMessage(request: EmailNotificationDto) {
        val mail = Mail()
        mail.from = Email(noReplyEmailAddress, request.supplierName)
        mail.subject = request.subject
        mail.addContent(Content("text/html", request.content))
        mail.addReceivers(request.tos)
        mail.addAttachments(request.attachments)
        mail.send()
    }

    private fun Mail.addReceivers(receivers: List<String>) {
        val personalization = Personalization()
        receivers.forEach { to ->
            personalization.addTo(Email(to))
        }
        this.addPersonalization(personalization)
    }

    private fun Mail.addAttachments(attachmentsRequest: List<AttachmentDto>) {
        attachmentsRequest.forEach { attachmentRequest ->
            val attachment = Attachments()
            attachment.content = attachmentRequest.content
            attachment.contentId = attachmentRequest.contentId
            attachment.filename = attachmentRequest.filename
            attachment.type = attachmentRequest.type
            this.addAttachments(attachment)
        }
    }

    private fun Mail.send() {
        val sg = SendGrid(sendgridApiToken)
        val request = Request()
        request.method = Method.POST
        request.endpoint = SENDGRID_SEND_EMAIL_ENDPOINT
        request.body = this.build()
        val response = sg.api(request)
        if (response.statusCode >= 400) {
            logger.error(response.body)
        }
    }

    companion object {
        const val SENDGRID_SEND_EMAIL_ENDPOINT = "mail/send"
    }

}
