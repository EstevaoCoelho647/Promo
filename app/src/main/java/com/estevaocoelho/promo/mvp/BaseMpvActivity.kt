package com.estevaocoelho.promo.mvp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

/**
 * Created by estevaocoelho on 04/12/17.
 */
abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : AppCompatActivity(), BaseMvpView {

    protected abstract var mPresenter: T

    override fun getActivity(): Activity = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun showError(error: String) {
        Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_SHORT).show()
    }

    override fun showError(stringResId: Int) {
        Snackbar.make(findViewById(android.R.id.content), stringResId, Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(srtResId: Int) {
        Snackbar.make(findViewById(android.R.id.content), srtResId, Snackbar.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}