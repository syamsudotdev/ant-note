package net.mnsam.antnote

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.mnsam.antnote.di.component.AppComponent
import javax.inject.Inject

/**
 * Created by Mochamad Noor Syamsu on 12/29/17.
 */
class ApplicationBase : Application(), HasActivityInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}