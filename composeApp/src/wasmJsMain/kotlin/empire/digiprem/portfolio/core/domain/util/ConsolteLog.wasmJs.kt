package empire.digiprem.portfolio.core.domain.util

actual object ConsoleLog {


    @OptIn(ExperimentalWasmJsInterop::class)
    actual fun log(message: Any?) {
        val msg = message?.toString() ?: "null"
        // Utilise la console native du navigateur pour un meilleur filtrage
        if (msg.contains("ERROR", true)) {
            console.error(msg)
        } else if (msg.contains("WARN", true)) {
          console.warn(msg)
        } else {
            console.log(msg)
        }
    }

    @OptIn(ExperimentalWasmJsInterop::class)
    actual fun error(message: Any?) {
        val msg = message?.toString() ?: "null"
        console.error(msg)
    }

    @OptIn(ExperimentalWasmJsInterop::class)
    actual fun warn(message: Any?) {
        val msg = message?.toString() ?: "null"
        console.warn( msg)
    }
        /* actual fun log(message: Any?) {
             getError(message.toString())
         }

         actual fun error(message: Any?) {
             getError(message.toString())
         }

         actual fun warn(message: Any?) {
             getError(message.toString())
         }

         private fun getError(message: String) {
             when {
                 message.contains("ERROR", true) -> println("❌ $message")
                 message.contains("WARN", true) -> println("⚠️ $message")
                 else -> println("ℹ️ $message")
             }
         }*/

}

external object console {
    fun log(message: String)
    fun warn(message: String)
    fun error(message: String)
}