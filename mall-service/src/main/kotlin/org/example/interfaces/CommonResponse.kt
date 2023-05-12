package org.example.interfaces

import org.springframework.http.HttpStatus

class CommonResponse<T>(
    val result: ResultStatus
    , var data: T? = null
    , val status: HttpStatus
) {

    companion object {
        fun <T> of(result: ResultStatus, data: T, status: HttpStatus): CommonResponse<T> {
            return CommonResponse(result, data, status)
        }

        fun of(result: ResultStatus, status: HttpStatus): CommonResponse<*> {
            return CommonResponse(result, null, status)
        }
    }

    enum class ResultStatus {
        SUCCESS, FAIL
    }
}