package org.example.infrastructure.user

import org.example.domain.user.User
import org.example.domain.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl @Autowired constructor(
    private val userRepository: UserJpaRepository
): UserRepository {
    override fun store(user: User) {
        userRepository.save(user)
    }

    override fun findBy(userToken: String): User? {
        return userRepository.findByUserToken(userToken)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}