package com.kleberson.appbanco.exception

class EmptyFieldException (message: String = "Erro: Todos os campos devem ser preenchidos.") : Exception(message) {
    override val message: String?
        get() = "Erro: ${super.message}"
}