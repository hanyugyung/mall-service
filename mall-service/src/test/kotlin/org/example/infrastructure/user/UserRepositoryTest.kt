package org.example.infrastructure.user

import org.example.domain.user.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 내장DB가 아닌 YML에 설정한 DB사용
@ActiveProfiles("test")
internal class UserRepositoryTest @Autowired constructor(
    val userRepository : UserRepository
) {

    @Test
    fun 사용자_회원가입() {
        var email = "test@test.com"
        var user = User(email, "password1234")

        user = userRepository.save(user)
        assertEquals(user.id, 1L)
    }
}