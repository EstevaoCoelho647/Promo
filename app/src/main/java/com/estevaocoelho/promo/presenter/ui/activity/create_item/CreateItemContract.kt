package com.estevaocoelho.promo.presenter.ui.activity.create_item

import android.content.Intent
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.mvp.BaseMvpPresenter
import com.estevaocoelho.promo.mvp.BaseMvpView
import java.io.File

/**
 * Created by estevaocoelho on 05/12/17.
 */
object CreateItemContract {

    interface View : BaseMvpView {
        fun cancelImageDialog()
        fun showImageDialog()
        fun showSelectedImage(imageUri: File)
        fun close()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun onCameraSelect()
        fun onGallerySelect()
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
        fun activityResult(requestCode: Int, resultCode: Int, data: Intent?)
        fun saveItem(mItem: Item)
        fun onItemSaveSuccess()
        fun onItemSaveFailure()
    }
}