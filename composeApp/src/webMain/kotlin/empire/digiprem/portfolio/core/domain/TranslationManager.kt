package empire.digiprem.portfolio.core.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

object TranslationManager {


    var currentLanguage by mutableStateOf(
        Language.entries
            .firstOrNull {
                it.name.lowercase() == getLocaleLanguage()
            }
            ?: Language.EN)
    var translationsLoaded by mutableStateOf(false)
    private val translations = mutableMapOf<Language, Map<String, String>>()

    fun setLanguage(language: Language) {
        currentLanguage = language
        saveLanguage(language.name)
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
                return Json.decodeFromString(text)
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

fun String.formatArgs(args:List<String> =emptyList()): String {
    var result = this
    args.forEachIndexed { index, arg ->
        result = result.replace("%${index+1}\$s", arg) }
    return result
}
expect fun getLocaleLanguage(): String
expect fun saveLanguage(languageTag: String)
expect suspend fun loadTranslations(lang: String): Map<String, String>