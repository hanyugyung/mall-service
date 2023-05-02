package org.example.domain.user

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

        assertNotNull(user.userToken)
    }

    @Test
    fun 사용자_회원가입_이미_존재하는_이메일인경우_실패() {
        var email = "test_exist@test.com"
        var password = "password1234"

        userService.signUpUser(UserCommand.SignUpUser(email, password))

        assertThrows<IllegalArgumentException> {
            userService.signUpUser(UserCommand.SignUpUser(email, password))
        }
    }
}