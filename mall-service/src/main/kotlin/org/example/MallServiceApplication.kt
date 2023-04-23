package org.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MallServiceApplication {
    fun main(args: Array<String>) {
        runApplication<MallServiceApplication>(*args)
    }
}

