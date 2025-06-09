package com.kleberson.appbanco.model

class Account(val email: String, val password: String, var firstName: String, var lastName: String, var phone: String) {
    override fun toString(): String {
        return "Account(email='$email', password='$password', firstName='$firstName', lastName='$lastName', phone='$phone')"
    }
}