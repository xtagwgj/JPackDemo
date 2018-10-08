package com.xtagwgj.jpackdemo.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.arch.persistence.room.migration.Migration
import android.content.Context
import com.xtagwgj.jpackdemo.db.dao.TestDao
import com.xtagwgj.jpackdemo.domain.TestDomain
import kotlinx.coroutines.experimental.async

@Database(entities = arrayOf(TestDomain::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {

    abstract fun testDao(): TestDao

    companion object {

        private val CHEESE_DATA = arrayListOf(
                "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
                "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
                "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
                "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
                "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
                "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
                "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
                "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
                "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
                "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
                "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
                "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
                "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
        )

        private const val DB_NAME = "studyJetpack.db"

        @Volatile
        private var instance: AppDb? = null

        @Synchronized
        fun getInstance(context: Context): AppDb {
            if (instance == null) {
                instance = create(context)
            }

            return instance!!
        }

        private fun create(context: Context): AppDb {
            return Room
                    .databaseBuilder(context, AppDb::class.java, DB_NAME)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            async {
                                getInstance(context).testDao().insertMore(CHEESE_DATA.map { TestDomain(content = it) })
                            }

                        }
                    })
                    //允许在主线程查询数据
                    //.allowMainThreadQueries()
                    //迁移数据库使用
                    .addMigrations(MIGRATION_1_2)
                    //迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                    .fallbackToDestructiveMigration()
                    .build()
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }
    }
}