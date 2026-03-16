package empire.digiprem.portfolio.sections.experience.data.categories

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.experience.domain.ExperienceType
import empire.digiprem.portfolio.sections.experience.domain.ProfessionalExperience
import empire.digiprem.portfolio.sections.experience.domain.TimelineItem

 val  professionalExperienceCategory = Category<TimelineItem>(
    details = PortfolioTabItem(
        id = "2",
        title = "Professional",
    ),
    groups = listOf(
        ProfessionalExperience(
            title = "Digi-Prem Solutions",
            location = "Douala, Cameroon",
            type = ExperienceType.INTERNSHIP,
            position = null,
            startYear = 2022,
            endYear = 2022,
            description = """
            * Assisted in mobile app development with Kotlin & Jetpack Compose
            * Integrated REST APIs
        """.trimIndent()
        ),
        ProfessionalExperience(
            title = "Netfx Solutions",
            location = "Douala, Cameroon",
            type = ExperienceType.JOB,
            position = "Android Developer",
            startYear = 2023,
            endYear = 2025,
            description = """
            * Developed Kotlin Multiplatform apps
            * Integrated Firebase, WebSocket, REST APIs
        """.trimIndent()
        )
    )
)
