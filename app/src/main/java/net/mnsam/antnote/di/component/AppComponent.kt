package net.mnsam.antnote.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import net.mnsam.antnote.ApplicationBase
import net.mnsam.antnote.di.module.ActivityBuilder
import net.mnsam.antnote.di.module.AppModule
import net.mnsam.antnote.di.module.DbModule
import javax.inject.Singleton

/**
 * Created by Mochamad Noor Syamsu on 1/17/18.
 */
@Singleton
@Component(modules = [
    AppModule::class,
    ActivityBuilder::class,
    DbModule::class
])
interface AppComponent : AndroidInjector<ApplicationBase> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}