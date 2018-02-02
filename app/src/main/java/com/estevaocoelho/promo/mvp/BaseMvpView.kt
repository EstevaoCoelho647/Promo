package com.estevaocoelho.promo.mvp

import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by estevaocoelho on 04/12/17.
 */

interface BaseMvpView {

    fun getActivity(): Activity

    fun showError(error: String)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)

}