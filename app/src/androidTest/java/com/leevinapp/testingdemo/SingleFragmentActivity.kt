package com.leevinapp.testingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.leevinapp.testingdemo.common.ActivityInjector
import com.leevinapp.testingdemo.common.FragmentInjector
import com.leevinapp.testingdemo.di.ActivityComponent
import com.leevinapp.testingdemo.di.ActivityComponentProvider
import javax.inject.Inject

// TODO: 2021/9/18 how to decouple the single fragment testing from activity due to DI dependencies
class SingleFragmentActivity : AppCompatActivity(), ActivityComponentProvider, FragmentInjector {

    @Inject
    lateinit var fragmentInjector: FragmentInjector

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (application as ActivityInjector).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment::class.java.canonicalName)
            .commitNow()
    }

    override fun inject(fragment: Fragment) {
        fragmentInjector.inject(fragment)
    }

    override val component: ActivityComponent by lazy {
        activityComponent
    }
}
