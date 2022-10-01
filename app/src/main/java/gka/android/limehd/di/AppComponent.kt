package gka.android.limehd.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import gka.android.limehd.di.viewmodel.ViewModelFactory
import gka.android.limehd.di.viewmodel.ViewModelModule
import gka.android.limehd.ui.channels.ChannelsFragment
import gka.android.limehd.ui.main.MainActivity

@AppScope
@Component(modules = [ViewModelModule::class, DataModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(channelsFragment: ChannelsFragment)
    fun viewModelsFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Context): AppComponent
    }
}