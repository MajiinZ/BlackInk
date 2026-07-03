package com.mz.designsystem

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform