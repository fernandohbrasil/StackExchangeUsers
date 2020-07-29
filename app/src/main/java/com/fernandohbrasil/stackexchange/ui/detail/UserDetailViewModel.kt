package com.fernandohbrasil.stackexchange.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fernandohbrasil.stackexchange.network.model.User
import com.fernandohbrasil.stackexchange.repository.UsersRepository
import com.fernandohbrasil.stackexchange.ui.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseViewModel() {

    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState>
        get() = _userState

    fun getUserById(userId: Int) {
        disposable.add(
            usersRepository.getUserById(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _userState.postValue(UserStateSuccess(it.items[0])) },
                    { _userState.postValue(UserStateError(it.message)) }
                )
        )
    }

    fun start() = _userState.postValue(UserStateStarted)

    fun finish() = _userState.postValue(UserStateFinished)
}

sealed class UserState
object UserStateStarted : UserState()
object UserStateFinished : UserState()
data class UserStateSuccess(val user: User) : UserState()
data class UserStateError(val error: String?) : UserState()