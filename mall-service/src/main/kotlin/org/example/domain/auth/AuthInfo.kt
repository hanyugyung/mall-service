package org.example.domain.auth

class AuthInfo {

    class Login(
        val token: String
    ) {
        companion object{
            fun of(token: String): Login{
                return Login(token)
            }
        }
    }
}