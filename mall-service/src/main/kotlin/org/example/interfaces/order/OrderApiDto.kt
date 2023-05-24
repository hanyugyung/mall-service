package org.example.interfaces.order

import org.example.domain.order.Order
import org.example.domain.order.OrderCommand
import org.example.domain.order.OrderInfo
import java.time.ZonedDateTime

class OrderApiDto {

    class RegisterOrderRequest (
        private val address1: String
        , private val address2: String
        , private val zipCode: String
        , private val receiverContact: String
        , private val receiverName: String
        , private val receiveMemo: String
        , private val orderItemDtoList: List<RegisterOrderItemRequest>
    ) {
        fun toDomainDto(): OrderCommand.RegisterOrder {
            return OrderCommand.RegisterOrder(
                address1 = this.address1
                , address2 = this.address2
                , zipCode = this.zipCode
                , receiverContact = this.receiverContact
                , receiverName = this.receiverName
                , receiveMemo = this.receiveMemo
                , orderItemDtoList = this.orderItemDtoList.map { it.toDomainDto() }
            )
        }
    }

    class RegisterOrderItemRequest (
        private val price: Int
        , val itemToken: String
        , private val itemName: String
        , private val orderItemOptionDtoList: List<RegisterOrderItemOptionRequest>
    ) {
        fun toDomainDto(): OrderCommand.RegisterOrderItem {
            return OrderCommand.RegisterOrderItem(
                price = this.price
                , itemToken = this.itemToken
                , itemName = this.itemName
                , orderItemOptionDtoList = this.orderItemOptionDtoList
                    .map { it.toDomainDto()
                }
            )
        }
    }

    class RegisterOrderItemOptionRequest (
        private val extraPrice: Int = 0
        , private val optionName: String
        , private val count: Int
        , private val itemOptionToken: String
    ) {
        fun toDomainDto(): OrderCommand.RegisterOrderItemOption {
            return OrderCommand.RegisterOrderItemOption(
                extraPrice = this.extraPrice
                , optionName = this.optionName
                , count = this.count
                , itemOptionToken = this.itemOptionToken
            )
        }
    }


    class RegisterOrderResponse(
        private val orderToken: String
    ) {
        companion object {
            fun of(orderToken: String): RegisterOrderResponse {
                return RegisterOrderResponse(orderToken)
            }
        }
    }

    class GetListOfOrderResponse(
        private val price: Int
        , val itemToken: String
        , private val itemName: String
        , private val extraPrice: Int = 0
        , private val optionName: String
        , val count: Int
        , private val orderToken: String
        , private val orderStatus: Order.Status
        , private val orderCreatedAt: ZonedDateTime
    ) {
        companion object{
            fun of(domainDto: OrderInfo.GetListOfOrder): GetListOfOrderResponse {
                return GetListOfOrderResponse(
                    price = domainDto.price
                    , itemToken =  domainDto.itemToken
                    , itemName = domainDto.itemName
                    , extraPrice = domainDto.extraPrice
                    , optionName = domainDto.optionName
                    , count = domainDto.count
                    , orderToken = domainDto.orderToken
                    , orderStatus = domainDto.orderStatus
                    , orderCreatedAt = domainDto.orderCreatedAt
                )
            }
        }
    }

}