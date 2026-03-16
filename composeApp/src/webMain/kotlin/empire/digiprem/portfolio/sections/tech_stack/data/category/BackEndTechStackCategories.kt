package empire.digiprem.portfolio.sections.tech_stack.data.category

import androidx.compose.ui.graphics.Color
import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.tech_stack.domain.TechStack

val backEndTechStackCategories = Category(
    details = PortfolioTabItem(
        id = "2",
        title = "Backend",
    ),
    groups = listOf(
        TechStack(
            name = "Kotlin",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg",
            backgroundColor = Color(0xFF7F52FF)
        ),

        TechStack(
            name = "Java",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg",
            backgroundColor = Color(0xFFED8B00)
        ),

        TechStack(
            name = "Spring Boot",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg",
            backgroundColor = Color(0xFF6DB33F)
        ),

        TechStack(
            name = "Ktor",
            iconLink = "https://cdn.simpleicons.org/ktor",
            backgroundColor = Color(0xFF087CFA)
        ),

        TechStack(
            name = "WebSocket",
            iconLink = "https://websocket.org/_astro/websocket-logo.BkDW_HW4_Z1LYP1R.webp",
            backgroundColor = Color(0xFF010101)
        ),

        TechStack(
            name = "PostgreSQL",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg",
            backgroundColor = Color(0xFF336791)
        ),

        TechStack(
            name = "Firebase",
            iconLink = "https://cdn.simpleicons.org/firebase",
            backgroundColor = Color(0xFFFFCA28)
        ),

        TechStack(
            name = "Supabase",
            iconLink = "https://cdn.simpleicons.org/supabase",
            backgroundColor = Color(0xFF3ECF8E)
        ),

        TechStack(
            name = "WAMP",
            iconLink = "https://cdn.simpleicons.org/apache",
            backgroundColor = Color(0xFFD22128)
        )

    )
)
