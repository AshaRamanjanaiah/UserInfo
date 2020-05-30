package com.userinfo.au.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.userinfo.au.model.Album
import com.userinfo.au.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AlbumViewModel(private val userId: Int): ViewModel() {

    private val _albumList by lazy { MutableLiveData<List<Album>>() }
    val albumList: LiveData<List<Album>> get() = _albumList

    private val _loading by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> get() = _loading

    private val _loadError by lazy { MutableLiveData<Boolean>() }
    val loadError: LiveData<Boolean> get() = _loadError

    private val _title by lazy { MutableLiveData<Int>() }
    val title: LiveData<Int> get() = _title

    private val disposable = CompositeDisposable()

    init {
        refresh()
        _loading.value = true
    }

    fun refresh() {
        getAlbum()
        _loadError.value = false
        _title.value = userId
    }

    /**
     * This method gets user info from network
     */
    fun getAlbum() {
        disposable.add(
            ApiService.retrofitService.getAlbumInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Album>>() {
                    override fun onSuccess(newAlbumList: List<Album>) {
                        getUserAlbums(newAlbumList)
                        _loading.value = false
                        _loadError.value = false

                    }

                    override fun onError(e: Throwable) {
                        if (!_albumList.value.isNullOrEmpty()) {
                            _loadError.value = false
                        } else {
                            _albumList.value = listOf()
                            _loadError.value = true
                        }
                        _loading.value = false
                    }

                })
        )
    }

    /**
     * Clearing disposable, it might cause memory leaks if not cleared.
     * @param newAlbumList fresh album list
     * @return album list depending on user id( if album id = user id)
     */

    private fun getUserAlbums(newAlbumList: List<Album>) {
        val userAlbumsList = mutableListOf<Album>()
        for (album in newAlbumList) {
            if (album.albumId == userId) {
                userAlbumsList.add(album)
            }
        }
        _albumList.value = userAlbumsList
    }

    /**
     * Clearing disposable, it might cause memory leaks if not cleared.
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}