package com.userinfo.au.network

import com.userinfo.au.di.DaggerApiComponent
import com.userinfo.au.model.Album
import com.userinfo.au.model.User
import io.reactivex.Single
import javax.inject.Inject

class ApiService {

    @Inject
    lateinit var api: ApiEndPoint

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getUsers(): Single<List<User>> {
        return api.getUserInfo()
    }

    fun getAlbums(): Single<List<Album>> {
        return api.getAlbumInfo()
    }
}