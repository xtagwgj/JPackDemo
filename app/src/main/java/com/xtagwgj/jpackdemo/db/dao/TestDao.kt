package com.xtagwgj.jpackdemo.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.xtagwgj.jpackdemo.domain.TestDomain

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(domain: TestDomain): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMore(domain: List<TestDomain>)

    @Update
    fun update(vararg domain: TestDomain)

    @Query("SELECT * FROM TestDomain ORDER BY id DESC")
    fun listTest(): DataSource.Factory<Int, TestDomain>

    @Delete
    fun delete(vararg domain: TestDomain)

}