package com.fernandohbrasil.stackexchange.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.stackexchange.RxImmediateSchedulerRule
import com.fernandohbrasil.stackexchange.network.model.Badges
import com.fernandohbrasil.stackexchange.network.model.User
import com.fernandohbrasil.stackexchange.network.model.Users
import com.fernandohbrasil.stackexchange.repository.UsersRepository
import io.reactivex.Flowable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


const val EXCEPTION = "Exception"
class ListUsersViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: UsersRepository

    private lateinit var viewModel: ListUsersViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ListUsersViewModel(repository)
    }

    @Test
    fun usersSuccess() {
        val user = User(0, "", Badges(0, 0, 0), 0L, "", "", 0)
        val users = Users((listOf(user)))

        Mockito.`when`(repository.getUsers(""))
            .thenReturn(Flowable.just(users))

        viewModel.getUsers("")

        Assert.assertEquals(
            UsersSuccess(users),
            viewModel.usersState.value
        )
    }

    @Test
    fun usersError() {
        Mockito.`when`(repository.getUsers(""))
            .thenReturn(Flowable.error(Throwable(EXCEPTION)))

        viewModel.getUsers("")

        Assert.assertEquals(
            UsersError(EXCEPTION),
            viewModel.usersState.value
        )
    }

}