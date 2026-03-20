package empire.digiprem.portfolio.sections.about.data

import empire.digiprem.portfolio.core.data.networking.HttpClientFactory
import empire.digiprem.portfolio.core.data.networking.post
import empire.digiprem.portfolio.core.domain.util.DataError
import empire.digiprem.portfolio.core.domain.util.Result
import empire.digiprem.portfolio.core.domain.util.WindowsPlatform
import empire.digiprem.portfolio.core.domain.util.onSuccess
import empire.digiprem.portfolio.sections.about.data.dto.SendUserInformationRequest
import empire.digiprem.portfolio.sections.about.domain.DownloadCVRepository
import io.ktor.client.*

class DownloadCVRepositoryImpl(private val client: HttpClient = HttpClientFactory().create()): DownloadCVRepository {
    override suspend fun sendUserInformation(userInformationDto: UserInformationDto): Result<String, DataError.Remote> {
        return client.post<SendUserInformationRequest, String>(
            baseUrl = null,
            route = "https://formspree.io/f/xjgaodye",
            body = SendUserInformationRequest(
                name =userInformationDto.name ,
                email=userInformationDto.email,
                reason=userInformationDto.reason,
            ),
        ).onSuccess {
            WindowsPlatform.downloadField("nana-adrien", "drawable/c1.pdf")
        }
    }
}