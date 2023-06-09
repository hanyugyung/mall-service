package org.example.interfaces.user

import io.swagger.v3.oas.annotations.media.Schema
import lombok.NoArgsConstructor
import org.example.domain.auth.AuthCommand
import org.example.domain.auth.AuthInfo
import org.example.domain.auth.UseRole
import org.example.domain.user.UserCommand
import org.example.domain.user.UserInfo

class UserApiDto {

    @Schema(description = "회원가입 요청 API DTO")
    class SignUpUserRequest(
        @Schema(description = "이메일", example = "test@test.com") val email: String = ""
        , @Schema(description = "암호", example = "1234") val password: String = ""
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

    @Schema(description = "로그인 요청 API DTO")
    class LoginUserRequest(
        @Schema(description = "이메일", example = "test@test.com") val email: String = ""
        , @Schema(description = "암호", example = "1234") val password: String = ""
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