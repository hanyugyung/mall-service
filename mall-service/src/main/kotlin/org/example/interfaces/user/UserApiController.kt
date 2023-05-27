package org.example.interfaces.user;

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.example.domain.auth.AuthService
import org.example.domain.user.UserService
import org.example.interfaces.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserApiController @Autowired constructor(
    private val userService: UserService, private val authService: AuthService
) {

    @Operation(summary = "회원가입 요청")
    @PostMapping("/sign-up")
    fun signUpUser(@RequestBody @Valid requestDto: UserApiDto.SignUpUserRequest): CommonResponse<UserApiDto.SignUpUserResponse> {

        return CommonResponse.successOf(
            UserApiDto.SignUpUserResponse.of(
                userService.signUpUser(requestDto.toDomainDto())
            )
        )
    }

    @Operation(summary = "사용자 로그인")
    @PostMapping("/login")
    fun loginUser(@RequestBody @Valid requestDto: UserApiDto.LoginUserRequest): CommonResponse<UserApiDto.LoginUserResponse> {
        return CommonResponse.successOf(
            UserApiDto.LoginUserResponse.of(
                authService.loginUser(requestDto.toDomainDto())
            )
        )
    }
}
