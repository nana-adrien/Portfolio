package empire.digiprem.portfolio.core.domain.services

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserSettingService {

    const val THEME_KEY = "THEME_KEY"
    private val userSettingStorage: StorageService = StorageService()
    var isDarkTheme by mutableStateOf(userSettingStorage.getLocalValue(THEME_KEY)=="true")
    fun initTheme(isDarkTheme: Boolean) {
        if (userSettingStorage.getLocalValue(THEME_KEY)==null) {
            userSettingStorage.saveValue(THEME_KEY,isDarkTheme.toString())
        }
    }
    /*fun isDarkTheme(): Boolean{
      return  isDarkTheme
    }*/
    fun onThemeChanged(newTheme: Boolean) {
        userSettingStorage.saveValue(THEME_KEY,newTheme.toString().lowercase())
        isDarkTheme=newTheme
    }

}