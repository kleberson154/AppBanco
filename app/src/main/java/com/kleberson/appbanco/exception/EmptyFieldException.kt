package com.kleberson.appbanco.exception

class EmptyFieldException (message: String) : Exception(message) {
    override val message: String?
        get() = "Erro: ${super.message}"
}