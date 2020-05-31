package com.userinfo.au

import com.userinfo.au.di.ApiModule
import com.userinfo.au.network.ApiService

class ApiModuleTest(val mockApiService: ApiService): ApiModule() {
    override fun provideApiService(): ApiService {
        return mockApiService
    }
}