package org.example.domain.auth

class AuthCommand {

    class LoginUser(
        val email: String
        , val password: String
    ) {
    }

    class LoginPartner(
        val email: String
        , val password: String
    ) {
    }
}