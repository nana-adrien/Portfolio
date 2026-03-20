package empire.digiprem.portfolio.core.data.networking

import empire.digiprem.portfolio.core.domain.util.DataError
import empire.digiprem.portfolio.core.domain.util.Result
import io.ktor.client.statement.*
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext

actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handlerResponse: suspend (HttpResponse) -> Result<T, DataError.Remote>
): Result<T, DataError.Remote> {
    return try {
        val response = execute()
        handlerResponse(response)

    } catch (e: kotlinx.serialization.SerializationException) {
        Result.Failure(DataError.Remote.SERIALIZATION)

    } catch (e: io.ktor.client.plugins.HttpRequestTimeoutException) {
        Result.Failure(DataError.Remote.REQUEST_TIMEOUT)

    } catch (e: Throwable) {
        coroutineContext.ensureActive()

        val message = e.message.orEmpty()

        when {
            // 🌐 Pas d’internet / CORS / fetch error
            message.contains("Failed to fetch", ignoreCase = true) ||
                    message.contains("NetworkError", ignoreCase = true) ||
                    message.contains("Load failed", ignoreCase = true) -> {
                Result.Failure(DataError.Remote.NOT_INTERNET)
            }

            // ⏱ Timeout (parfois message texte)
            message.contains("timeout", ignoreCase = true) -> {
                Result.Failure(DataError.Remote.REQUEST_TIMEOUT)
            }

            else -> {
                println("Unknown error (JS/WASM): $e")
                Result.Failure(DataError.Remote.UNKNOWN)
            }
        }
    }
}