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

    fun login(email: String, password: String): Account? {
        val profile = db.login(email, password)
        if (profile != null) {
            Log.d("MVC_controller", "Login realizado com sucesso - Email: $email")
            return profile
        } else {
            Log.d("MVC_controller", "Falha no login - Email: $email")
            return null
        }
    }

    fun deposit(email: String, value: Double) {
        db.deposit(email, value)
        Log.d("MVC_controller", "Depósito realizado com sucesso - Valor: $value")
    }

    fun sake(email: String, value: Double) {
        db.sake(email, value)
        Log.d("MVC_controller", "Sake realizado com sucesso - Valor: $value")
    }

    fun transfer(email: String, value: Double, inputEmail: String) {
        db.transfer(email, value, inputEmail)
        Log.d("MVC_controller", "Transferência realizada com sucesso - Valor: $value para $inputEmail")
    }
}