package com.example.listacompras

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Produto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        var TEST_MODE = false
        private val databaseName = "ListaCompras.db"

        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (db == null) {
                db = if (TEST_MODE) {
                    Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
                } else {
                    Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
                }
            }
            return db!!
        }

    }
}