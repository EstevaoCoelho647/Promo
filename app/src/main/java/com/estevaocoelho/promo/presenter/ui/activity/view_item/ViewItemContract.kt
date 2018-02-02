package com.estevaocoelho.promo.presenter.ui.activity.view_item

import com.estevaocoelho.promo.mvp.BaseMvpPresenter
import com.estevaocoelho.promo.mvp.BaseMvpView

/**
 * Created by estevaocoelho on 05/12/17.
 */
object ViewItemContract {

    interface View : BaseMvpView {


    }

    interface Presenter : BaseMvpPresenter<View> {
        fun addNewVisualization(id: String)
    }
}