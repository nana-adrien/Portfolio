package empire.digiprem.portfolio.core.domain.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import empire.digiprem.portfolio.core.domain.Language
import empire.digiprem.portfolio.core.domain.extension.formatArgs
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

object TranslationService {
    private const val LANGUAGE_KEY="LANGUAGE_KEY"
    private val languageRepository: StorageService = StorageService()
    var currentLanguage by mutableStateOf(
        Language.entries
            .firstOrNull {
                it.name.lowercase() ==
                        (languageRepository.getLocalValue(LANGUAGE_KEY)
                            ?: WindowsPlatform.getNavigatorLanguage())
            }
            ?: Language.EN)
    var translationsLoaded by mutableStateOf(false)
    private val translations = mutableMapOf<Language, Map<String, String>>()

    fun setLanguage(language: Language) {
        currentLanguage = language
        languageRepository.saveValue(LANGUAGE_KEY,language.name)
    }


    suspend fun load(language: Language) {

      translations[language] =onLoadTranslationJson(language.name.lowercase()) //loadTranslations(language.name.lowercase())

    }
    suspend fun onLoadTranslationJson(lang: String):Map<String, String>{
        try {
            val client = HttpClient()

            val result = client.get("/lang/lang_${lang}.json")

            if (result.status.isSuccess()) {
                val text = result.bodyAsText()
                return Json.Default.decodeFromString(text)
            }

            return emptyMap()

        }catch (e: Exception) {
            e.printStackTrace()
            return emptyMap()
        }
    }

    fun getString(key: String, arg: List<String> =emptyList()): String {
        val string=translations[currentLanguage]?.get(key)
            ?: key.split('_').joinToString(" ") { it.capitalize() }
        return  string.formatArgs(arg)
    }
}