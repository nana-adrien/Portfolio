package empire.digiprem.portfolio.core.data.networking

import empire.digiprem.portfolio.core.domain.util.ConsoleLog
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

expect fun getHttpClientEngine() : HttpClientEngine
class HttpClientFactory(
) {

    fun create(engine: HttpClientEngine=getHttpClientEngine() ): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L

            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        when {
                            message.contains("ERROR", true) -> ConsoleLog.error(message)
                            message.contains("WARN", true) -> ConsoleLog.warn(message)
                            else -> ConsoleLog.log(message)
                        }
                    }

                }
                level = LogLevel.ALL
            }
            install(WebSockets) {
                pingIntervalMillis = 20_000L
            }


            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }
    }
}