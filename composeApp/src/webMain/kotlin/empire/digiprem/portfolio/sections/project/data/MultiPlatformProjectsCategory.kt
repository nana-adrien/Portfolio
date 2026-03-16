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
            description = "Chirp is a chat application developed as part of a Kotlin Multiplatform project. It allows users to communicate seamlessly across multiple platforms, including Android and iOS for mobile devices, and Windows, macOS, and Linux for desktop.",
            githubLink = "https://github.com/nana-adrien/Chirp"
        ),
        MyProject(
            title = "Immobi-Market",
            description = "A project focused on designing and building a platform that connects property owners with people looking for housing. The goal is to simplify property listing, discovery, and communication between owners and potential tenants or buyers.",
            githubLink = "https://github.com/nana-adrien/Immobi-Market"
        ),
    )
)