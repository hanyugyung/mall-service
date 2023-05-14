package org.example.domain.auth

enum class UseRole(
    private val description: String
) {
    CUSTOMER("일반 사용자")
    , ADMIN("관리자")
    , PARTNER("파트너")
}