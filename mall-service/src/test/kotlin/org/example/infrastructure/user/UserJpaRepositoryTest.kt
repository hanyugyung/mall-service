package org.example.infrastructure.user

import org.example.domain.user.User
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class UserJpaRepositoryTest @Autowired constructor(
    val userRepository: UserJpaRepository
) {

    @Test
    fun 사용자_회원가입() {
        var email = "test@test.com"
        var user = User(email, "password1234")

        user = userRepository.save(user)
        assertNotNull(user.id)
    }
}