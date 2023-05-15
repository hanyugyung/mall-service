package org.example.domain.auth

import org.springframework.transaction.annotation.Transactional

interface AuthService {

    fun issueToken(email: String, password: String, role: UseRole): TokenDto.TokenInfo

    fun loginUser(dto: AuthCommand.LoginUser): AuthInfo.Login {
        return AuthInfo.Login.of(
            issueToken(dto.email, dto.password, UseRole.CUSTOMER)
        )
    }

    fun loginPartner(dto: AuthCommand.LoginPartner): AuthInfo.Login {
        return AuthInfo.Login.of(
            issueToken(dto.email, dto.password, UseRole.PARTNER)
        )
    }
}