package org.example.interfaces

import org.springframework.http.HttpStatus

class CommonResponse<T>(
    val result: ResultStatus
    , val status: HttpStatus
    , var data: T? = null
) {

    companion object {
        fun <T> of(result: ResultStatus, status: HttpStatus, data: T): CommonResponse<T> {
            return CommonResponse(result, status, data)
        }

        fun of(result: ResultStatus, status: HttpStatus): CommonResponse<*> {
            return CommonResponse(result, status, null)
        }
    }

    enum class ResultStatus {
        SUCCESS, FAIL
    }
}