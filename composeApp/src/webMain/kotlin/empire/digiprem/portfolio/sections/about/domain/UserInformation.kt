package empire.digiprem.portfolio.sections.about.domain

import empire.digiprem.portfolio.sections.about.presentation.AboutFormReason

data class UserInformation(
    val name: String,
    val email: String?,
    val reason: AboutFormReason,
)
