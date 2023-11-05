package com.alexsanderdev.motivation.infrastructure

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storageString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    fun getString(key: String): String {
        return security.getString(key, "") ?: ""
    }

}