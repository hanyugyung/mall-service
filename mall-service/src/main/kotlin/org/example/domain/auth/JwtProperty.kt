package org.example.domain.auth

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("auth.token")
class JwtProperty(
    val expireMs: Long
    , val issuer: String
    , val secretKey: String
)