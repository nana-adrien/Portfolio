package empire.digiprem.portfolio.sections.experience.data.categories

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.experience.domain.Certification
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem

val certificationCategory = Category<TimelineItem>(
    details = PortfolioTabItem(
        id = "3",
        title = "certifications_title",
    ),
    groups = listOf(
        Certification(
            title = "certification_cmp_mobile_title",
            location = "certification_issuer",
            description = "certification_program_note",
        ),
        Certification(
            title = "certification_cmp_desktop_title",
            location = "certification_issuer",
            description = "certification_program_note",
        ),
        Certification(
            title = "certification_spring_backend_title",
            location = "certification_issuer",
            description = "certification_program_note",
        ),
    )
)
