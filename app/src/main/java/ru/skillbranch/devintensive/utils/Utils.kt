package ru.skillbranch.devintensive.utils

object Utils {

    private val chars = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
    )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.trim()?.split(" ")
        val firstName = if (parts?.get(0).isNullOrEmpty()) null else parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String? {
        val (firstName, lastName) = parseFullName(payload)

        var engFirstName = ""
        var engLastName = ""

        firstName?.forEach { char ->
            var c = chars[char.toLowerCase().toString()]

            if (c != null) {
                engFirstName += if (char.isUpperCase()) {
                    c[0].toUpperCase()
                } else {
                    c[0]
                }

                if (c.length > 1) {
                    c.forEachIndexed { index, char ->
                        if (index > 0) {
                            engFirstName += char
                        }
                    }
                }

            }
        }

        lastName?.forEach { char ->
            var c = chars[char.toLowerCase().toString()]

            if (c != null) {
                engLastName += if (char.isUpperCase()) {
                    c[0].toUpperCase()
                } else {
                    c[0]
                }

                if (c.length > 1) {
                    c.forEachIndexed { index, char ->
                        if (index > 0) {
                            engLastName += char
                        }
                    }
                }
            }
        }

        var res = ""

        if (engFirstName.isNullOrEmpty() && firstName != null) {
            engFirstName = firstName
        }
        if (engLastName.isNullOrEmpty() && lastName != null) {
            engLastName = lastName
        }

        return if (!engLastName.isNullOrEmpty()) return engFirstName + divider + engLastName else return engFirstName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var res: String? = null
        val firstNameInital = getFirstCharToUpperCase(firstName)
        val lastNameInital = getFirstCharToUpperCase(lastName)
        if (firstNameInital != null) {
            res = firstNameInital.toString()
        }
        if (lastNameInital != null) {
            if (res != null) {
                res += lastNameInital
            } else {
                res = lastNameInital.toString()
            }
        }
        return res
    }

    private fun getFirstCharToUpperCase(value: String?): Char? {
        var result = value?.trim()
        return if (result.isNullOrEmpty()) {
            null
        } else {
            result[0]?.toUpperCase()
        }
    }
}