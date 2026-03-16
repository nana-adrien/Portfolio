package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.presentation.MyProject



val multiplatformProjectsCategory =  Category(
    details =  PortfolioTabItem(id = "6", title = "Multiplatform"),
    groups = listOf(
        MyProject(
            title = "Native os in Compose Multiplatforme",
            description = "Demonstrates how to embed a native iOS SwuiftUI Button inside a Compose Multiplatform UI using UIKitViewController.",
            previewLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme/tree/main/capture",
            githubLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme"
        ),
        MyProject(
            title = "Chirp",
            description = "Chirp is a chat application created using Kotlin cross-platform training; it supports several platforms such as (Android and iOS) for mobile and (Windows, Mac OS and Linux) for desktop. ",
            githubLink = "https://github.com/nana-adrien/Chirp"
        ),
    )
)