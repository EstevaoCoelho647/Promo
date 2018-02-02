package com.estevaocoelho.promo.presenter.component

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.estevaocoelho.promo.presenter.ui.fragment.AllListFragment
import com.estevaocoelho.promo.presenter.ui.fragment.HotListFragment


/**
 * Created by estevaocoelho on 04/12/17.
 */
class PageAdapter(supportFragmentManager: FragmentManager?) : FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                AllListFragment()
            }
            1 -> {
                HotListFragment()
            }
            else -> null
        }
    }


    override fun getCount(): Int {
        return 2
    }

}