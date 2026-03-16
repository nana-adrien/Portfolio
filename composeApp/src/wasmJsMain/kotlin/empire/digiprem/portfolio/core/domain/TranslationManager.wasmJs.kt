package empire.digiprem.portfolio.core.domain

import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import org.w3c.fetch.Response

private const val LANGUAGE_KEY = "LANGUAGE_KEY"

@OptIn(ExperimentalWasmJsInterop::class)
actual suspend fun loadTranslations(lang: String): Map<String, String> {
    val response = window.fetch("/lang/lang_${lang}.json").await<Response>()
    val text = response.text().await<String>()

    return Json.decodeFromString(text)
}

actual fun getLocaleLanguage(): String {
    return window.localStorage.getItem(LANGUAGE_KEY)
        ?:window.navigator
            .language.substringBefore("-").lowercase()
}

actual fun saveLanguage(languageTag: String) {
    window.localStorage.setItem(LANGUAGE_KEY, languageTag.lowercase())
}