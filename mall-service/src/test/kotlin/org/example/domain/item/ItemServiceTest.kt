package org.example.domain.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
internal class ItemServiceTest @Autowired constructor(
    val itemService: ItemService
) {

    @Transactional
    @Test
    fun 상품_등록() {

        val partnerId = 1L

        val option1 = ItemCommand.RegisterItemOption(10, 1000, "청바지S")
        val option2 = ItemCommand.RegisterItemOption(5, 2500, "청바지M")

        val dto = ItemCommand.RegisterItem("상품1", 10000, listOf(option1, option2))

        assertNotNull(itemService.registerItem(dto, partnerId))
    }

    @Test
    fun 등록_상품_조회() {
        val partnerId = 2L
        val option1 = ItemCommand.RegisterItemOption(10, 1000, "청바지S")
        val option2 = ItemCommand.RegisterItemOption(5, 2500, "청바지M")
        val dto = ItemCommand.RegisterItem("상품1", 10000, listOf(option1, option2))
        itemService.registerItem(dto, partnerId)

        val info = itemService.getListOfItems(partnerId)

        assertEquals(1, info.size)
        assertEquals(2, info[0].itemOptionDtoList.size)
    }
}