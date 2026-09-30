package empire.digiprem.portfolio.sections.experience.data.categories

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.experience.domain.Education
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem

val educationExperienceCategory = Category<TimelineItem>(
    details = PortfolioTabItem(
        id = "1",
        title = "education_title",
    ),
    groups = listOf(
        Education(
            title = "education_master_title",
            degree = "education_master_degree",
        ),
        Education(
            title = "education_licence_title",
            degree = "education_licence_degree",
        ),
        Education(
            title = "education_bts_title",
            degree = "education_bts_degree",
        )
    )
)