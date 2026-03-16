package empire.digiprem.portfolio.sections.tech_stack.data.category

import androidx.compose.ui.graphics.Color
import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.tech_stack.domain.TechStack

val frontEndTechStackCategories = Category(
    details = PortfolioTabItem(
        id = "1",
        title = "Frontend",
    ),
    groups = listOf(

        TechStack(
            name = "Android (Native)",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/android/android-original.svg",
            backgroundColor = Color(0xFF3DDC84)
        ),

        TechStack(
            name = "Jetpack Compose",
            iconLink = "https://cdn.simpleicons.org/jetpackcompose",
            backgroundColor = Color(0xFF4285F4)
        ),

        TechStack(
            name = "Kotlin Multiplatform",
            iconLink = "https://cdn.simpleicons.org/kotlin",
            backgroundColor = Color(0xFF7F52FF)
        ),

        TechStack(
            name = "SwiftUI",
            iconLink = "https://cdn.simpleicons.org/swift",
            backgroundColor = Color(0xFFFF6B35)
        ),

        TechStack(
            name = "HTML",
            iconLink = "https://cdn.simpleicons.org/html5",
            backgroundColor = Color(0xFFE34F26)
        ),

        TechStack(
            name = "CSS",
            iconLink = "https://cdn.simpleicons.org/css",
            backgroundColor = Color(0xFF1572B6)
        )

    )
)
