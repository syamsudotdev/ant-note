package net.mnsam.antnote.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.mnsam.antnote.feature.list.MainActivity

/**
 * Created by Mochamad Noor Syamsu on 1/17/18.
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityInjector(): MainActivity
}