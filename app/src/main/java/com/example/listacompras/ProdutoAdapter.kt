package com.example.listacompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.ByteArrayInputStream


class ProdutoAdapter (context: Context) : ArrayAdapter<Produto>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View
        val f = NumberFormat.getCurrencyInstance()
        val item = getItem(position)
        if(convertView == null) {
            v = LayoutInflater
                .from(context)
                .inflate(R.layout.list_view_item, parent, false)
        } else {
            v = convertView
        }

        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        txt_produto.text = item?.name
        txt_qtd.text = item?.quantity.toString()
        txt_valor.text = f.format(item?.price)

        if(item?.photo != null) {
            img_produto.setImageBitmap(byteArrayToBitmap(item.photo))
        }

        return v
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        val arrayInputStream = ByteArrayInputStream(byteArray)
        return BitmapFactory.decodeStream(arrayInputStream)
    }
}