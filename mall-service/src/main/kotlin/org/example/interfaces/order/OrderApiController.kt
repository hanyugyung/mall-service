package org.example.interfaces.order

import jakarta.validation.Valid
import org.example.domain.auth.Requester
import org.example.domain.order.OrderService
import org.example.interfaces.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderApiController @Autowired constructor(
    private val orderService: OrderService
) {

    @GetMapping
    fun getListOfOrder(@AuthenticationPrincipal requester: Requester):
            CommonResponse<List<OrderApiDto.GetListOfOrderResponse>> {

        return CommonResponse.successOf(
            orderService.getListOfOrder(requester.idToken).map {
                OrderApiDto.GetListOfOrderResponse.of(it)
            })
    }

    @PostMapping
    fun registerOrder(@AuthenticationPrincipal requester: Requester
                      , @RequestBody @Valid requestDto: OrderApiDto.RegisterOrderRequest
    ): CommonResponse<OrderApiDto.RegisterOrderResponse> {

        return CommonResponse.successOf(
            OrderApiDto.RegisterOrderResponse(
                orderService.registerOrder(requestDto.toDomainDto(), requester.idToken)
            )
        )
    }

}