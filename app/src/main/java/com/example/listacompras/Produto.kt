package com.example.listacompras

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "produto")
data class Produto(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val price: Double,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val photo: ByteArray?
)