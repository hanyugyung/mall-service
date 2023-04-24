package org.example.domain.item

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
internal class ItemServiceTest @Autowired constructor(
    val itemService: ItemService
) {

    @Test
    fun 등록_상품_조회() {

        var partnerId = 1L
        var dto1 = ItemCommand.RegisterItem("상품1", 10000, partnerId)
        var dto2 = ItemCommand.RegisterItem("상품2", 20000, partnerId)
        itemService.registerItem(dto1)
        itemService.registerItem(dto2)

        val lst = itemService.getListOfItem(partnerId)

        assertEquals(lst.size, 2)
    }
}