package org.example.interfaces.auth

import jakarta.validation.Valid
import org.example.domain.auth.AuthService
import org.example.interfaces.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthApiController @Autowired constructor(
    private val authService: AuthService
) {

    @PostMapping("/users/login")
    fun loginUser(@RequestBody @Valid requestDto: AuthApiDto.LoginUser): CommonResponse<AuthApiDto.Login> {
        return CommonResponse.successOf(
            AuthApiDto.Login.of(
                authService.loginUser(requestDto.toDomainDto())
            )
        )
    }

    @PostMapping("/partners/login")
    fun loginPartner(@RequestBody @Valid requestDto: AuthApiDto.LoginPartner): CommonResponse<AuthApiDto.Login> {
        return CommonResponse.successOf(
            AuthApiDto.Login.of(
                authService.loginPartner(requestDto.toDomainDto())
            )
        )
    }

    @PostMapping("/admins/login")
    fun loginAdmin(@RequestBody @Valid requestDto: AuthApiDto.LoginAdmin): CommonResponse<AuthApiDto.Login> {
        return CommonResponse.successOf(
            AuthApiDto.Login.of(
                authService.loginAdmin(requestDto.toDomainDto())
            )
        )
    }

}