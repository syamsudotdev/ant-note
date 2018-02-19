package net.mnsam.antnote.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.mnsam.antnote.feature.create.CreateActivity
import net.mnsam.antnote.feature.detail.DetailNoteActivity
import net.mnsam.antnote.feature.list.Activity

/**
 * Created by Mochamad Noor Syamsu on 1/17/18.
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityInjector(): Activity

    @ContributesAndroidInjector
    internal abstract fun contributeCreateActivityInjector(): CreateActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailNoteActivityInjector(): DetailNoteActivity
}