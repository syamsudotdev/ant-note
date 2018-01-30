package net.mnsam.antnote.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.mnsam.antnote.ApplicationBase
import net.mnsam.antnote.di.module.*
import javax.inject.Singleton

/**
 * Created by Mochamad Noor Syamsu on 1/17/18.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    ConstantsModule::class,
    DbModule::class,
    PresenterModule::class
])
interface AppComponent : AndroidInjector<ApplicationBase> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}