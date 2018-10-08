package com.xtagwgj.jpackdemo.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.xtagwgj.jpackdemo.db.AppDb

class DemoViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        private const val PAGE_SIZE = 15
        private const val ENABLE_PLACEHOLDERS = false
    }

    val dao = AppDb.getInstance(app).testDao()

    val pageListConfig = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .build()

    val allDemos = LivePagedListBuilder(dao.listTest(), pageListConfig).build()
}