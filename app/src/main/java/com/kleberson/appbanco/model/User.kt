package com.kleberson.appbanco.model

class User(val email: String, val password: String) {
    override fun toString(): String {
        return "User(email='$email', password='$password')"
    }
}