package utilities

class RegexPatterns {

    companion object {
        fun getJsonPropertyValue(propertyName: String): Regex {
            return "(?<=\"${propertyName}\":).*?(?=[,}])".toRegex()
        }
    }
}