package empire.digiprem.portfolio.sections.project.domain

data class MyProject(
    val title: String,
    val description: String,
    val fullDescription: String? = null,
    val tags: List<String> = emptyList(),
    val isPrivate: Boolean=false,
    val image: String?=null,
    val demoLink: String? = null,
    val githubLink: String? = null,
    val previewLink: String? = null,
)