package org.example.interfaces.user

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
internal class UserApiControllerTest {

    val urlPrefix = "/api/users"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @Transactional
    fun 사용자_회원가입() {

        // given
        val requestDtoStr = """
            {
                "email" : "test_user@test.com"
                , "password" : "pass1234!@#"
            }
        """

        // when, then
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("$urlPrefix/sign-up")
                    .content(requestDtoStr)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.userToken").exists())
    }
}