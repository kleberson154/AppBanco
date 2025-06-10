package com.kleberson.appbanco.controller

import android.content.Context
import android.util.Log
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.model.Account

class AccountController(context: Context) {
    var account: Account = Account("", "", "", "", "")
    var db = Database(context)

    override fun toString(): String {
        Log.d("MVC_controller", "Controller iniciado")
        return super.toString()
    }

    fun createAccount(email: String, password: String, firstName: String, lastName: String, phone: String) {
        account = Account(email, password, firstName, lastName, phone)
        db.insertAccount(account.email, account.password, account.firstName, account.lastName, account.phone)
        Log.d("MVC_controller", "Conta criada com sucesso - Dados: ${account.toString()}")
    }

    fun login(email: String, password: String): Boolean {
        val accountFromDb = db.getAccountByEmail(email)
        return if (accountFromDb != null && accountFromDb.password == password) {
            account = accountFromDb
            Log.d("MVC_controller", "Login bem-sucedido: ${account.toString()}")
            true
        } else {
            Log.d("MVC_controller", "Falha no login: email ou senha incorretos")
            false
        }
    }
}