package com.userinfo.au.network

import com.userinfo.au.model.Album
import com.userinfo.au.model.User
import io.reactivex.Single
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("users")
    fun getUserInfo(): Single<List<User>>

    @GET("/photos")
    fun getAlbumInfo(): Single<List<Album>>

}