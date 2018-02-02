package com.estevaocoelho.promo.presenter.ui.activity.create_item

import android.content.Intent
import com.estevaocoelho.promo.model.entity.Item
import com.estevaocoelho.promo.mvp.BaseMvpPresenter
import com.estevaocoelho.promo.mvp.BaseMvpView
import java.io.File

/**
 * Created by estevaocoelho on 05/12/17.
 */
object DashboardContract {

    interface View : BaseMvpView {


    }

    interface Presenter : BaseMvpPresenter<View> {

    }
}