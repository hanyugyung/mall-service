package org.example.domain.user

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserServiceImpl : UserService {
    override fun signUpUser(dto: UserCommand.SignUpUser) {
        var user = dto.toEntity() // TODO password 암호화
        // TODO 영속화
    }
}