package com.app.sample.manager.error

class NetworkingError(var errorType: ErrorType, var message: String = "") {
    enum class ErrorType {
        NO_INTERNET, UNEXPECTED_RESPONSE_CODE, FAILED_REQUEST
    }
}