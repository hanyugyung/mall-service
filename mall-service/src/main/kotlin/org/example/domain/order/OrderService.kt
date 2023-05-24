package org.example.domain.order;

interface OrderService {

    fun registerOrder(dto: OrderCommand.RegisterOrder, userToken: String): String

    fun getListOfOrder(userToken: String): List<OrderInfo.GetListOfOrder>

}
