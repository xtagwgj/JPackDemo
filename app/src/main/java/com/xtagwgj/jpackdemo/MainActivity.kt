package com.xtagwgj.jpackdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.xtagwgj.jpackdemo.db.AppDb
import com.xtagwgj.jpackdemo.db.dao.TestDao
import com.xtagwgj.jpackdemo.domain.TestDomain
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.asReference

class MainActivity : AppCompatActivity() {

    private val ref = this@MainActivity.asReference()

    private lateinit var testDao: TestDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testDao = AppDb.getInstance(this).testDao()

    }

    override fun onResume() {
        super.onResume()
        loadAllData()
    }

    private fun loadAllData() {
        async {
            val listText = testDao.listTest().toString()
            runOnUiThread {
                showData(listText)
            }

        }
    }

    private fun showData(msg: String) {
        tv_message.text = msg
    }

    /**
     *  添加新的数据
     */
    fun addNew(v: View) {
        async {
            val number = testDao.insert(TestDomain(content = "${System.currentTimeMillis()}"))
            if (number > 0L) {
                loadAllData()
            }
        }
    }

}