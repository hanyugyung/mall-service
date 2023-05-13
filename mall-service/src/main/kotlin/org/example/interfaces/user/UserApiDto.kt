package org.example.interfaces.user

import org.example.domain.user.UserCommand

class UserApiDto {

    class SignUpUser(
        private val email: String
        , private val password: String
    ) {
        fun toDomainDto(): UserCommand.SignUpUser {
            return UserCommand.SignUpUser(this.email, this.password)
        }
    }

}