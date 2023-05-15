package org.example.interfaces.user

import org.example.domain.auth.AuthCommand
import org.example.domain.auth.AuthInfo
import org.example.domain.auth.UseRole
import org.example.domain.user.UserCommand
import org.example.domain.user.UserInfo

class UserApiDto {

    class SignUpUserRequest(
        private val email: String, private val password: String
    ) {
        fun toDomainDto(): UserCommand.SignUpUser {
            return UserCommand.SignUpUser(this.email, this.password)
        }
    }

    class SignUpUserResponse(
        val email: String, val userToken: String
    ) {
        companion object {
            fun of(domainDto: UserInfo.SignUpUser): SignUpUserResponse {
                return SignUpUserResponse(
                    email = domainDto.email, userToken = domainDto.userToken
                )
            }
        }
    }

    class LoginUserRequest(
        private val email: String, private val password: String
    ) {
        fun toDomainDto(): AuthCommand.LoginUser {
            return AuthCommand.LoginUser(this.email, this.password)
        }
    }

    class LoginUserResponse(
        val token: String
        , val role: UseRole
    ) {
        companion object {
            fun of(domainDto: AuthInfo.Login): LoginUserResponse {
                return LoginUserResponse(domainDto.token, domainDto.role)
            }
        }
    }
}