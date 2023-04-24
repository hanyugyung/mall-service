package org.example.domain.user

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class UserServiceTest @Autowired constructor(
    val userService: UserService
) {

    @Test
    fun 사용자_회원가입() {
        var email = "test@test.com"
        var password = "password1234"

        val user = userService.signUpUser(UserCommand.SignUpUser(email, password))

        assertNotNull(user.id)
    }
}