package empire.digiprem.portfolio.sections.contact.presentation


sealed interface ContactAction {
    data object OnDismissSuccessDialog : ContactAction
    data object OnSendButonClick : ContactAction
    data object OnCleanForm : ContactAction


}