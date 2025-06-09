package com.kleberson.appbanco.util

import android.content.Context
import com.kleberson.appbanco.model.Account

class Util(val context: Context) {
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    fun confirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun clearPreferences() {
        editor.clear()
        editor.apply()
    }

    fun savePreferences(account: Account) {
        editor.putString("email", account.email)
        editor.putString("password", account.password)
        editor.apply()
    }

    fun getPreferences(): Account {
        return Account(
            sharedPreferences.getString("email", "") ?: "",
            sharedPreferences.getString("password", "") ?: ""
            , "", "", ""
        )
    }
}