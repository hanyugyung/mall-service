package org.example.domain.user

import org.example.domain.auth.AuthCommand

class UserCommand {

    class SignUpUser(
        val email: String
        , private val password: String
    ) {
        fun toEntity(): User {
            return User(this.email, this.password)
        }
    }
}