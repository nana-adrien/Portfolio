package empire.digiprem.portfolio.core.domain.services

object UserSettingService {

    const val THEME_KEY = "THEME_KEY"
    private val userSettingStorage: StorageService = StorageService()

    fun initTheme(isDarkTheme: Boolean) {
        if (userSettingStorage.getLocalValue(THEME_KEY)==null) {
            userSettingStorage.saveValue(THEME_KEY,isDarkTheme.toString())
        }
    }
    fun isDarkTheme(): Boolean{
      return   userSettingStorage.getLocalValue(THEME_KEY).toBoolean()
    }
    fun onThemeChanged(newTheme: Boolean) {
        userSettingStorage.saveValue(THEME_KEY,newTheme.toString())
    }

}