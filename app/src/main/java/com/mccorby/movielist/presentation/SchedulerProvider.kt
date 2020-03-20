package com.mccorby.movielist.presentation

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulerProvider {
    fun getIoScheduler() = Schedulers.io()
    fun getMainScheduler() = AndroidSchedulers.mainThread()
}
