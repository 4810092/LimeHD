package gka.android.limehd.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import gka.android.limehd.ui.channels.ChannelsFragment

class ChannelsPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private var pages = listOf(ChannelsFragment(false), ChannelsFragment(true))

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }
}