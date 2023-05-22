package org.example.domain.item

import jakarta.persistence.EntityNotFoundException
import org.example.domain.partner.Partner
import org.example.domain.partner.PartnerCommand
import org.example.domain.partner.PartnerRepository
import org.example.domain.partner.PartnerService
import org.example.infrastructure.partner.PartnerJpaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
internal class ItemServiceTest @Autowired constructor(
    val itemService: ItemService
    , val partnerService: PartnerService
) {

    @Test
    @Transactional
    fun 상품_등록() {

        val partnerToken = partnerService.registerPartner(
            PartnerCommand.RegisterPartner(
                email = "itempartner@test.com", "pw1234", "111-11-11111", "브랜드1"
            )
        )

        val option1 = ItemCommand.RegisterItemOption(10, 1000, "청바지S")
        val option2 = ItemCommand.RegisterItemOption(5, 2500, "청바지M")

        val dto = ItemCommand.RegisterItem("상품1", 10000, listOf(option1, option2))

        assertNotNull(itemService.registerItem(dto, partnerToken))
    }

    @Test
    @Transactional
    fun 등록_상품_조회() {

        val partnerToken = partnerService.registerPartner(
            PartnerCommand.RegisterPartner(
                email = "itempartner@test.com", "pw1234", "111-11-11111", "브랜드1"
            )
        )

        val option1 = ItemCommand.RegisterItemOption(10, 1000, "청바지S")
        val option2 = ItemCommand.RegisterItemOption(5, 2500, "청바지M")
        val dto = ItemCommand.RegisterItem("상품1", 10000, listOf(option1, option2))
        itemService.registerItem(dto, partnerToken)

        val info = itemService.getListOfItems(partnerToken)

        assertEquals(1, info.size)

        val optionList = itemService.getItemOption(info[0].itemToken)
        assertEquals(2, optionList.size)
    }

    @Test
    fun 등록_상품_조회_파트너token이_없는_경우_실패() {
        val partnerToken = "token"
        assertThrows<EntityNotFoundException> {
            itemService.getListOfItems(partnerToken)
        }
    }
}