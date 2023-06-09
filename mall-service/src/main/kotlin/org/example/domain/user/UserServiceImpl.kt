package org.example.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
    , private val passwordEncoder: PasswordEncoder
) : UserService {

    private fun existEmailAlready(email: String) {
        if(userRepository.findByEmail(email) != null) throw IllegalArgumentException("이미 존재하는 이메일 입니다.")
    }

    @Transactional
    override fun signUpUser(dto: UserCommand.SignUpUser) : UserInfo.SignUpUser {
        existEmailAlready(dto.email)
        val user = dto.toEntity()
        user.encodePassword(passwordEncoder.encode(user.password))
        userRepository.store(user)
        return UserInfo.SignUpUser.of(user)
    }
}