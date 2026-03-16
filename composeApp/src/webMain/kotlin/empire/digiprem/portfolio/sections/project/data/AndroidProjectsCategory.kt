package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val androidProjectsCategory = Category(
    details = PortfolioTabItem(id = "1", title = "Android"),
    groups = listOf(
        MyProject(
            title = "Android-Notifications",
            isPrivate = true,
            description = "Tech Stack: Android, Java, XML, Firebase, MVVM, RoomDB, SQLite",
            demoLink = "github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme/blob/main/README.md",
            githubLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme"
        ),
    )
)