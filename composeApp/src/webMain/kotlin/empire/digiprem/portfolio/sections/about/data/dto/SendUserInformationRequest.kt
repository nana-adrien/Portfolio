package empire.digiprem.portfolio.sections.about.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendUserInformationRequest (
    val name: String,
    val email: String?,
    val reason: String,
)