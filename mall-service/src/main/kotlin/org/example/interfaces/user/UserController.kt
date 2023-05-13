package org.example.interfaces.user;

import jakarta.validation.Valid
import org.example.domain.user.UserService
import org.example.interfaces.CommonResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @PostMapping("/sign-up")
    fun signUpUser(@RequestBody @Valid requestDto: UserApiDto.SignUpUser): CommonResponse<*> {
        return CommonResponse.of(
            CommonResponse.ResultStatus.SUCCESS
            , HttpStatus.OK
            , userService.signUpUser(requestDto.toDomainDto())
        )
    }

}
