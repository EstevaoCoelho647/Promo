package com.estevaocoelho.promo

import android.app.Application
import com.estevaocoelho.localfilemanagerlib.FileManager
import com.estevaocoelho.promo.util.ApplicationUtil

/**
 * Created by estevaocoelho on 07/12/17.
 */
class PromoApplication : Application() {

    override fun onCreate() {
        FileManager.create(this)
        ApplicationUtil.setContext(this)
        super.onCreate()
    }
}