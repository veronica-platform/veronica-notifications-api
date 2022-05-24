package ec.veronica.notifications.dto

import com.fasterxml.jackson.annotation.JsonInclude
import javax.validation.constraints.NotEmpty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class SlackNotificationDto(
    @get:NotEmpty val blocks: List<Block>
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Block(
    @get:NotEmpty val type: String,
    @get:NotEmpty val block_id: String? = null,
    @get:NotEmpty val text: Text,
    @get:NotEmpty val fields: List<Field>? = null,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Text(
    @get:NotEmpty val type: String,
    @get:NotEmpty val text: String,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Field(
    @get:NotEmpty val type: String,
    @get:NotEmpty val text: String,
)
