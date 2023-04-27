package org.example.domain.order

import jakarta.persistence.EntityNotFoundException
import org.example.domain.item.ItemRepository
import org.example.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl constructor(
    val orderRepository: OrderRepository
    , val userRepository: UserRepository
    , val itemRepository: ItemRepository
): OrderService {

    @Transactional
    override fun registerOrder(dto: OrderCommand.RegisterOrder, userToken: String): String {

        val user = userRepository.findByUserToken(userToken) ?: throw EntityNotFoundException("잘못된 사용자입니다.")

        // TODO 재고 파악
        val order = dto.toEntity(user.id!!)
        order.addOrderItemList(
            dto.orderItemDtoList.map {
                val item = itemRepository.findByItemToken(it.itemToken)
                    ?: throw EntityNotFoundException("존재하지 않는 상품입니다.")

                val orderItem = it.toEntity(order, item)
                orderItem.addOrderItemOptionList(
                    it.orderItemOptionDtoList.map { dto -> dto.toEntity(orderItem) }.toList()
                )
                orderItem
            }.toList()
        )
        return orderRepository.save(order)
    }

    @Transactional(readOnly = true)
    override fun getListOfOrdersToUser(userToken: String): List<Order> {
        return listOf()
    }
}