package com.vungn.todoapp.ui.main.setting.contract.implement

import android.app.Application
import androidx.lifecycle.*
import com.vungn.todoapp.data.repository.ConfigManager
import com.vungn.todoapp.ui.main.setting.contract.SettingViewModel
import com.vungn.todoapp.usecase.home.LoadInfoUserUseCase
import com.vungn.todoapp.usecase.setting.LogOutUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModelImpl @Inject constructor(
    application: Application,
    private val configManager: ConfigManager,
    private val loadInfoUserUseCase: LoadInfoUserUseCase,
    private val logOutUserUseCase: LogOutUserUseCase
) : AndroidViewModel(application),
    SettingViewModel {

    private val mutableAvartar: MutableLiveData<String> = MutableLiveData()
    override val avatar: LiveData<String>
        get() = mutableAvartar

    private val mutableName: MutableLiveData<String> = MutableLiveData()
    override val name: LiveData<String>
        get() = mutableName

    private val mutableEmail: MutableLiveData<String> = MutableLiveData()
    override val email: LiveData<String>
        get() = mutableEmail

    override fun logout() {
        viewModelScope.launch {
            logOutUserUseCase.DeleteUserInSharePreferences()
        }
        configManager.setLoggedIn(false)
    }

    override fun loadUser() {
        viewModelScope.launch {
            val user = loadInfoUserUseCase.getUserFromJson()
            mutableName.postValue(user.name)
            mutableAvartar.postValue(user.avatar)
            mutableEmail.postValue(user.email)
        }
    }


}