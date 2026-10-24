package com.mz.blackink.di

import com.mz.blackink.data.InMemoryConversationRepository
import com.mz.blackink.data.domain.ConversationRepository
import com.mz.blackink.feature.home.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<ConversationRepository> { InMemoryConversationRepository() }
    viewModelOf(::HomeViewModel)
}

fun initializeKoin(config: KoinApplication.() -> Unit = {}) = startKoin {
    config()
    modules(appModule)
}
