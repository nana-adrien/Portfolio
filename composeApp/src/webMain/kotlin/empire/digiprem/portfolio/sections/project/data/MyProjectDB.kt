package empire.digiprem.portfolio.sections.project.data

import empire.digiprem.portfolio.sections.project.data.desktopProjectsCategory

object MyProjectDB {
    suspend fun categories() = listOf(
        androidProjectsCategory(),
        iosProjectsCategory(),
        backendProjectsCategory(),
        desktopProjectsCategory(),
        webProjectsCategory(),
        multiplatformProjectsCategory(),
    )
}