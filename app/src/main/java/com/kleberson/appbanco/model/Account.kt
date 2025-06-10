package com.kleberson.appbanco.model

class Account(val email: String, val password: String, var firstName: String, var lastName: String, var phone: String, val balance: Double = 0.0, val limitCredit: Double = 1000.0) {
    override fun toString(): String {
        return "Account(email='$email', password='$password', firstName='$firstName', lastName='$lastName', phone='$phone, balance=$balance, limitCredit=$limitCredit)"
    }
}