package com.fernandohbrasil.stackexchange.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fernandohbrasil.stackexchange.RxImmediateSchedulerRule
import com.fernandohbrasil.stackexchange.network.model.Badges
import com.fernandohbrasil.stackexchange.network.model.User
import com.fernandohbrasil.stackexchange.network.model.Users
import com.fernandohbrasil.stackexchange.repository.UsersRepository
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

const val EXCEPTION = "Exception"

class UserDetailViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val ruleForLivaData = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: UsersRepository

    private lateinit var viewModel: UserDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserDetailViewModel(repository)
    }

    @Test
    fun userStateStarted() {
        viewModel.start()
        assertEquals(UserStateStarted, viewModel.userState.value)
    }

    @Test
    fun userStateFinished() {
        viewModel.finish()
        assertEquals(UserStateFinished, viewModel.userState.value)
    }


    @Test
    fun userStateSuccess() {
        val user = User(0, "", Badges(0, 0, 0), 0L, "", "", 0)
        val users = Users((listOf(user)))

        Mockito.`when`(repository.getUserById(0))
            .thenReturn(Single.just(users))

        viewModel.getUserById(0)

        assertEquals(
            UserStateSuccess(user),
            viewModel.userState.value
        )
    }

    @Test
    fun characterApiError() {
        val user = User(0, "", Badges(0, 0, 0), 0L, "", "", 0)

        Mockito.`when`(repository.getUserById(userId = user.id))
            .thenReturn(Single.error(Throwable(EXCEPTION)))

        viewModel.getUserById(user.id)

        assertEquals(
            UserStateError(EXCEPTION),
            viewModel.userState.value
        )
    }
}