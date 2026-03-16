package empire.digiprem.portfolio.sections.tech_stack.data.category

import androidx.compose.ui.graphics.Color
import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.tech_stack.domain.TechStack

val otherTechStackCategories = Category(
    details = PortfolioTabItem(
        id = "3",
        title = "tech_stack_tab_other",
    ),
    groups = listOf(

        TechStack(
            name = "Android Studio",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/androidstudio/androidstudio-original.svg",
            backgroundColor = Color(0xFF3DDC84)
        ),

        TechStack(
            name = "IntelliJ IDEA",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg",
            backgroundColor = Color(0xFF000000)
        ),
        TechStack(
            name = "Postman",
            iconLink = "https://cdn.simpleicons.org/postman",
            backgroundColor = Color(0xFFFF6C37)
        ),
        TechStack(
            name = "Xcode",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/xcode/xcode-original.svg",
            backgroundColor = Color(0xFF147EFB)
        ),

        TechStack(
            name = "Git",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg",
            backgroundColor = Color(0xFFF05032)
        ),

        TechStack(
            name = "GitHub",
            iconLink = "https://cdn.simpleicons.org/github",
            backgroundColor = Color(0xFF181717)
        ),

        TechStack(
            name = "GitLab",
            iconLink = "https://cdn.simpleicons.org/gitlab",
            backgroundColor = Color(0xFFFCA121)
        ),

        TechStack(
            name = "UML",
            iconLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYAm56KxOvcav1Ze35W9hWx2gRFlWLYbCwpA&s",
            backgroundColor = Color(0xFF007ACC)
        ),
        TechStack(
            name = "Jira",
            iconLink = "https://cdn.simpleicons.org/jira",
            backgroundColor = Color(0xFF0052CC)
        ),

        TechStack(
            name = "Formspree",
            iconLink = "https://cdn.simpleicons.org/formspree",
            backgroundColor = Color(0xFFEC1C24)
        ),

        TechStack(
            name = "Google Cloud",
            iconLink = "https://cdn.simpleicons.org/googlecloud",
            backgroundColor = Color(0xFF4285F4)
        ),

        TechStack(
            name = "Azure DevOps",
            iconLink = "https://cdn.jsdelivr.net/gh/devicons/devicon/icons/azuredevops/azuredevops-original.svg",
            backgroundColor = Color(0xFF0078D7)
        ),

        TechStack(
            name = "Mapbox",
            iconLink = "https://cdn.simpleicons.org/mapbox",
            backgroundColor = Color(0xFF000000)
        ),

        TechStack(
            name = "Microsoft Teams",
            iconLink = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/94/Microsoft_Office_Teams_%282019–2025%29.svg/960px-Microsoft_Office_Teams_%282019–2025%29.svg.png",
            backgroundColor = Color(0xFF6264A7)
        ),


        TechStack(
            name = "Apple iCloud",
            iconLink = "https://cdn.simpleicons.org/icloud",
            backgroundColor = Color(0xFF0A84FF)
        ),
        TechStack(
            name = "Google APIs",
            iconLink = "https://cdn.simpleicons.org/google",
            backgroundColor = Color(0xFF4285F4)
        ),
        TechStack(
            name = "Vercel",
            iconLink = "https://cdn.simpleicons.org/vercel",
            backgroundColor = Color(0xFF000000)
        )
    )
)
