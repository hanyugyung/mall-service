package org.example.domain.auth

class TokenDto(
    val email: String
    , val idToken: String
    , val role: UseRole
) {

    companion object {
        fun of(email: String, idToken: String, role: UseRole): TokenDto {
            return TokenDto(
                email = email
                , idToken = idToken
                , role = role
            )
        }
    }

    fun generateToken(): String {
        // TODO jwt token
        return "token"
    }
}