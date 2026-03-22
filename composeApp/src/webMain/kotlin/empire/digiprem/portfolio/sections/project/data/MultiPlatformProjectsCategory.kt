package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.core.Category
import empire.digiprem.portfolio.core.design_system.PortfolioTabItem
import empire.digiprem.portfolio.sections.project.domain.MyProject
import portfolionanaadrien.composeapp.generated.resources.Res


suspend fun multiplatformProjectsCategory() = Category(
    details = PortfolioTabItem(id = "6", title = "Multiplatform"),
    groups = listOf(
        MyProject(
            title = "project_multiplatform_nativeios_title",
            image = Res.getUri("drawable/img_1.png"),
            description = "project_multiplatform_nativeios_desc",
            previewLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme/tree/main/capture",
            githubLink = "https://github.com/nana-adrien/Native-Ios-in-Compose-Multiplatforme"
        ),

        MyProject(
            title = "project_multiplatform_chirp_title",
            description = "project_multiplatform_chirp_desc",
            image = Res.getUri("drawable/img.png"),
            githubLink = "https://github.com/nana-adrien/Chirp"
        ),

        MyProject(
            title = "project_multiplatform_immobi_title",
            description = "project_multiplatform_immobi_desc",
            image = "https://cdn.jsdelivr.net/gh/nana-adrien/Immobi-Market@master/captures/immobi_market_preview.png",
            previewLink = "https://github.com/nana-adrien/Immobi-Market/blob/master/captures/immobi_market_preview.png?raw=true",
            githubLink = "https://github.com/nana-adrien/Immobi-Market"
        ),
    )
)