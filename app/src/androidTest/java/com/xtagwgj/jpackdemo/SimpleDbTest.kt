package com.xtagwgj.jpackdemo

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.xtagwgj.jpackdemo.db.AppDb
import com.xtagwgj.jpackdemo.db.dao.TestDao
import com.xtagwgj.jpackdemo.domain.TestDomain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleDbTest {

    private lateinit var testDao: TestDao

    private lateinit var mDb: AppDb

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        mDb = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
        testDao = mDb.testDao()
    }

    @After
    fun closeDb() {
        try {
            mDb.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @Test
    fun testInsert() {
        testDao.insert(TestDomain(content = "testA"))
        testDao.insert(TestDomain(content = "testB"))

        testList()

    }

    @Test
    fun testUpdate() {

    }

    @Test
    fun testList() {
        val listDomain = testDao.listTest()
        assert(listDomain.size == 2) {
            println("list = $listDomain")
        }

    }
}