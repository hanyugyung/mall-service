package org.example.infrastructure.user

import org.example.domain.user.User
import org.example.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl @Autowired constructor(
    private val userRepository: UserJpaRepository
): UserRepository {
    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun findByUserToken(userToken: String): User? {
        return userRepository.findByUserToken(userToken)
    }
}