package org.example.domain.order

import org.example.domain.item.Item
import org.example.domain.item.ItemCommand
import org.example.domain.item.ItemService
import org.example.domain.partner.Partner
import org.example.domain.partner.PartnerCommand
import org.example.domain.partner.PartnerRepository
import org.example.domain.partner.PartnerService
import org.example.domain.user.*
import org.example.infrastructure.item.ItemJpaRepository
import org.example.infrastructure.order.OrderJpaRepository
import org.example.infrastructure.partner.PartnerJpaRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
internal class OrderServiceTest @Autowired constructor(
    val orderService: OrderService
    , val userService: UserService
    , val partnerRepository: PartnerJpaRepository
    , val itemRepository: ItemJpaRepository
    , val orderRepository: OrderJpaRepository
) {

    lateinit var user: UserInfo.SignUpUser
    lateinit var partner: Partner
    lateinit var item: Item

    @BeforeAll
    fun setup() {
        var email = UUID.randomUUID().toString().substring(0,5) + "test@test.com"
        var password = "password1234"

        user = userService.signUpUser(UserCommand.SignUpUser(email, password))

        partner = partnerRepository.save(Partner(
            email = "email2@test.com", password = "pw1234", bizNo = "111-11-11112", brandName = "브랜드2"
        ))

        val option1 = ItemCommand.RegisterItemOption(10, 1000, "청바지S")
        val option2 = ItemCommand.RegisterItemOption(5, 2500, "청바지M")
        val dto = ItemCommand.RegisterItem("상품1", 10000, listOf(option1, option2))

        item = itemRepository.save(dto.toEntity(partner.id!!))

    }

    @Transactional
    @Test
    fun 주문하기() {

        val initStock = item.itemOptionList[0].stock

        val dto = OrderCommand.RegisterOrder(
            address1 = "xx시 xx구 xx동"
            , address2 = "xx호"
            , zipCode = "11111"
            , receiverContact = "010-0000-0000"
            , receiverName = "test"
            , receiveMemo = "문앞에서 전화주세요"
            , orderItemDtoList = listOf(
                OrderCommand.RegisterOrderItem(
                    price = 10000
                    , itemToken = item.itemToken
                    , itemName = item.name
                    , orderItemOptionDtoList = listOf(OrderCommand.RegisterOrderItemOption(
                            extraPrice = item.itemOptionList[0].extraPrice
                        , optionName = item.itemOptionList[0].optionName
                        , count = 1
                        , item.itemOptionList[0].itemOptionToken
                        )
                    )
                )
            )
        )

        val order = orderRepository.findByOrderToken(orderService.registerOrder(dto, user.userToken))
        item = itemRepository.findByItemToken(item.itemToken)!!

        assertEquals(order!!.totalPrice, 11000)


        assertEquals(item.itemOptionList[0].stock, initStock - 1)
    }
}