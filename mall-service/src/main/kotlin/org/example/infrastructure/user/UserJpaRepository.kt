package org.example.infrastructure.user

import org.example.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {

    fun findByUserToken(userToken: String): User?

}