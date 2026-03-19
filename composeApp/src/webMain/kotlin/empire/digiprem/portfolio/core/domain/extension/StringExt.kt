package empire.digiprem.portfolio.core.domain.extension


fun String.formatArgs(args:List<String> =emptyList()): String {
    var result = this
    args.forEachIndexed { index, arg ->
        result = result.replace("%${index+1}\$s", arg) }
    return result
}