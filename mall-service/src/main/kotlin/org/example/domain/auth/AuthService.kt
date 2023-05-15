package org.example.domain.auth

import org.springframework.transaction.annotation.Transactional

interface AuthService {

    fun validateAccount(email: String, password: String, role: UseRole): TokenDto

    @Transactional(readOnly = true)
    private fun issueToken(email: String, password: String, role: UseRole): String {
        /**
         * 1. 사용자, 암호 확인
         * 2. jwt 토큰 발급
         */
        val tokenDto = validateAccount(email, password, role)
        return tokenDto.generateToken()
    }

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

    fun loginAdmin(dto: AuthCommand.LoginAdmin): AuthInfo.Login {
        return AuthInfo.Login.of(
            issueToken(dto.email, dto.password, UseRole.ADMIN)
        )
    }

}