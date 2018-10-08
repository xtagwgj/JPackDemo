package com.xtagwgj.jpackdemo.db.dao

import android.arch.persistence.room.*
import com.xtagwgj.jpackdemo.domain.TestDomain

@Dao
interface TestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( domain: TestDomain): Long

    @Update
    fun update(vararg domain: TestDomain)

    @Query("SELECT * FROM TestDomain ORDER BY id DESC")
    fun listTest(): List<TestDomain>

    @Delete
    fun delete(vararg domain: TestDomain)

}