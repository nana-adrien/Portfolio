package empire.digiprem.portfolio.sections.experience.domain

enum class ExperienceType { INTERNSHIP, JOB }

data class ProfessionalExperience(
    override val title: String,      // Nom de l'entreprise
    override val location: String,
    val type: ExperienceType,        // Stage ou Travail
    val position: String?,           // Poste occupé
    override val startYear: Int,
    override val endYear: Int,
    override val description: String
) : TimelineItem(title, location, startYear, endYear, description)