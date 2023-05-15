package org.example.interfaces.auth

import org.example.domain.auth.AuthCommand
import org.example.domain.auth.AuthInfo

class AuthApiDto {

    class LoginUser(
        private val email: String, private val password: String
    ) {
        fun toDomainDto(): AuthCommand.LoginUser {
            return AuthCommand.LoginUser(this.email, this.password)
        }
    }

    class LoginPartner(
        private val email: String, private val password: String
    ) {
        fun toDomainDto(): AuthCommand.LoginPartner {
            return AuthCommand.LoginPartner(this.email, this.password)
        }
    }

    class LoginAdmin(
        private val email: String, private val password: String
    ) {
        fun toDomainDto(): AuthCommand.LoginAdmin {
            return AuthCommand.LoginAdmin(this.email, this.password)
        }
    }

    class Login(
        val token: String
    ) {
        companion object{
            fun of(domainDto: AuthInfo.Login): Login{
                return Login(domainDto.token)
            }
        }
    }
}