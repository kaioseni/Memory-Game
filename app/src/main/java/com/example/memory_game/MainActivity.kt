package com.example.memory_game

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var primeiraImg: ImageView
    private lateinit var segundaImg: ImageView
    private  var primeiraEscolha = -1
    private  var segundaEscolha = -1
    private var cartas = IntArray(16)
    private var jogadas = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Jogo da Memória"

        cartas[0] = R.drawable.fig0
        cartas[1] = R.drawable.fig0
        cartas[2] = R.drawable.fig1
        cartas[3] = R.drawable.fig1
        cartas[4] = R.drawable.fig2
        cartas[5] = R.drawable.fig2
        cartas[6] = R.drawable.fig3
        cartas[7] = R.drawable.fig3
        cartas[8] = R.drawable.fig4
        cartas[9] = R.drawable.fig4
        cartas[10] = R.drawable.fig5
        cartas[11] = R.drawable.fig5
        cartas[12] = R.drawable.fig6
        cartas[13] = R.drawable.fig6
        cartas[14] = R.drawable.fig7
        cartas[15] = R.drawable.fig7

        cartas.shuffle()
    }

    fun play(view: View){
        val img = findViewById<ImageView>(view.id)
        if(!img.isEnabled)
            return


        val escolha = view.tag.toString().toInt()

        img.setImageResource(cartas[escolha])

        title = "Jogo da Memória - jogadas: ${++jogadas}"

        if(primeiraEscolha != -1 && segundaEscolha != -1){
            if(cartas[primeiraEscolha] != cartas[segundaEscolha]){
                primeiraImg.setImageResource(R.drawable.help)
                segundaImg.setImageResource(R.drawable.help)
                primeiraImg.isEnabled = true
                segundaImg.isEnabled = true
            }
            primeiraEscolha = -1
            segundaEscolha = -1
        }

        if(primeiraEscolha == -1){
            primeiraEscolha = escolha
            primeiraImg = img
            primeiraImg.isEnabled = false
        }
        else{
            segundaEscolha = escolha
            segundaImg = img
            segundaImg.isEnabled = false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.opcoes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.mnuNovo)
            novo()
        else if (item.itemId == R.id.mnuSair)
            finish()
        return super.onOptionsItemSelected(item)
    }

    fun novo(){
        finish()
        startActivity(intent)
    }
}