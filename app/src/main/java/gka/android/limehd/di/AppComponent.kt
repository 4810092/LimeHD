package gka.android.limehd.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import gka.android.limehd.di.viewmodel.ViewModelModule
import gka.android.limehd.ui.channels.ChannelsFragment
import gka.android.limehd.ui.main.MainActivity

@Component(modules = [ViewModelModule::class, DataModule::class])
@AppScope
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(channelsFragment: ChannelsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}