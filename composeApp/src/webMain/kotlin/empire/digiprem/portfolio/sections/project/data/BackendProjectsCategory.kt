package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.design_system.PortfolioTabItem
import empire.digiprem.portfolio.design_system.ProjectCategory
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val backendProjectsCategory =ProjectCategory(
    tab = PortfolioTabItem(id = "3", title = "Server"),
    projects = listOf(
        MyProject(
            title = "Backend API",
            description = "Spring Boot, Kotlin, REST API",
            githubLink = "https://github.com/example/server1"
        )
    )
)