package com.userinfo.au.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.userinfo.au.model.User
import com.userinfo.au.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UserListViewModel: ViewModel() {

    private val _userList by lazy { MutableLiveData<List<User>>() }
    val userList: LiveData<List<User>> get() = _userList

    private val _loading by lazy { MutableLiveData<Boolean>() }
    val loading: LiveData<Boolean> get() = _loading

    private val _loadError by lazy { MutableLiveData<Boolean>() }
    val loadError: LiveData<Boolean> get() = _loadError

    private val disposable = CompositeDisposable()

    init {
        refresh()
        _loading.value = true
    }

    fun refresh() {
        getUser()
        _loadError.value = false
    }

    /**
     * This method gets user info from network
     */
    fun getUser() {
        disposable.add(
            ApiService.retrofitService.getUserInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<User>>() {
                    override fun onSuccess(newUserList: List<User>) {
                        _userList.value = newUserList
                        _loading.value = false
                        _loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        if (!_userList.value.isNullOrEmpty()) {
                            _loadError.value = false
                        } else {
                            _userList.value = listOf()
                            _loadError.value = true
                        }
                        _loading.value = false
                    }

                })
        )
    }

    /**
     * Clearing disposable, it might cause memory leaks if not cleared.
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}