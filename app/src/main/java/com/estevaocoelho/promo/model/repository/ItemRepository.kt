package com.estevaocoelho.promo.model.repository

import android.net.Uri
import android.util.Log
import android.widget.NumberPicker
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.presenter.ui.activity.create_item.CreateItemContract
import com.estevaocoelho.promo.presenter.ui.activity.view_item.ViewItemPresenter
import com.estevaocoelho.promo.util.DATABASE_ITEM_PATH
import com.google.firebase.database.*

/**
 * Created by estevaocoelho on 07/12/17.
 */
class ItemRepository {
    val instance: FirebaseDatabase

    constructor() {
        instance = FirebaseDatabase.getInstance()
    }


    fun saveItem(item: Item, createItemPresenter: CreateItemContract.Presenter) {
        val push = instance.reference.child(DATABASE_ITEM_PATH).push()
        if (item.imageURL != "") {
            StorageRepository().saveImageOnStorage(
                    item.imageURL, push.key, object : StorageRepository.OnImageSaveListener {
                override fun onSuccess(url: Uri) {
                    item.imageURL = url.toString()
                    saveItemOnDatabase(item, push, createItemPresenter)
                }

                override fun onError() {

                }
            })
        } else saveItemOnDatabase(item, push, createItemPresenter)
    }

    private fun saveItemOnDatabase(item: Item, push: DatabaseReference, createItemPresenter: CreateItemContract.Presenter) {
        push.setValue(item).addOnCompleteListener({ task ->
            if (task.isSuccessful) {
                createItemPresenter.onItemSaveSuccess()
            } else
                createItemPresenter.onItemSaveFailure()
        })
    }

    fun getAllItems(onGetItems: OnGetItems) {
        instance.reference.child(DATABASE_ITEM_PATH).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                Log.e("getAllItemsError", p0?.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot?) {
                val children = dataSnapshot?.children

                var list: MutableList<Item> = ArrayList()

                children?.forEach { child ->
                    val item = child.getValue(Item::class.java)
                    item?.id = child.key
                    if (item != null) {
                        list.add(item)
                    }

                }
                onGetItems.onGetItemsSuccess(list)
            }
        })
    }


    fun addNewVisualization(id: String, viewItemPresenter: ViewItemPresenter) {
        instance.reference.child(id).child("views").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                var value = p0?.value
                if (value != null) {
                    value = value as Int
                    instance.reference.child(id).child("views").setValue(value + 1)
                } else
                    instance.reference.child(id).child("views").setValue(1)

            }
        })
    }

    interface OnGetItems {
        fun onGetItemsSuccess(list: MutableList<Item>)
        fun onGetItemsFailure()

    }

}