package org.example.domain.user

interface UserRepository {
    fun save(user: User): User

    fun findByUserToken(userToken: String): User?
}