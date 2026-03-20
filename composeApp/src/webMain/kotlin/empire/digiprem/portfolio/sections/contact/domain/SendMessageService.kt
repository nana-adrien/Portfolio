package empire.digiprem.portfolio.sections.contact.domain

import empire.digiprem.portfolio.core.domain.util.DataError
import empire.digiprem.portfolio.core.domain.util.Result

interface SendMessageService {
    suspend fun sendMessage(
        name: String,
        email: String,
        message: String,
    ): Result<String, DataError.Remote>
}