package empire.digiprem.portfolio.sections.about.domain

import empire.digiprem.portfolio.core.domain.util.DataError
import empire.digiprem.portfolio.core.domain.util.Result
import empire.digiprem.portfolio.sections.about.data.UserInformationDto

interface DownloadCVRepository {
    suspend fun sendUserInformation(userInformationDto: UserInformationDto): Result<String, DataError.Remote>
}