package empire.digiprem.portfolio.core.domain.services

expect class  StorageService() {
    fun getLocalValue(key: String): String?
     fun saveValue(key: String, value: String)
}