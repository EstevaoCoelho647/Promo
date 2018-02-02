package com.estevaocoelho.promo.presenter.ui.activity.dash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.estevaocoelho.promo.R
import com.estevaocoelho.promo.mvp.BaseMvpActivity
import com.estevaocoelho.promo.presenter.component.PageAdapter
import com.estevaocoelho.promo.presenter.ui.activity.create_item.CreateItemActivity
import com.estevaocoelho.promo.presenter.ui.activity.create_item.DashboardContract
import com.estevaocoelho.promo.presenter.ui.activity.create_item.DashboardPresenter
import kotlinx.android.synthetic.main.activity_dash.*

class DashActivity : BaseMvpActivity<DashboardContract.View, DashboardContract.Presenter>(), DashboardContract.View {

    override var mPresenter: DashboardContract.Presenter = DashboardPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            if (result != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        1)
            }


            var mSectionsPagerAdapter = PageAdapter(supportFragmentManager)
            viewPager.adapter = mSectionsPagerAdapter
            tabLayout.setupWithViewPager(viewPager)
            tabLayout.getTabAt(0)!!.setIcon(R.drawable.selector_ic_dashboard)
            tabLayout.getTabAt(1)!!.setIcon(R.drawable.selector_ic_whatshot)


            fab.setOnClickListener({
                val intent = Intent(this, CreateItemActivity::class.java)
                startActivity(intent)
            })
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}