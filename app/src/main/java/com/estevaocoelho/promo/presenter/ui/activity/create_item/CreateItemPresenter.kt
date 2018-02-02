package com.estevaocoelho.promo.presenter.ui.activity.create_item

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.model.repository.ItemRepository
import com.estevaocoelho.promo.mvp.BaseMvpPresenterImpl
import com.estevaocoelho.promo.util.PERMISSION_WRITE_EXTERNAL
import com.estevaocoelho.promo.util.SELECT_CAMERA
import com.estevaocoelho.promo.util.SELECT_PICTURE
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import java.io.File

/**
 * Created by estevaocoelho on 05/12/17.
 */
class CreateItemPresenter : BaseMvpPresenterImpl<CreateItemContract.View>(), CreateItemContract.Presenter {

    override fun onItemSaveSuccess() {
        mView!!.showMessage(R.string.item_save_success)
        mView!!.close()
    }

    override fun onItemSaveFailure() {
        mView!!.showError(R.string.item_save_error)
    }

    override fun saveItem(mItem: Item) {
        ItemRepository().saveItem(mItem, this)
    }

    override fun activityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        EasyImage.handleActivityResult(requestCode, resultCode, data, mView?.getActivity(), object : DefaultCallback() {
            override fun onImagePickerError(e: Exception?, source: EasyImage.ImageSource?, type: Int) {
                e!!.printStackTrace()
            }

            override fun onImagePicked(imageFile: File, source: EasyImage.ImageSource, type: Int) {
                mView?.showSelectedImage(imageFile)
            }

            override fun onCanceled(source: EasyImage.ImageSource?, type: Int) {}
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_WRITE_EXTERNAL -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    EasyImage.openCamera(mView!!.getActivity(), SELECT_CAMERA)
                } else {
                    mView?.showMessage(R.string.camera_permission_message)
                }
            }
        }
    }

    override fun onCameraSelect() {
        if (ContextCompat.checkSelfPermission(mView!!.getActivity(),
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mView!!.getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mView!!.getActivity(),
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_WRITE_EXTERNAL)
        } else {
            EasyImage.openCamera(mView?.getActivity(), SELECT_CAMERA)

        }
        mView?.cancelImageDialog()
    }

    override fun onGallerySelect() {
        EasyImage.openGallery(mView?.getActivity(), SELECT_PICTURE)
    }
}