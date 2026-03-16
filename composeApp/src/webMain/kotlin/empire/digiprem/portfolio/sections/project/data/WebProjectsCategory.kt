package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val webProjectsCategory =  Category(
    details =  PortfolioTabItem(id = "5", title = "web"),
    groups = listOf(
        MyProject(
            title = "NotyStack Android",
            description = "Tech Stack: Android, Java, XML, Firebase, MVVM, RoomDB, SQLite",
            demoLink = "github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme/blob/main/README.md",
            githubLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme"
        ),
        MyProject(
            title = "Another Android Project",
            description = "Kotlin, Compose, RoomDB",
            demoLink = "",
            githubLink = "https://github.com/example/android2"
        )
    )
)