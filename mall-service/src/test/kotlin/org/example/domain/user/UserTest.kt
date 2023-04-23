package org.example.domain.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class UserTest {
    @Test
    fun 사용자_엔티티를_생성한다() {
        var email = "test@test.com"
        var user = User(email, "password1234")
        assertEquals(email, user.email)
    }
}

