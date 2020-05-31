package com.userinfo.au.di

import com.userinfo.au.network.ApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(apiEndPoint: ApiService)
}