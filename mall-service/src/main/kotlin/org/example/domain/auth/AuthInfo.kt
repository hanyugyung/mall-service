package org.example.domain.auth

class AuthInfo {

    class Login(
        val token: String
        , val role: UseRole
    ) {
        companion object{
            fun of(tokenDto: TokenDto.TokenInfo): Login{
                return Login(tokenDto.token, tokenDto.role)
            }
        }
    }
}