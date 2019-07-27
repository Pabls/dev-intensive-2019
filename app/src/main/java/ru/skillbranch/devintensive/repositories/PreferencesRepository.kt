package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val DEFAULT_STRING_VALUE = ""
    private const val DEFAULT_NUMBER_VALUE = 0
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"
    private const val APP_THEME = "APP_THEME"

    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun saveAppTheme(theme: Int) {
        putValue(APP_THEME to theme)
    }

    fun getAppTheme(): Int = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)

    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(REPOSITORY to repository)
            putValue(RATING to rating)
            putValue(RESPECT to respect)
        }
    }

    fun getProfile(): Profile = Profile(
        prefs.getString(FIRST_NAME, DEFAULT_STRING_VALUE)!!,
        prefs.getString(LAST_NAME, DEFAULT_STRING_VALUE)!!,
        prefs.getString(ABOUT, DEFAULT_STRING_VALUE)!!,
        prefs.getString(REPOSITORY, DEFAULT_STRING_VALUE)!!,
        prefs.getInt(RATING, DEFAULT_NUMBER_VALUE),
        prefs.getInt(RESPECT, DEFAULT_NUMBER_VALUE)
    )

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()){
        val key = pair.first
        val value = pair.second

        when(value){
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitives types can be stored in Shared Preferences")
        }
        apply()
    }
}