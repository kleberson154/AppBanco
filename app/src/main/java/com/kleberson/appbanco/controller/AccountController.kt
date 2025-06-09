package com.kleberson.appbanco.controller

import android.util.Log
import com.kleberson.appbanco.model.Account
import com.kleberson.appbanco.util.Util

class AccountController {
    val util: Util = TODO()

    override fun toString(): String {
        Log.d("MVC_controller", "Controller iniciado")
        return super.toString()
    }

    fun createAccount(email: String, password: String, confirmPassword: String) {
        if (util.confirmPassword(password, confirmPassword)) {
            Log.d("MVC_controller", "Senha confirmada")
        } else {
            Log.d("MVC_controller", "Senha não confirmada")
            return
        }
        val account = Account(email, password, "", "", "")
        util.savePreferences(account)
        Log.d("MVC_controller", "Dados salvos: ${account.toString()}")
    }

    fun login(email: String, password: String): Boolean {
        val account = util.getPreferences()
        return account.email == email && account.password == password
    }
}