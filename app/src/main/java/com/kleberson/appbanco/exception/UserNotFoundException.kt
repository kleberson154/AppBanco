package com.kleberson.appbanco.exception

class UserNotFoundException(
    message: String = "Usuário não encontrado. Verifique o email e tente novamente."
) : Exception(message) {
    override val message: String?
        get() = "Erro: ${super.message}"
}