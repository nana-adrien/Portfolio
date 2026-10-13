package empire.digiprem.portfolio.sections.experience.domain

data class Certification(
    override val title: String,          // Nom du certificat
    override val location: String? = null, // Organisme émetteur
    override val startYear: Int? = null,
    override val endYear: Int? = null,
    override val description: String? = null,
    val certificateLink: String? = null,
) : TimelineItem(title, location, startYear, endYear, description)
