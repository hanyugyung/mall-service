package org.example.domain.user

interface UserService {
    fun signUpUser(dto: UserCommand.SignUpUser)
}