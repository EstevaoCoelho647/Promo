package com.estevaocoelho.promo.presenter.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.estevaocoelho.promo.R

/**
 * Created by estevaocoelho on 04/12/17.
 */
public class HotListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_hot_items, container, false)

        return rootView
    }
}