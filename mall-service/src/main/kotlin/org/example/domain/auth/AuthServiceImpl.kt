package org.example.domain.auth

import org.example.domain.partner.PartnerRepository
import org.example.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
    , private val partnerRepository: PartnerRepository
) : AuthService {

    private fun validatePassword(password: String, passwordHash: String): Boolean {
        // TODO password encoding 확인
        return true
    }

    override fun validateAccount(email: String, password: String, role: UseRole): TokenDto {
        when (role) {
            UseRole.CUSTOMER -> {
                val user = userRepository.findByEmail(email) ?: throw IllegalArgumentException("인증 실패")
                validatePassword(password, user.password)
                return TokenDto.of(user.email, user.userToken, role)
            }

            UseRole.PARTNER -> {
                val partner = partnerRepository.findByEmail(email) ?: throw IllegalArgumentException("인증 실패")
                validatePassword(password, partner.password)
                return TokenDto.of(partner.email, partner.partnerToken, role)
            }
            // TODO admin 추가
//            UseRole.ADMIN -> {
//                val admin = adminRepository.findByEmail(email) ?: throw IllegalArgumentException("인증 실패")
//                validatePassword(password, admin.password)
//                return TokenDto.of(admin.email, admin.adminToken, role)
//            }
            else -> {
                return TokenDto.of("admin.email", "admin.partnerToken", role)
            }
        }
    }

}