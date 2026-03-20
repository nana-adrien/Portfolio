package empire.digiprem.portfolio.sections.about.presentation

import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import empire.digiprem.portfolio.core.domain.util.onFailure
import empire.digiprem.portfolio.core.domain.util.onSuccess
import empire.digiprem.portfolio.core.domain.validator.EmailValidator
import empire.digiprem.portfolio.core.presentation.AbstractMVIPatternViewModel
import empire.digiprem.portfolio.sections.about.data.DownloadCVRepositoryImpl
import empire.digiprem.portfolio.sections.about.data.toDto
import empire.digiprem.portfolio.sections.about.domain.UserInformation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.random.Random

class AboutMeViewModel : AbstractMVIPatternViewModel<AboutMeState, AboutMeAction>(
    AboutMeState()
) {

    private val repository = DownloadCVRepositoryImpl()
    private var isNameValidFlow = snapshotFlow {
        state.value.nameTextFieldState.text.toString()
    }.map { name ->
        name.isNotBlank() && (name.length in 3..<20)
    }
        .distinctUntilChanged()
    private var isEmailValidFlow = snapshotFlow {
        state.value.emailTextFieldState.text.toString()
    }.map { email ->
        EmailValidator.validate(email)
    }.distinctUntilChanged()
    private var isReasonValidFlow = state.map {
        when (it.reason) {
            null -> false
            else -> true
        }
    }.distinctUntilChanged()

    override fun onStart() {
        observeTextState()
    }

    private fun observeTextState() {
        combine(
            isNameValidFlow,
            isEmailValidFlow,
            state.map { it.reason }.distinctUntilChanged()
        ) { isNameValid, isEmailValid, reason ->
            val isReasonValid = reason != null
            updateState.update {
                it.copy(
                    errorMessage = null,
                    isNameValid = isNameValid,
                    isEmailValid = isEmailValid,
                    canDownload = isNameValid && isReasonValid && (if (reason == AboutFormReason.OTHER) true else isEmailValid) //
                )
            }
        }.launchIn(viewModelScope)

    }

    fun cleanForm() {
        state.value.emailTextFieldState.clearText()
        state.value.nameTextFieldState.clearText()
        updateState.update {
            it.copy(
                reason = null,
                //errorMessage = null,
            )
        }
    }

    override fun onAction(action: AboutMeAction) {
        when (action) {
            AboutMeAction.OnDownloadButonClick -> {
                onDownload()
            }

            is AboutMeAction.OnSelectReason -> {
                updateState.update {
                    it.copy(
                        reason = action.reason,
                    )
                }
            }

            AboutMeAction.OnShowDialog -> {
                updateState.update {
                    it.copy(
                        showDialog = !it.showDialog,
                    )
                }
            }

            AboutMeAction.OnDismissSuccessDialog -> {
                updateState.update {
                    it.copy(
                        showSuccessDialog = !it.showSuccessDialog,
                    )
                }
            }

            AboutMeAction.OnCleanForm -> cleanForm()
        }
    }


    fun onDownload() {
        updateState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
        val isSuccess = Random.nextBoolean()
        viewModelScope.launch {
            repository.sendUserInformation(
                UserInformation(
                    name = state.value.nameTextFieldState.text.toString(),
                    email = state.value.emailTextFieldState.text.toString(),
                    reason = state.value.reason!!
                ).toDto()
            )
                .onSuccess {
                    updateState.update {
                        it.copy(
                            isLoading = false,
                            showDialog = false,
                            showSuccessDialog = true,
                        )
                    }
                }
                .onFailure {error->
                    updateState.update {
                        it.copy(
                            isLoading = false,
                            showDialog = true,
                            showSuccessDialog = false,
                            errorMessage = error.name
                        )
                    }
                }


        }
    }


}