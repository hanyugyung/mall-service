package org.example.domain.auth

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.interfaces.CommonResponse
import org.example.support.filter.AuthFilter
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

class JwtAuthFilter(
    private val tokenHeader: String
    , private val secretKey: String
): AuthFilter() {

    private fun validateToken(tokenString: String): Claims {

        val claims: Claims = Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(tokenString)
            .body
        val exp = claims["exp"] as Date
        if (exp.before(Date())) {
            throw ExpiredJwtException(Jwts.header(), claims, "Token Expired")
        }
        return claims
    }

    override fun doFilterInternal(request: HttpServletRequest
                                  , response: HttpServletResponse
                                  , filterChain: FilterChain
    ) {

        try {
            val claims = validateToken(request.getHeader(tokenHeader))
            val requester = Requester(
                claims["email"] as String
                , claims["idToken"] as String
                , claims["role"] as String
            )

            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(requester, null, requester.authorities)

        } catch (exception: RuntimeException) {
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = "application/json"
            ObjectMapper().writeValue(response.outputStream
                , CommonResponse.successOf(exception, HttpStatus.UNAUTHORIZED))
        } catch (exception: Exception) {
            response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            response.contentType = "application/json"
            ObjectMapper().writeValue(response.outputStream
                , CommonResponse.failOf(exception))
        }


    }

}