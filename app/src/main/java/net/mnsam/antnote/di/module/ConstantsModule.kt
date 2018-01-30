package net.mnsam.antnote.di.module

import dagger.Module
import dagger.Provides
import net.mnsam.antnote.util.Constants
import javax.inject.Singleton

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
@Module
class ConstantsModule {
    @Provides
    @Singleton
    fun provideConstantsInstance() = Constants()
}