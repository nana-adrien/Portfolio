package empire.digiprem.portfolio.sections.experience.domain

data class Education(
    override val title: String,
    override val location: String? = null,
    val degree: String,          // Diplôme ou programme
    override val startYear: Int,
    override val endYear: Int,
    override val description: String? = null,
) : TimelineItem(title, location, startYear, endYear, description)