package empire.digiprem.portfolio.sections.project.presentation

data class MyProject(
    val title: String,
    val description: String,
    val demoLink: String? = null,
    val githubLink: String? = null,
    val previewLink: String? = null,
)