package com.example.listacompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 101
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {
            val name = txt_produto.text.toString()
            val quantity = txt_qtd.text.toString()
            val price = txt_valor.text.toString()

            if (name.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                val produto = Produto(name, quantity.toInt(), price.toDouble(), imageBitMap)
                produtosGlobal.add(produto)
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
                imageBitMap = null
            }else{
                txt_produto.error = if (txt_produto.text.isEmpty()) "Preenchao nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null
            }

        }

        img_foto_produto.setOnClickListener {
            abrirGaleria()
        }
    }

    fun abrirGaleria() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"

        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                val inputStream = contentResolver.openInputStream(data.data ?: Uri.EMPTY)
                imageBitMap = BitmapFactory.decodeStream(inputStream)

                img_foto_produto.setImageBitmap(imageBitMap)
            }
        }
    }
}
