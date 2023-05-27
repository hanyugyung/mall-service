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

        val user = userRepository.findBy(userToken) ?: throw EntityNotFoundException("잘못된 사용자입니다.")

        val order = dto.toEntity(user.userToken)
        order.addOrderItemList(
            dto.orderItemDtoList.map {
                val item = itemRepository.findBy(it.itemToken)
                    ?: throw EntityNotFoundException("존재하지 않는 상품입니다.")

                val orderItem = it.toEntity(order, item)
                orderItem.addOrderItemOptionList(
                    it.orderItemOptionDtoList.map { rgstrOIO ->
                        val itemOption =
                            item.itemOptionList.first { io -> io.itemOptionToken == rgstrOIO.itemOptionToken }
                        itemOption.sell(rgstrOIO.count)
                        rgstrOIO.toEntity(orderItem)
                    }.toList()
                )
                orderItem
            }
        )

        orderRepository.store(order)

        return order.orderToken
    }

    @Transactional(readOnly = true)
    override fun getListOfOrder(userToken: String): List<OrderInfo.GetListOfOrder> {
        return orderRepository.findAllBy(userToken)
            .map { OrderInfo.GetListOfOrder.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getOrderDetail(userToken: String, orderToken: String): List<OrderInfo.GetOrderDetail> {

        val order = orderRepository.findBy(userToken, orderToken)
            ?: throw EntityNotFoundException("존재하지 않는 주문 이력입니다.")

        return order.orderItemList.map { OrderInfo.GetOrderDetail.of(it) }
    }
}