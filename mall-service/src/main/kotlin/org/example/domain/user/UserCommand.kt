package org.example.domain.user

class UserCommand {

    class SignUpUser(val email: String, val password: String) {
        fun toEntity(): User {
            return User(this.email, this.password)
        }
    }
}