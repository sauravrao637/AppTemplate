package com.camo.template.util


data class Resource<out T>(val status: Status, val data: T?, val errorInfo: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, errorInfo = null)

        fun <T> error(data: T?, errorInfo: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, errorInfo = errorInfo)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = Status.LOADING, data = data, errorInfo = null)

        fun <T> loading(): Resource<T> =
            Resource(status = Status.LOADING, data = null, errorInfo = null)

        fun <T> idle(): Resource<T> = Resource(status = Status.IDLE, null, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    IDLE
}