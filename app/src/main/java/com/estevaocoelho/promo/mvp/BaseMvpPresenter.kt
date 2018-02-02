package com.estevaocoelho.promo.mvp

/**
 * Created by estevaocoelho on 04/12/17.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}