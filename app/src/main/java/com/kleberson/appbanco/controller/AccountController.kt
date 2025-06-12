package com.kleberson.appbanco.controller

import android.content.Context
import com.kleberson.appbanco.database.Database
import com.kleberson.appbanco.model.Account

class AccountController(context: Context) {
    private var db = Database(context)

    fun createAccount(email: String, password: String, firstName: String, lastName: String, phone: String) {
        val account = Account(email, password, firstName, lastName, phone)
        db.insertAccount(account)
    }

    fun login(email: String, password: String): Account? {
        val profile = db.login(email, password)
        if (profile != null) {
            return profile
        } else {
            return null
        }
    }

    fun deposit(email: String, value: Double) {
        db.deposit(email, value)
    }

    fun sake(email: String, value: Double) {
        db.sake(email, value)
    }

    fun transfer(email: String, value: Double, inputEmail: String) {
        db.transfer(email, value, inputEmail)
    }
}