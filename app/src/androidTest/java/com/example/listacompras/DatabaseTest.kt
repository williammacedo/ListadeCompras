package com.example.listacompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream
import java.io.IOException


@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {
    private lateinit var produtoDao: ProdutoDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        AppDatabase.TEST_MODE = true
        db = AppDatabase.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
        produtoDao = db.produtoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val bitmap = BitmapFactory
            .decodeResource(
                InstrumentationRegistry.getInstrumentation().targetContext.resources,
                R.drawable.visualizar
            )
        val produto = Produto(name = "TV",quantity = 2,price = 25.0, photo = bitMapToByteArray(bitmap))
        produtoDao.insertAll(produto)

        val byName = produtoDao.findByName("TV")
        assertEquals("Nome dos produtos devem ser iguais", produto.name, byName.get(0).name)
    }

    private fun bitMapToByteArray(image: Bitmap?): ByteArray {
        val stream = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
    }
}