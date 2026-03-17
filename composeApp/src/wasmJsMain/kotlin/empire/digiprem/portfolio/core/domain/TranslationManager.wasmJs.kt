package empire.digiprem.portfolio.core.domain

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.w3c.fetch.Response

private const val LANGUAGE_KEY = "LANGUAGE_KEY"

@OptIn(ExperimentalWasmJsInterop::class)
actual suspend fun loadTranslations(lang: String): Map<String, String> {

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

actual fun getLocaleLanguage(): String {
    return window.localStorage.getItem(LANGUAGE_KEY)
        ?:window.navigator
            .language.substringBefore("-").lowercase()
}

actual fun saveLanguage(languageTag: String) {
    window.localStorage.setItem(LANGUAGE_KEY, languageTag.lowercase())
}