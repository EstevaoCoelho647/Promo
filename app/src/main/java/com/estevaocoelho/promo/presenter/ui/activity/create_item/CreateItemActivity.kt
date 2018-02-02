package com.estevaocoelho.promo.presenter.ui.activity.create_item

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.mvp.BaseMvpActivity
import com.estevaocoelho.promo.presenter.component.DialogImageOption
import com.estevaocoelho.promo.util.PARCELABLE_ITEM
import com.estevaocoelho.promo.util.PicassoUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_create_item.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.io.File

/**
 * Created by estevaocoelho on 05/12/17.
 */
class CreateItemActivity : BaseMvpActivity<CreateItemContract.View, CreateItemContract.Presenter>(), CreateItemContract.View {
    override var mPresenter: CreateItemContract.Presenter = CreateItemPresenter()
    lateinit var mItem: Item

    private lateinit var dialogImageOption: DialogImageOption

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_item)

        val extras = intent.extras
        if (extras != null) {
            mItem = extras.getParcelable(PARCELABLE_ITEM)
        } else
            mItem = Item()

        dialogImageOption = DialogImageOption(context = this, callback = mPresenter)

        itemImageView.setOnClickListener({
            dialogImageOption.show()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> createItem()
        }
        return false
    }

    private fun createItem() {
        mItem.price = editTextItemPrice.text.toString().toDouble()
        mItem.description = textViewItemTitle.text.toString()

        mPresenter.saveItem(mItem)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mPresenter.activityResult(requestCode, resultCode, data)
    }

    override fun showSelectedImage(imageUri: File) {
        mItem.imageURL = imageUri.absolutePath
        cancelImageDialog()
        PicassoUtil().setImageWithPicasso(this, itemImageView, imageUri)
    }

    override fun close() {
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu);
        return true
    }

    override fun showImageDialog() {
        dialogImageOption.show()
    }

    override fun cancelImageDialog() {
        dialogImageOption.cancel()
    }

}