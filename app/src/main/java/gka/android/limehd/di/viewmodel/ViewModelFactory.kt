package gka.android.limehd.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gka.android.limehd.ui.main.MainViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(myViewModelProvider: Provider<MainViewModel>) :
    ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        MainViewModel::class.java to myViewModelProvider
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}
