package com.example.listacompras

import android.graphics.Bitmap

data class Produto(val name: String, val quantity: Int, val price: Double, val photo: Bitmap? = null)