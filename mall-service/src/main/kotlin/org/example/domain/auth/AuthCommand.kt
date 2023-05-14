package org.example.domain.auth

class AuthCommand {

    class LoginUser(
        val email: String
        , val password: String
    ) {
//        fun toEntity(): User {
//            return User(this.email, this.password)
//        }
    }

    class LoginPartner(
        val email: String
        , val password: String
    ) {
//        fun toEntity(): User {
//            return User(this.email, this.password)
//        }
    }

    class LoginAdmin(
        val email: String
        , val password: String
    ) {
//        fun toEntity(): User {
//            return User(this.email, this.password)
//        }
    }
}