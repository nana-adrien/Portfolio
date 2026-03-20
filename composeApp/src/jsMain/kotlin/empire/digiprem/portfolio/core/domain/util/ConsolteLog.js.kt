package empire.digiprem.portfolio.core.domain.util

actual object ConsoleLog {
    actual fun log(message: Any?) {
        console.log (message)
    }

    actual fun error(message: Any?) {
        console.error(message)
    }

    actual fun warn(message: Any?) {
        console.warn(message)
    }
}