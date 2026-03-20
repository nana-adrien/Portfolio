package empire.digiprem.portfolio.sections.contact.presentation

import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import empire.digiprem.portfolio.core.domain.util.onFailure
import empire.digiprem.portfolio.core.domain.util.onSuccess
import empire.digiprem.portfolio.core.domain.validator.EmailValidator
import empire.digiprem.portfolio.core.presentation.AbstractMVIPatternViewModel
import empire.digiprem.portfolio.sections.contact.data.SendMessageServiceImpl
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ContactViewModel : AbstractMVIPatternViewModel<ContactState, ContactAction>(
    ContactState()
) {

    private val sendMessageService = SendMessageServiceImpl()
    private var isNameValidFlow = snapshotFlow {
        state.value.nameTextFieldState.text.toString()
    }.map { name ->
        name.isNotBlank() && (name.length in 3..<20)
    }.distinctUntilChanged()
    private var isMessageValidFlow = snapshotFlow {
        state.value.messageTextFieldState.text.toString()
    }.map { name ->
        name.isNotBlank() && (name.length in 100..<1000)
    }.distinctUntilChanged()
    private var isEmailValidFlow = snapshotFlow {
        state.value.emailTextFieldState.text.toString()
    }.map { email ->
        EmailValidator.validate(email)
    }.distinctUntilChanged()


    override fun onStart() {
        observeTextState()
    }

    private fun observeTextState() {
        combine(
            isNameValidFlow,
            isEmailValidFlow,
            isMessageValidFlow
        ) { isNameValid, isEmailValid, isMessageValid ->
            updateState.update {
                it.copy(
                    errorMessage = null,
                    isNameValid = isNameValid,
                    isEmailValid = isEmailValid,
                    isMessageValid = isMessageValid,
                    canSend = isNameValid && isMessageValid && isEmailValid //
                )
            }
        }.launchIn(viewModelScope)

    }

    fun cleanForm() {
        state.value.emailTextFieldState.clearText()
        state.value.nameTextFieldState.clearText()
        state.value.messageTextFieldState.clearText()
    }

    override fun onAction(action: ContactAction) {
        when (action) {
            ContactAction.OnSendButonClick -> {
                onSendForm()
            }

            ContactAction.OnDismissSuccessDialog -> {
                updateState.update {
                    it.copy(
                        showSuccessDialog = !it.showSuccessDialog,
                    )
                }
            }

            ContactAction.OnCleanForm -> cleanForm()
        }
    }


    fun onSendForm() {
        updateState.update {
            it.copy(
                isLoading = true,
                errorMessage = null
            )
        }
        viewModelScope.launch {
            sendMessageService.sendMessage(
                name = state.value.nameTextFieldState.text.toString(),
                email = state.value.emailTextFieldState.text.toString(),
                message = state.value.messageTextFieldState.text.toString(),
                )
                .onSuccess {
                    updateState.update {
                        it.copy(
                            isLoading = false,
                            showSuccessDialog = true,
                        )
                    }
                    cleanForm()
                }
                .onFailure { error ->
                    updateState.update {
                        it.copy(
                            isLoading = false,
                            showSuccessDialog = false,
                            errorMessage = error.name
                        )
                    }
                }


        }
    }


}