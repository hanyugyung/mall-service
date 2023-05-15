package org.example.domain.auth

import org.example.domain.user.UserCommand
import org.example.domain.user.UserService
import org.example.interfaces.user.UserApiDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.lang.IllegalArgumentException

@SpringBootTest
@ActiveProfiles("test")
internal class AuthServiceTest @Autowired constructor(
    val authService: AuthService
    , val userService: UserService
) {

    private fun signUpUser(email: String, password: String) {
        val user = userService.signUpUser(UserCommand.SignUpUser(email, password))
    }

    @Test
    fun 토큰_발급_요청() {

        // given
        val email = "token_user@test.com"
        val password = "password1234!@#$"
        signUpUser(email, password)

        // when
        val dto = authService.loginUser(AuthCommand.LoginUser(email = email, password = password))

        // then
        assertEquals(dto.role, UseRole.CUSTOMER)
        assertNotNull(dto.token)
    }

    @Test
    fun 토큰_발급_요청시_인증실패() {

        // when, then
        assertThrows<IllegalArgumentException> {
            authService.loginUser(AuthCommand.LoginUser(email = "email", password = "password"))
        }

    }
}