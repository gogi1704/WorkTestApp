package com.l_george.worktestapp.auth

import android.content.Context
import javax.inject.Inject


class TestAuth @Inject constructor(context: Context) {
    companion object {
        const val PREF_AUTH_TOKEN_NAME = "PREF_AUTH_TOKEN_NAME"
        const val PREF_AUTH_TOKEN_VALUE = "PREF_AUTH_TOKEN_VALUE"
    }

    private val authPref = context.getSharedPreferences(PREF_AUTH_TOKEN_NAME, Context.MODE_PRIVATE)

    val isAuth = token != null

    val token: String?
        get() = authPref.getString(PREF_AUTH_TOKEN_VALUE, null)

    fun saveToken(token: String) {
        authPref.edit()
            .putString(PREF_AUTH_TOKEN_VALUE, token)
            .apply()
    }

    fun clearToken() {
        authPref.edit()
            .clear()
            .apply()
    }


}