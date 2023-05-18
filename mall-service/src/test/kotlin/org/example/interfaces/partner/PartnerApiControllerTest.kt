package org.example.interfaces.partner

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
internal class PartnerApiControllerTest {

    val urlPrefix = "/api/partners"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @Transactional
    fun 파트너_등록_요청() {

        // TODO 인증 테스트단에서 어떻게 할지 고민 필요~

        // given
        val requestDtoStr = """
            {
                "email" : "test_partner@test.com"
                , "password" : "pass1234!@#"
                , "bizNo" : "111-11-11111"
                , "brandName" : "test브랜드"
            }
        """

        // when, then
        mockMvc
            .perform(
                post("$urlPrefix/register")
                    .content(requestDtoStr)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk)
    }


}