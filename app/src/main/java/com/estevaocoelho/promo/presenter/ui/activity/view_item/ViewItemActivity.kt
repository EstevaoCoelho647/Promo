package com.estevaocoelho.promo.presenter.ui.activity.view_item

import android.os.Bundle
import android.util.Log
import com.estevaocoelho.localfilemanagerlib.FileManager
import com.estevaocoelho.localfilemanagerlib.OnFileDownloadCallback
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.mvp.BaseMvpActivity
import com.estevaocoelho.promo.util.PARCELABLE_ITEM
import com.estevaocoelho.promo.util.PATH_ITEM_IMAGES
import com.estevaocoelho.promo.util.PicassoUtil
import kotlinx.android.synthetic.main.activity_view_item.*
import java.io.File

/**
 * Created by estevaocoelho on 26/12/17.
 */
class ViewItemActivity : BaseMvpActivity<ViewItemContract.View, ViewItemContract.Presenter>(), ViewItemContract.View {
    override var mPresenter: ViewItemContract.Presenter = ViewItemPresenter()

    lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_item)

        val extras = intent.extras
        if (extras != null) {
            item = extras.getParcelable<Item>(PARCELABLE_ITEM)
            mPresenter.addNewVisualization(item.id!!)
            bindItemData()
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = ""
    }

    private fun bindItemData() {
        FileManager.getFileManagerInstance().getFileFromLocalOrDownload(
                item.imageURL,
                item.id + ".jpg",
                PATH_ITEM_IMAGES,
                object : OnFileDownloadCallback {
                    override fun onItemDownloadError() {
                        Log.e("OnItemDownloadError", "error")
                    }

                    override fun onItemDownloaded(file: File?) {
                        PicassoUtil().setImageWithPicasso(getActivity(), placeImage, file)
                    }
                }
        )

    }
}