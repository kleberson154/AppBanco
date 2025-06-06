package com.kleberson.appbanco.controller


import android.content.Context
import android.util.Log
import com.kleberson.appbanco.model.Account

class UserController(private val context: Context) {
    val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    override fun toString(): String {
        Log.d("MVC_controller", "Controller iniciado")
        return super.toString()
    }

    fun saveAccount(email: String, password: String) {
        val account = Account(email, password)
        savePreferences(account)
        Log.d("MVC_controller", "Dados salvos: ${account.toString()}")
    }

    private fun clearPreferences() {
        editor.clear()
        editor.apply()
    }

    private fun savePreferences(account: Account) {
        editor.putString("email", account.email)
        editor.putString("password", account.password)
        editor.apply()
    }

    fun getPreferences(): Account {
        return Account(
            sharedPreferences.getString("email", "") ?: "",
            sharedPreferences.getString("password", "") ?: "",
        )
    }
}