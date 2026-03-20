package empire.digiprem.portfolio.sections.contact.data

import empire.digiprem.portfolio.core.data.networking.HttpClientFactory
import empire.digiprem.portfolio.core.data.networking.post
import empire.digiprem.portfolio.core.domain.util.DataError
import empire.digiprem.portfolio.core.domain.util.Result
import empire.digiprem.portfolio.sections.contact.data.dto.SendMessageRequest
import empire.digiprem.portfolio.sections.contact.domain.SendMessageService
import io.ktor.client.*

class SendMessageServiceImpl(private val client: HttpClient = HttpClientFactory().create()): SendMessageService {
    override suspend fun sendMessage(
        name: String,
        email: String,
        message: String
    ): Result<String, DataError.Remote> {
        return client.post<SendMessageRequest, String>(
            baseUrl = null,
            route = "https://formspree.io/f/xgonqodb",
            body = SendMessageRequest(
                name = name,
                email = email,
                message = message,
            ),
        )
    }
}