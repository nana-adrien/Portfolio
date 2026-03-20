package empire.digiprem.portfolio.sections.about.data

import empire.digiprem.portfolio.sections.about.domain.UserInformation

fun UserInformation.toDto() =  UserInformationDto(
        name = name,
        email = email,
        reason=reason.name
    )
