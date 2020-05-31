package com.userinfo.au.di

import com.userinfo.au.viewmodel.UserListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: UserListViewModel)
}