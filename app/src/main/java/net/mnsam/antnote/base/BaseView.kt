package net.mnsam.antnote.base

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface BaseView {
    fun toastMessage(message: String)
    fun showLoading()
    fun hideLoading()
}