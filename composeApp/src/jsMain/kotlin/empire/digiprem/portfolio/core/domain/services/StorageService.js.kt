package empire.digiprem.portfolio.core.domain.services

import kotlinx.browser.window

actual class StorageService {
    actual fun getLocalValue(key: String): String? {
        return window.localStorage.getItem(key)
    }

    actual fun saveValue(key: String, value: String) {
        window.localStorage.setItem(key, value)
    }

}