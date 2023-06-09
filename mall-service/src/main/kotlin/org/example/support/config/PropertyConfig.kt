package org.example.support.config

import jakarta.annotation.PostConstruct
import org.example.domain.auth.JwtProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(JwtProperty::class)
class PropertyConfig {

    // 값이 뭔지 테스트 하고 싶은 변수 찍기
    @Value("\${spring.jpa.open-in-view}")
    lateinit var test: String

    @PostConstruct
    fun testValue() {
        println("what is value: $test")
    }

}