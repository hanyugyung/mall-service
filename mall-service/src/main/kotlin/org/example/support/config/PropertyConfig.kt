package org.example.support.config

import org.example.domain.auth.JwtProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperty::class)
class PropertyConfig {
}