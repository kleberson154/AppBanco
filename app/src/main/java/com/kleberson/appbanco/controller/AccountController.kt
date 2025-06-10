package com.kleberson.appbanco.controller

import android.content.Context
import android.util.Log
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.model.Account


class AccountController(private val context: Context) {
    var account: Account = Account("", "", "", "", "")
    var db = Database(context)

    override fun toString(): String {
        Log.d("MVC_controller", "Controller iniciado")
        return super.toString()
    }

    fun createAccount(phone: String) {
        Log.d("MVC_controller", "Conta criada com sucesso")
    }

    fun saveAccount(email: String, password: String, confirmPassword: String) {
        if (confirmPassword(password, confirmPassword)) {
            Log.d("MVC_controller", "Senha confirmada")
        } else {
            Log.d("MVC_controller", "Senha não confirmada")
            return
        }
        account = Account(email, password, "", "", "")
        Log.d("MVC_controller", "Dados salvos: ${account.toString()}")
    }

    fun login(email: String, password: String): Boolean {
        return account.email == email && account.password == password
    }

    fun setName(firstName: String, lastName: String) {
        account.firstName = firstName
        account.lastName = lastName
        Log.d("MVC_controller", "Dados salvos: ${account.toString()}")
    }

    fun setPhone(phone: String) {
        account.phone = phone
        Log.d("MVC_controller", "Dados salvos: ${account.toString()}")
    }

    fun confirmPassword(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}