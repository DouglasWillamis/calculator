package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numeros
        btnUm.setOnClickListener { appendOnExpresstion("1", true) }
        btnDois.setOnClickListener { appendOnExpresstion("2", true) }
        btnTres.setOnClickListener { appendOnExpresstion("3", true) }
        btnQuatro.setOnClickListener { appendOnExpresstion("4", true) }
        btnCinco.setOnClickListener { appendOnExpresstion("5", true) }
        btnSeis.setOnClickListener { appendOnExpresstion("6", true) }
        btnSete.setOnClickListener { appendOnExpresstion("7", true) }
        btnOito.setOnClickListener { appendOnExpresstion("8", true) }
        btnNove.setOnClickListener { appendOnExpresstion("9", true) }
        btnZero.setOnClickListener { appendOnExpresstion("0", true) }
        btnPonto.setOnClickListener { appendOnExpresstion(".", true) }

        //Operadores
        btnSoma.setOnClickListener { appendOnExpresstion("+", false) }
        btnSubtracao.setOnClickListener { appendOnExpresstion("-", false) }
        btnMultiplicacao.setOnClickListener { appendOnExpresstion("*", false) }
        btnDivisao.setOnClickListener { appendOnExpresstion("/", false) }
        btnOpen.setOnClickListener { appendOnExpresstion("(", false) }
        btnClose.setOnClickListener { appendOnExpresstion(")", false) }

        btnCE.setOnClickListener {
            tvExpressao.text = ""
            tvResultado.text = ""
        }

        btnLimpar.setOnClickListener {
            val string = tvExpressao.text.toString()
            if(string.isNotEmpty()){
                tvExpressao.text = string.substring(0,string.length-1)
            }
            tvResultado.text = ""
        }

        btnIgual.setOnClickListener {
            try {

                val expression = ExpressionBuilder(tvExpressao.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResultado.text = longResult.toString()
                else
                    tvResultado.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }

    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {

        if(tvResultado.text.isNotEmpty()){
            tvExpressao.text = ""
        }

        if (canClear) {
            tvResultado.text = ""
            tvExpressao.append(string)
        } else {
            tvExpressao.append(tvResultado.text)
            tvExpressao.append(string)
            tvResultado.text = ""
        }
    }
}
