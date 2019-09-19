package com.example.listacompras

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtosAdapter = ProdutoAdapter(this)
        list_view_produtos.adapter = produtosAdapter

        btn_adicionar.setOnClickListener {
            val cadastroIntent = Intent(this, CadastroActivity::class.java)

            startActivity(cadastroIntent)
        }

        list_view_produtos.setOnItemLongClickListener { parent, view, position, id ->
            val item = produtosAdapter.getItem(position)
            produtosAdapter.remove(item)
            true
        }
    }

    override fun onResume() {
        super.onResume()
        val adapter = list_view_produtos.adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        val soma = produtosGlobal.sumByDouble { it.price * it.quantity }
        val f = NumberFormat.getCurrencyInstance()
        txt_total.text = "TOTAL: ${ f.format(soma) }"
    }

}
