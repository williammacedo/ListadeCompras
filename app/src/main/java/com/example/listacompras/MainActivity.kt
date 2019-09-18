package com.example.listacompras

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var produtos = mutableListOf("TV", "Notebook", "Celular", "Monitor")
        val produtosAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, produtos)

        list_view_produtos.adapter = produtosAdapter

        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()

            if(produto.isNotEmpty()) {
                produtosAdapter.add(produto)
                txt_produto.text.clear()
            } else {
                txt_produto.error = "Preencha um valor."
            }
        }

        list_view_produtos.setOnItemLongClickListener { parent, view, position, id ->
            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            true
        }
    }
}
