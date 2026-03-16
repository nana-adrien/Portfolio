package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject
import org.jetbrains.compose.resources.getString
import portfolionanaadrien.composeapp.generated.resources.Res
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_chirp_desc
import portfolionanaadrien.composeapp.generated.resources.project_multiplatform_chirp_title
import portfolionanaadrien.composeapp.generated.resources.project_web_portfolio_desc
import portfolionanaadrien.composeapp.generated.resources.project_web_portfolio_title


suspend fun webProjectsCategory()=  Category(
    details =  PortfolioTabItem(id = "5", title = "web"),
    groups = listOf(
        MyProject(
            title = getString(Res.string.project_web_portfolio_title),
            description = getString(Res.string.project_web_portfolio_desc),
            image = "https://raw.githubusercontent.com/nana-adrien/Portfolio/master/composeApp/production/capture/portfolio_preview.jpg",
            githubLink = "https://github.com/nana-adrien/Portfolio",
        ),
    )
)