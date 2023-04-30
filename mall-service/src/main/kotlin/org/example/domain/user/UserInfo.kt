package org.example.domain.user

class UserInfo {

    class SignUpUser(
        val email: String
        , val userToken: String
    ) {
        companion object {
            fun of(user: User): SignUpUser {
                return SignUpUser(
                    email = user.email
                    , userToken = user.userToken
                )
            }
        }
    }
}