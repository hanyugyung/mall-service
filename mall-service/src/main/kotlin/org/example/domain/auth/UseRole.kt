package org.example.domain.auth

import org.example.domain.user.User

enum class UseRole(
    private val description: String
) {
    CUSTOMER("일반 사용자")
    , ADMIN("관리자")
    , PARTNER("파트너");

    fun of(role: User.Role): UseRole {
        return if(role == User.Role.CUSTOMER) {
            CUSTOMER
        } else ADMIN
    }
}