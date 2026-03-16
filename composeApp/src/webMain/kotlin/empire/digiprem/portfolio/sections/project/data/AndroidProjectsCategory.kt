package empire.digiprem.portfolio.sections.project.data

import androidx.compose.ui.input.key.Key.Companion.R
import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject
import org.jetbrains.compose.resources.getString
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.back_home
import portfolionanaadrien.composeapp.generated.resources.project_android_notifications_desc
import portfolionanaadrien.composeapp.generated.resources.project_android_notifications_title


suspend fun androidProjectsCategory() = Category(
    details = PortfolioTabItem(id = "1",
        title = "Android"
    ),
    groups = listOf(
        MyProject(
            title = getString(Res.string.project_android_notifications_title),
            isPrivate = true,
            description = getString(Res.string.project_android_notifications_desc),
            demoLink = "github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme/blob/main/README.md",
            githubLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme"
        ),
    )
)