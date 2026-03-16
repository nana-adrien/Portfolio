package empire.digiprem.portfolio.core.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object TranslationManager {


    var currentLanguage by mutableStateOf(
        Language.entries
            .firstOrNull {
                it.name.lowercase() == getLocaleLanguage()
            }
            ?: Language.EN)
    private val translations = mutableMapOf<Language, Map<String, String>>()

    fun setLanguage(language: Language) {
        currentLanguage = language
        saveLanguage(language.name)
    }

    suspend fun load(language: Language) {
        translations[language] = loadTranslations(language.name.lowercase())
    }

    fun getString(key: String): String {
        return translations[currentLanguage]?.get(key) ?: key.split('_').joinToString(" ") { it.capitalize() }
    }
}


expect fun getLocaleLanguage(): String
expect fun saveLanguage(languageTag: String)
expect suspend fun loadTranslations(lang: String): Map<String, String>