package com.estevaocoelho.promo.presenter.ui.activity.view_item

import com.estevaocoelho.promo.model.repository.ItemRepository
import com.estevaocoelho.promo.mvp.BaseMvpPresenterImpl

/**
 * Created by estevaocoelho on 05/12/17.
 */
class ViewItemPresenter : BaseMvpPresenterImpl<ViewItemContract.View>(), ViewItemContract.Presenter {
    override fun addNewVisualization(id: String) {
        ItemRepository().addNewVisualization(id, this)
    }

}