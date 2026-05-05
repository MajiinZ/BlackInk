package com.mz.blackink

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform