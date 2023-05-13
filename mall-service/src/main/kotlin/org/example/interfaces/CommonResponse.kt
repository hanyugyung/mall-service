package org.example.interfaces

import org.springframework.http.HttpStatus

class CommonResponse<T>(
    val result: ResultStatus
    , val status: Int
    , val message: String
    , var data: T? = null
) {

    companion object {

        fun <T> successOf(data: T): CommonResponse<T> {
            return CommonResponse(
                result = ResultStatus.SUCCESS
                , status = 200
                , message = "success"
                , data)
        }

        fun successOf(): CommonResponse<*> {
            return CommonResponse(
                result = ResultStatus.SUCCESS
                , status = 200
                , message = "success"
                , data = null)
        }

        fun successOf(exception: RuntimeException, status: HttpStatus): CommonResponse<*> {
            return CommonResponse(
                result = ResultStatus.SUCCESS
                , status = status.value()
                , message = exception.message ?: exception.localizedMessage
                , data = null)
        }

        fun failOf(exception: Exception): CommonResponse<*> {
            return CommonResponse(
                result = ResultStatus.FAIL
                , status = 500
                , message = exception.message ?: exception.localizedMessage
                , data = null)
        }
    }

    enum class ResultStatus {
        SUCCESS, FAIL
    }
}