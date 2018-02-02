package com.estevaocoelho.promo.presenter.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.model.repository.ItemRepository
import com.estevaocoelho.promo.presenter.ui.activity.dash.adapter.ItemListAdapter
import com.estevaocoelho.promo.presenter.ui.activity.view_item.ViewItemActivity
import com.estevaocoelho.promo.util.PARCELABLE_ITEM
import kotlinx.android.synthetic.main.fragment_all_items.view.*

/**
 * Created by estevaocoelho on 04/12/17.
 */
class AllListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_all_items, container, false)

        val itemListAdapter = ItemListAdapter(this)
        rootView.recyclerViewAllItems.layoutManager = LinearLayoutManager(activity)
        rootView.recyclerViewAllItems.adapter = itemListAdapter

        ItemRepository().getAllItems(object : ItemRepository.OnGetItems {
            override fun onGetItemsSuccess(list: MutableList<Item>) {
                itemListAdapter.setList(list, true)
            }

            override fun onGetItemsFailure() {
            }
        })

        return rootView
    }

    fun onItemClick(item: Item) {
        val intent = Intent(
                activity,
                ViewItemActivity::class.java)
        intent.putExtra(PARCELABLE_ITEM, item)
        activity.startActivity(intent)
    }
}