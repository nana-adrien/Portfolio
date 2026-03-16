package empire.digiprem.portfolio.sections.experience.domain

abstract class TimelineItem(
    open val title: String,      // Nom de l'institution ou de l'entreprise
    open val location: String?,  // Lieu (nullable pour éducation si pas nécessaire)
    open val startYear: Int,     // Année de début
    open val endYear: Int,       // Année de fin
    open val description: String? // Description ou tâches
)