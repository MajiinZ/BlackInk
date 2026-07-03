package com.mz.domain

interface BlackInkLogger {
    fun info(message: String)
    fun warn(message: String)
    fun error(message: String, throwable: Throwable? = null)
    fun debug(message: String)

}
