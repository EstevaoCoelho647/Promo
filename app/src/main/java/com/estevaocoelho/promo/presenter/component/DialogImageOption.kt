package com.estevaocoelho.promo.presenter.component

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.presenter.ui.activity.create_item.CreateItemContract
import kotlinx.android.synthetic.main.dialog_image_options.*

/**
 * Created by estevaocoelho on 06/12/17.
 */
class DialogImageOption(context: Context, private val callback: CreateItemContract.Presenter) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        val view = View.inflate(context, R.layout.dialog_image_options, null)
        setContentView(view)

        actionCamera.setOnClickListener {
            callback.onCameraSelect()
        }
        actionGallery.setOnClickListener {
            callback.onGallerySelect()
        }
    }
}