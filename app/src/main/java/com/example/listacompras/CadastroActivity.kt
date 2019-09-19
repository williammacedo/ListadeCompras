package com.example.listacompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        btn_inserir.setOnClickListener {
            val name = txt_produto.text.toString()
            val quantity = txt_qtd.text.toString()
            val price = txt_valor.text.toString()

            if (name.isNotEmpty() && quantity.isNotEmpty() && price.isNotEmpty()) {
                val produto = Produto(name, quantity.toInt(), price.toDouble())
                produtosGlobal.add(produto)
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            }else{
                txt_produto.error = if (txt_produto.text.isEmpty()) "Preenchao nome do produto" else null
                txt_qtd.error = if (txt_qtd.text.isEmpty()) "Preencha a quantidade" else null
                txt_valor.error = if (txt_valor.text.isEmpty()) "Preencha o valor" else null
            }

        }
    }
}
