package com.example.listacompras

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM produto")
    fun getAll(): List<Produto>

    @Query("SELECT * FROM produto WHERE id IN (:produtoIds)")
    fun loadAllByIds(produtoIds: IntArray): List<Produto>

    @Query("SELECT * FROM produto WHERE name LIKE :name")
    fun findByName(name: String): List<Produto>

    @Insert
    fun insertAll(vararg produtos: Produto)

    @Delete
    fun delete(produto: Produto)
}