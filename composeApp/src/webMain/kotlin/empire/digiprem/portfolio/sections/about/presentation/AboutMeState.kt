package empire.digiprem.portfolio.sections.about.presentation

import androidx.compose.foundation.text.input.TextFieldState

data class AboutMeState(
    val emailTextFieldState :TextFieldState= TextFieldState(""),
    val isEmailValid : Boolean = false,
    val isNameValid : Boolean = false,
    val nameTextFieldState:TextFieldState =TextFieldState(""),
    val reason :AboutFormReason?= null,
    val canDownload :Boolean =false,
    val isLoading :Boolean =false,
    val showDialog :Boolean =false,
    val showSuccessDialog :Boolean =false,
    val errorMessage :String? = null,
)