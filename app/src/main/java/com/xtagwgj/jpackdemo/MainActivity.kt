package com.xtagwgj.jpackdemo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.xtagwgj.jpackdemo.adapter.CheeseAdapter
import com.xtagwgj.jpackdemo.db.AppDb
import com.xtagwgj.jpackdemo.domain.TestDomain
import com.xtagwgj.jpackdemo.model.DemoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.asReference

class MainActivity : AppCompatActivity() {

    private val ref = this@MainActivity.asReference()

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DemoViewModel(application) as T
            }
        }).get(DemoViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = CheeseAdapter()
        mRecycler.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        viewModel.allDemos.observe(this, Observer {
            mAdapter.submitList(it)
        })

    }

    /**
     * 添加新的记录
     */
    fun addNewRecord(v: View) {
        async {
            AppDb.getInstance(ref()).testDao().insert(TestDomain(content = "测试消息，当前时间：${System.currentTimeMillis()}"))
        }
    }
}