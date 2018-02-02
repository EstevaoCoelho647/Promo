package com.estevaocoelho.promo.util

import android.content.Context
import android.media.Image
import android.widget.ImageView
import com.estevaocoelho.promo.R
import com.squareup.picasso.Picasso
import me.shaohui.advancedluban.Luban
import me.shaohui.advancedluban.OnCompressListener
import java.io.File

/**
 * Created by estevaocoelho on 13/12/17.
 */
class PicassoUtil {
    fun setImageWithPicasso(context: Context, imageView: ImageView, file: File?) {
        Picasso.with(context).load(file).fit().centerCrop().error(R.drawable.ic_image_placeholder).into(imageView)
    }
}