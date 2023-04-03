package com.example.demokmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
