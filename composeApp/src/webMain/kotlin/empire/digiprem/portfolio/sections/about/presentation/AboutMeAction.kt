package empire.digiprem.portfolio.sections.about.presentation


sealed interface AboutMeAction {
    data object OnShowDialog : AboutMeAction
    data object OnDismissSuccessDialog : AboutMeAction
    data object OnDownloadButonClick : AboutMeAction
    data object OnCleanForm : AboutMeAction

    data class OnSelectReason(val reason: AboutFormReason) : AboutMeAction

}