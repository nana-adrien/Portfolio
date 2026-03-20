package empire.digiprem.portfolio.sections.contact.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest (
    val name: String,
    val email: String,
    val message: String,
)