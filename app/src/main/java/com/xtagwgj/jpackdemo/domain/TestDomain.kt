package com.xtagwgj.jpackdemo.domain

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity
data class TestDomain(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        val content: String
)