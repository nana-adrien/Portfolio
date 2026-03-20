package empire.digiprem.portfolio.core.data.networking

import io.ktor.client.engine.*
import io.ktor.client.engine.js.*

actual fun getHttpClientEngine(): HttpClientEngine {
    return JsClient().create()
}