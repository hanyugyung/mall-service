package org.example.domain.order

import java.time.ZonedDateTime

class OrderInfo {

    class GetListOfOrder(
        val price: Int
        , val itemToken: String
        , val itemName: String
        , val extraPrice: Int = 0
        , val optionName: String
        , val count: Int
        , val orderToken: String
        , val orderStatus: Order.Status
        , val orderCreatedAt: ZonedDateTime
    )


//    class RegisterOrder(
//        private val address1: String
//        , private val address2: String
//        , private val zipCode: String
//        , private val receiverContact: String
//        , private val receiverName: String
//        , private val receiveMemo: String
//        , val orderItemDtoList: List<RegisterOrderItem>
//    ) {
//        fun toEntity(userId: Long): Order {
//
//            val orderAddress = OrderAddress(
//                address1 = this.address1
//                , address2 = this.address2
//                , zipCode = this.zipCode
//                , receiverContact = this.receiverContact
//                , receiverName = this.receiverName
//                , receiveMemo = receiveMemo
//            )
//
//            return Order(
//                userId = userId
//                , orderAddress = orderAddress
//            )
//        }
//    }
//
//    class RegisterOrderItem(
//        private val price: Int
//        , val itemToken: String
//        , private val itemName: String
//        , val orderItemOptionDtoList: List<RegisterOrderItemOption>
//    ) {
//        fun toEntity(order: Order, item: Item): OrderItem {
//            return OrderItem(
//                order = order
//                , price = this.price
//                , item = item
//                , itemName = this.itemName
//            )
//        }
//    }
//
//    class RegisterOrderItemOption(
//        private val extraPrice: Int = 0
//        , private val optionName: String
//        , val count: Int
//        , val itemOptionToken: String
//    ) {
//        fun toEntity(orderItem: OrderItem): OrderItemOption {
//            return OrderItemOption(
//                orderItem = orderItem
//                , extraPrice = this.extraPrice
//                , optionName = this.optionName
//                , count = this.count
//            )
//        }
//    }

}
