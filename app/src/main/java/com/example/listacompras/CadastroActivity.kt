package com.example.listacompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import java.io.ByteArrayOutputStream
import android.widget.Toast
import android.os.AsyncTask
import androidx.core.content.ContextCompat.startActivity





class CadastroActivity : AppCompatActivity() {

    val COD_IMAGE = 101
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {
            saveProduto()
        }

        img_foto_produto.setOnClickListener {
            abrirGaleria()
        }
    }

    private fun saveProduto() {
        val name = txt_produto.text.toString()
        val quantity = txt_qtd.text.toString()
        val price = txt_valor.text.toString()
        val image: ByteArray? = if(imageBitMap != null) bitMapToByteArray(imageBitMap) else null

        if (name.isEmpty() || quantity.isEmpty() || price.isEmpty()) {
            txt_produto.error = if (txt_produto.text.isEmpty()) "Preencha o nome do produto" else null
            txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
            txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null
            return
        }

        class SaveTask : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg voids: Void): Void? {
                //creating a task
                val produto = Produto(name = name, quantity = quantity.toInt(), price = price.toDouble(), photo = image)

                //adding to database
                AppDatabase.getInstance(applicationContext)
                    .produtoDao()
                    .insertAll(produto)
                return null
            }
        }

        val st = SaveTask()
        st.execute()

        finish()
        txt_produto.text.clear()
        txt_qtd.text.clear()
        txt_valor.text.clear()
        imageBitMap = null
        Toast.makeText(applicationContext, "Produto cadastrado com sucesso.", Toast.LENGTH_LONG).show()
    }

    private fun bitMapToByteArray(image: Bitmap?): ByteArray {
        val stream = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.PNG, 90, stream)
        return stream.toByteArray()
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
