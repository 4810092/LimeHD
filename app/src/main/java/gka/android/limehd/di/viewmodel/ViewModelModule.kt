package gka.android.limehd.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import gka.android.limehd.ui.main.MainViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}