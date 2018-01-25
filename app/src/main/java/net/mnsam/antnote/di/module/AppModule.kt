package net.mnsam.antnote.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import net.mnsam.antnote.di.AppContext

/**
 * Created by Mochamad Noor Syamsu on 1/17/18.
 */
@Module
class AppModule {
    @Provides
    @AppContext
    internal fun provideContext(application: Application): Context = application.applicationContext
}