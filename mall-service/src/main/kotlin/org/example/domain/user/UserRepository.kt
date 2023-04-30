package org.example.domain.user

interface UserRepository {
    fun store(user: User)

    fun findBy(userToken: String): User?

    fun findByEmail(email: String): User?
}