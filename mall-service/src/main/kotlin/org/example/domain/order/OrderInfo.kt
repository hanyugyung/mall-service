package org.example.domain.order

import org.example.domain.order.address.OrderAddress
import org.example.domain.order.item.OrderItem
import org.example.domain.order.item.OrderItemOption
import java.time.ZonedDateTime

class OrderInfo {

    class GetListOfOrder(
        val totalPrice: Int
        , val orderToken: String
        , val orderStatus: Order.Status
        , val orderCreatedAt: ZonedDateTime
        , val orderAddressInfo: OrderAddressInfo
    ) {
        companion object {
            fun of(order: Order): GetListOfOrder {
                return GetListOfOrder(
                    totalPrice = order.totalPrice
                    , orderToken = order.orderToken
                    , orderStatus = order.status
                    , orderCreatedAt = order.createdAt
                    , orderAddressInfo = OrderAddressInfo.of(order.orderAddress)
                )
            }
        }
    }

    class OrderAddressInfo(
        val address1: String
        , val address2: String
        , val zipCode: String
        , val receiverContact: String
        , val receiverName: String
        , val receiveMemo: String
    ) {
        companion object {
            fun of(orderAddress: OrderAddress): OrderAddressInfo {
                return OrderAddressInfo(
                    address1 = orderAddress.address1
                    , address2 = orderAddress.address2
                    , zipCode = orderAddress.zipCode
                    , receiverContact = orderAddress.receiverContact
                    , receiverName = orderAddress.receiverName
                    , receiveMemo = orderAddress.receiveMemo
                )
            }
        }
    }

    class GetOrderDetail(
        val itemName: String
        , val itemToken: String
        , val itemPrice: Int
        , val itemOptionList: List<GetOrderDetailItemOption>
    ) {
        companion object {
            fun of(orderItem: OrderItem): GetOrderDetail {
                return GetOrderDetail(
                    itemName = orderItem.itemName
                    , itemToken = orderItem.itemToken
                    , itemPrice = orderItem.price
                    , orderItem.orderItemOptionList.map { GetOrderDetailItemOption.of(it) })
            }
        }
    }

    class GetOrderDetailItemOption(
        var extraPrice: Int = 0
        , var optionName: String
        , var count: Int = 0
    ) {
        companion object {
            fun of(orderItemOption: OrderItemOption): GetOrderDetailItemOption {
                return GetOrderDetailItemOption(
                    extraPrice = orderItemOption.extraPrice
                    , optionName = orderItemOption.optionName
                    , count = orderItemOption.count
                )
            }
        }
    }
}
