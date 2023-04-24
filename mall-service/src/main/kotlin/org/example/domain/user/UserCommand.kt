package org.example.domain.user

class UserCommand {

    class SignUpUser(
        private val email: String
        , private val password: String
    ) {
        fun toEntity(): User {
            return User(this.email, this.password)
        }
    }
}