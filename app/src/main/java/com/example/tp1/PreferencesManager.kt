package com.example.tp1

import android.content.Context
import android.content.SharedPreferences

/**
 * PreferencesManager - Singleton class for managing SharedPreferences
 * This demonstrates using SharedPreferences alongside SQLite/Room database
 */
object PreferencesManager {
    private const val PREFS_NAME = "app_preferences"
    
    // Preference keys
    private const val KEY_THEME = "theme"
    private const val KEY_FIRST_LAUNCH = "is_first_launch"
    private const val KEY_DEFAULT_COUNTRY = "default_country"
    
    // Theme values
    const val THEME_LIGHT = "light"
    const val THEME_DARK = "dark"
    
    private lateinit var prefs: SharedPreferences
    
    /**
     * Initialize the PreferencesManager
     * Must be called before using any other methods
     */
    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }
    
    /**
     * Get the current theme preference
     * @return THEME_LIGHT or THEME_DARK
     */
    fun getTheme(): String {
        return prefs.getString(KEY_THEME, THEME_LIGHT) ?: THEME_LIGHT
    }
    
    /**
     * Set the theme preference
     * @param theme Either THEME_LIGHT or THEME_DARK
     */
    fun setTheme(theme: String) {
        prefs.edit().putString(KEY_THEME, theme).apply()
    }
    
    /**
     * Check if this is the first launch of the app
     * @return true if first launch, false otherwise
     */
    fun isFirstLaunch(): Boolean {
        return prefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }
    
    /**
     * Mark that the app has been launched
     * This should be called after handling first launch logic
     */
    fun setFirstLaunchComplete() {
        prefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }
    
    /**
     * Get the default country preference
     * @return The saved country name, or "France" as default
     */
    fun getDefaultCountry(): String {
        return prefs.getString(KEY_DEFAULT_COUNTRY, "France") ?: "France"
    }
    
    /**
     * Set the default country preference
     * @param country The country name to save
     */
    fun setDefaultCountry(country: String) {
        prefs.edit().putString(KEY_DEFAULT_COUNTRY, country).apply()
    }
    
    /**
     * Clear all preferences (useful for testing or logout)
     */
    fun clearAll() {
        prefs.edit().clear().apply()
    }
}
