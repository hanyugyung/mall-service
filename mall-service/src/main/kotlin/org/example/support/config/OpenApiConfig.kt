package org.example.support.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.example.domain.auth.JwtProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@OpenAPIDefinition(info = Info(title = "명세서"))
@EnableWebMvc
@Configuration
class OpenApiConfig {

    @Autowired
    lateinit var jwtProperty: JwtProperty

    @Bean
    fun openAPI(): OpenAPI {
        val jwt = jwtProperty.header
        val securityRequirement: SecurityRequirement = SecurityRequirement().addList(jwt) // 헤더에 토큰 포함
        val components: Components = Components().addSecuritySchemes(
            jwt, SecurityScheme()
                .name(jwt)
                .`in`(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.APIKEY)
        )
        return OpenAPI()
            .addSecurityItem(securityRequirement)
            .components(components)
    }
}