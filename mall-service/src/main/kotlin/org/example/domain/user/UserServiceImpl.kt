package org.example.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserService {

    @Transactional
    override fun signUpUser(dto: UserCommand.SignUpUser) : UserInfo.SignUpUser {

        // TODO email 중복 검사

        val user = dto.toEntity() // TODO password 암호화
        userRepository.store(user)
        return UserInfo.SignUpUser.of(user)
    }
}