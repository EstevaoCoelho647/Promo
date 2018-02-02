package com.estevaocoelho.promo.presenter.ui.activity.dash.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.estevaocoelho.localfilemanagerlib.FileManager
import com.estevaocoelho.localfilemanagerlib.OnFileDownloadCallback
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.presenter.ui.fragment.AllListFragment
import com.estevaocoelho.promo.util.PATH_ITEM_IMAGES
import com.estevaocoelho.promo.util.PicassoUtil
import kotlinx.android.synthetic.main.base_bar_item_info.view.*
import kotlinx.android.synthetic.main.item_layout.view.*
import kotlinx.android.synthetic.main.item_price.view.*
import java.io.File
import java.text.DecimalFormat
import java.util.*

/**
 * Created by estevaocoelho on 07/12/17.
 */
class ItemListAdapter(var callback: AllListFragment) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    var itemList: MutableList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(inflate, callback)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(itemList[position])

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(list: List<Item>, clear: Boolean) {
        if (clear) {
            itemList.clear()
        }

        itemList.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, val callback: AllListFragment) : RecyclerView.ViewHolder(itemView) {
        fun bindData(item: Item) {
            with(item) {
                if (item.imageURL != "") {
                    FileManager.getFileManagerInstance().getFileFromLocalOrDownload(
                            item.imageURL,
                            item.id + ".jpg",
                            PATH_ITEM_IMAGES,
                            object : OnFileDownloadCallback {
                                override fun onItemDownloadError() {
                                    Log.e("OnItemDownloadError", "error")
                                }

                                override fun onItemDownloaded(file: File?) {
                                    PicassoUtil().setImageWithPicasso(itemView.context, itemView.itemImageView, file)
                                }
                            }
                    )
                }
                itemView.textViewItemTitle.text = description
                itemView.textViewItemPrice.text = price.toCurrency()
                itemView.textViewViewsNumber.text = views.toString()
                itemView.setOnClickListener({
                    callback.onItemClick(item)
                })
            }
        }
    }
}

private fun Double.toCurrency(): CharSequence? {
    val numberFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "BR"))
    return numberFormat.format(this)
}
