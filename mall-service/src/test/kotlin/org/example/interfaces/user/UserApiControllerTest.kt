package org.example.interfaces.user

import org.example.interfaces.CommonResponse
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

    @Test
    @Transactional
    fun 이메일_중복시_사용자_회원가입에_실패한다() {

        // given
        val requestDtoStr1 = """
            {
                "email" : "test_user@test.com"
                , "password" : "pass1234!@#"
            }
        """

        mockMvc
            .perform(
                MockMvcRequestBuilders.post("$urlPrefix/sign-up")
                    .content(requestDtoStr1)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)

        val requestDtoStr2 = """
            {
                "email" : "test_user@test.com"
                , "password" : "pass1234!@#"
            }
        """

        mockMvc
            .perform(
                MockMvcRequestBuilders.post("$urlPrefix/sign-up")
                    .content(requestDtoStr2)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(CommonResponse.ResultStatus.SUCCESS.toString()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("이미 존재하는 이메일 입니다."))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist())

    }
}