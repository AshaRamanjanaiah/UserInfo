package com.userinfo.au

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.userinfo.au.di.DaggerViewModelComponent
import com.userinfo.au.model.User
import com.userinfo.au.network.ApiService
import com.userinfo.au.viewmodel.UserListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class UserListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    val userListViewModel by lazy { UserListViewModel(true) }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        DaggerViewModelComponent.builder()
            .apiModule(ApiModuleTest(apiService))
            .build()
            .inject(userListViewModel)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

    @Test
    fun getInfoSuccess() {
        val user1 = User(1, "Test name1", "test1@test.com", "12342424342")
        val user2 = User(2, "Test name2", "test2@test.com", "43534535")
        val userList = listOf(user1, user2)

        val testSingle = Single.just(userList)
        Mockito.`when`(apiService.getUsers()).thenReturn(testSingle)

        userListViewModel.refresh()

        Assert.assertEquals(2, userListViewModel.userList.value?.size)
        Assert.assertEquals(userList, userListViewModel.userList.value)
        Assert.assertEquals(false, userListViewModel.loadError.value)
        Assert.assertEquals(false,userListViewModel.loading.value)
    }

    @Test
    fun getInfoFailure() {
        val testSingleError = Single.error<List<User>>(Throwable())
        Mockito.`when`(apiService.getUsers()).thenReturn(testSingleError)

        userListViewModel.refresh()

        Assert.assertNotNull(userListViewModel.userList.value)
        Assert.assertEquals(false, userListViewModel.loadError.value)
        Assert.assertEquals(false,userListViewModel.loading.value)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        RxAndroidPlugins.reset()
        RxAndroidPlugins.reset()
    }
}