package org.example.domain.partner

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class PartnerServiceTest {

    @Autowired
    private lateinit var partnerService: PartnerService

    companion object {

        @JvmStatic
        @BeforeAll
        fun setup(@Autowired partnerService: PartnerService) {

            val partner1 = PartnerCommand.RegisterPartner(
                email = "email@test.com", "pw1234", "111-11-11111", "브랜드1"
            )
            val partner2 = PartnerCommand.RegisterPartner(
                email = "email2@test.com", password = "pw1234", bizNo = "111-11-11112", brandName = "브랜드2"
            )
            partnerService.registerPartner(partner1)
            partnerService.registerPartner(partner2)
        }
    }

    @Test
    fun 브랜드목록_조회() {

        val lst = partnerService.getPartnerBrandInfo()

        assertEquals(2, lst.size)

    }
}