package empire.digiprem.portfolio.sections.contact.presentation

import androidx.compose.foundation.text.input.TextFieldState

data class ContactState(
    val emailTextFieldState :TextFieldState= TextFieldState(""),
    val nameTextFieldState:TextFieldState =TextFieldState(""),
    val messageTextFieldState:TextFieldState =TextFieldState(""),
    val isEmailValid : Boolean = false,
    val isNameValid : Boolean = false,
    val isMessageValid : Boolean = false,
    val canSend :Boolean =false,
    val isLoading :Boolean =false,
    val showSuccessDialog :Boolean =false,
    val errorMessage :String? = null,
)