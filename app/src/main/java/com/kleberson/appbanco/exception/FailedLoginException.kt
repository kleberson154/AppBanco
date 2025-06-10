package com.kleberson.appbanco.exception

class FailedLoginException (
    message: String = "Falha ao fazer login. Verifique suas credenciais e tente novamente."
) : Exception(message) {
    override val message: String?
        get() = "Erro: ${super.message}"
}