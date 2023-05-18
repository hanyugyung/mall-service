package org.example.domain.auth

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*


class TokenDto {

    class TokenPayload(
        val email: String
        , val idToken: String
        , val role: UseRole
    ) {
        companion object {
            fun of(email: String, idToken: String, role: UseRole): TokenPayload {
                return TokenPayload(
                    email = email, idToken = idToken, role = role
                )
            }
        }

        fun generateToken(jwtProperty: JwtProperty): String {

            val iat = Date()
            val exp = Date(iat.time + jwtProperty.expireMs.toLong()) // TODO exp, issuer, secretkey 설정파일에서 가져오도록 수정

            val claims = mutableMapOf<String, Any>()
            claims["email"] = this.email
            claims["idToken"] = this.idToken
            claims["role"] = this.role
            claims["issuer"] = jwtProperty.issuer
            claims["iat"] = iat
            claims["exp"] = exp

            return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey.toByteArray())
                .compact()
        }
    }

    class TokenInfo(
        val token: String
        , val role: UseRole
    ) {
        companion object {
            fun of(payload: TokenPayload, jwtProperty: JwtProperty): TokenInfo {
                return TokenInfo(
                    payload.generateToken(jwtProperty), role = payload.role
                )
            }
        }
    }
}