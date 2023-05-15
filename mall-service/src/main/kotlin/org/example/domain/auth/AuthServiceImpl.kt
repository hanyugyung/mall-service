package org.example.domain.auth

import org.example.domain.partner.PartnerRepository
import org.example.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
    , private val partnerRepository: PartnerRepository
    , private val passwordEncoder: PasswordEncoder
) : AuthService {

    private fun validatePassword(password: String, passwordHash: String): Boolean {
        return passwordEncoder.matches(password, passwordHash)
    }

    private fun validateAccount(email: String, password: String, role: UseRole): TokenDto.TokenPayload {
        when (role) {
            UseRole.CUSTOMER -> {
                val user = userRepository.findByEmail(email) ?: throw IllegalArgumentException("인증 실패")
                validatePassword(password, user.password)
                return TokenDto.TokenPayload.of(user.email, user.userToken, role.of(user.role))
            }

            UseRole.PARTNER -> {
                val partner = partnerRepository.findByEmail(email) ?: throw IllegalArgumentException("인증 실패")
                validatePassword(password, partner.password)
                return TokenDto.TokenPayload.of(partner.email, partner.partnerToken, role)
            }
            else -> {
                throw IllegalStateException("잘못된 접근입니다.")
            }
        }
    }

    @Transactional(readOnly = true)
    override fun issueToken(email: String, password: String, role: UseRole): TokenDto.TokenInfo {
        /**
         * 1. 사용자, 암호 확인
         * 2. jwt 토큰 발급
         */
        val payload = validateAccount(email, password, role)
        return TokenDto.TokenInfo.of(payload)
    }
}