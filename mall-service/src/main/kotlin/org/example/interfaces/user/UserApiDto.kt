package org.example.interfaces.user

import org.example.domain.user.User
import org.example.domain.user.UserCommand
import org.example.domain.user.UserInfo

class UserApiDto {

    class SignUpUserRequest(
        private val email: String
        , private val password: String
    ) {
        fun toDomainDto(): UserCommand.SignUpUser {
            return UserCommand.SignUpUser(this.email, this.password)
        }
    }

    class SignUpUserResponse(
        val email: String
        , val userToken: String
    ) {
        companion object {
            fun of(domainDto: UserInfo.SignUpUser): SignUpUserResponse {
                return SignUpUserResponse(
                    email = domainDto.email, userToken = domainDto.userToken
                )
            }
        }
    }
}