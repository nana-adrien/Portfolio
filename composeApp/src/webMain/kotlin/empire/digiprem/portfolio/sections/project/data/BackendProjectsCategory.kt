package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val backendProjectsCategory = Category(
    details = PortfolioTabItem(id = "3", title = "Backend"),
    groups = listOf(
        MyProject(
            title = "chirp-api",
            description = "This project stems from the Kotlin multiplatform training course - it focuses solely on creating server applications using Spring Boot and KMP. ",
            githubLink = "https://github.com/nana-adrien/chirp-api"
        ),
        MyProject(
            title = "Immobi-Market-api",
            description = "Project for the design and implementation of a cross-platform property management application based on Spring Boot.",
            githubLink = "https://github.com/nana-adrien/Immobi-Market"
        ),
    )
)