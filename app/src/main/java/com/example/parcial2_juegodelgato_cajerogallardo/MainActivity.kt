package com.example.parcial2_juegodelgato_cajerogallardo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //Variables del tablero
    var imageView1: ImageView?=null
    var imageView2: ImageView?=null
    var imageView3: ImageView?=null
    var imageView4: ImageView?=null
    var imageView5: ImageView?=null
    var imageView6: ImageView?=null
    var imageView7: ImageView?=null
    var imageView8: ImageView?=null
    var imageView9: ImageView?=null

    //Variables para identificar el turno visualmente
    var imgTurnoX: ImageView?=null
    var imgTurnoO: ImageView?=null

    //Variable para destinar el turno
    var turno = "x"

    //Se declara matriz con las posibles maneras de ganar
    val matrizGanar= arrayOf(
        //Primera manera de ganar
        intArrayOf(1,2,3), intArrayOf(4,5,6), intArrayOf(7,8,9),
        intArrayOf(1,4,7), intArrayOf(2,5,8), intArrayOf(3,6,9),
        intArrayOf(1,5,9), intArrayOf(3,5,7),

        //Segunda manera de ganar
        intArrayOf(1,3,2), intArrayOf(4,6,5), intArrayOf(7,9,8),
        intArrayOf(1,7,4), intArrayOf(2,8,5), intArrayOf(3,9,6),
        intArrayOf(1,9,5), intArrayOf(3,7,5),

        //Tercerca manera de ganar
        intArrayOf(2,3,1), intArrayOf(5,6,4), intArrayOf(8,9,7),
        intArrayOf(4,7,1), intArrayOf(5,8,2), intArrayOf(6,9,3),
        intArrayOf(5,9,1), intArrayOf(5,7,3),

        //Cuarta manera de ganar
        intArrayOf(2,1,3), intArrayOf(5,4,6), intArrayOf(8,7,9),
        intArrayOf(4,1,7), intArrayOf(5,2,8), intArrayOf(6,3,9),
        intArrayOf(5,1,9), intArrayOf(5,3,7),

        //Quinta manera de ganar
        intArrayOf(3,1,2), intArrayOf(6,4,5), intArrayOf(9,7,8),
        intArrayOf(7,1,4), intArrayOf(8,2,5), intArrayOf(9,3,6),
        intArrayOf(9,1,5), intArrayOf(7,3,5),

        //Sexta manera de ganar
        intArrayOf(3,2,1), intArrayOf(6,5,4), intArrayOf(9,8,7),
        intArrayOf(7,4,1), intArrayOf(8,5,2), intArrayOf(9,6,3),
        intArrayOf(9,5,1), intArrayOf(7,5,3)
    )

    var posicionesX= IntArray(5)
    var posicionesO= IntArray(5)
    var contadorX= 0
    var contadorO= 0
    var ganador= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView1=findViewById(R.id.imageView1)
        imageView2=findViewById(R.id.imageView2)
        imageView3=findViewById(R.id.imageView3)
        imageView4=findViewById(R.id.imageView4)
        imageView5=findViewById(R.id.imageView5)
        imageView6=findViewById(R.id.imageView6)
        imageView7=findViewById(R.id.imageView7)
        imageView8=findViewById(R.id.imageView8)
        imageView9=findViewById(R.id.imageView9)

        imgTurnoX=findViewById(R.id.imgTurnoX)
        imgTurnoO=findViewById(R.id.imgTurnoO)
        imgTurnoX?.setBackgroundColor(Color.CYAN)

    }

    fun clickImg(view:View){

        //Se guarda las posiciones que fueron presionadas
        val rutaNombre= resources.getResourceName(view.id)
        val nombreClick = rutaNombre.substring(rutaNombre.lastIndexOf("/")+1)

        for(i in 1..9){
            var nombreConcat="imageView$i"
            if(nombreClick==nombreConcat){
                if (turno=="x"){
                    posicionesX[contadorX]=i
                    contadorX++
                }else{
                    posicionesO[contadorO]=i
                    contadorO++
                }
            }
        }

        //Se cambia de turno y el color del identificador de turno
        if(turno == "x"){
            view.setBackgroundResource(R.drawable.gato_x)
            imgTurnoX?.setBackgroundColor(Color.WHITE)
            imgTurnoO?.setBackgroundColor(Color.CYAN)
            turno = "o"
        }else{
            view.setBackgroundResource(R.drawable.gato_o)
            imgTurnoX?.setBackgroundColor(Color.CYAN)
            imgTurnoO?.setBackgroundColor(Color.WHITE)
            turno = "x"
        }

        var altoMatriz= matrizGanar.size
        for(i in 0 until contadorX){
            var gano = true
            for (j in 0 until altoMatriz){
                gano=coincideGanar(matrizGanar[j], posicionesX)
                if (gano==true){
                    ganador="x"
                    Toast.makeText(this, "¡EL GANADOR FUE X! ¡FELICIDADES :D!",Toast.LENGTH_LONG).show()
                    Linea()
                    deshabilitar()
                    break
                }
            }
            if (gano==true){
                break
            }
        }

        for(i in 0 until contadorO){
            var gano = true
            for (j in 0 until altoMatriz){
                gano=coincideGanar(matrizGanar[j], posicionesO)
                if (gano==true){
                    ganador="o"
                    Toast.makeText(this, "¡EL GANADOR FUE O! ¡FELICIDADES :D!",Toast.LENGTH_LONG).show()
                    Linea()
                    deshabilitar()
                    break
                }
            }
            if (gano==true){
                break
            }
        }

        //Se activa esto para que no se pueda presionar dos veces el mismo cuadro
        view.isEnabled=false
    }

    fun coincideGanar(registroGanador:IntArray, posiciones:IntArray):Boolean{

        for (i in 0 until 3){
            if (registroGanador[i]!=posiciones[i]){
                return false
            }
        }
        return true
    }

    fun Linea(){
        if(ganador=="x"){
            for(i in 0 until contadorX){
                when(posicionesX[i]){
                    1 -> imageView1?.setBackgroundColor(Color.GREEN)
                    2 -> imageView2?.setBackgroundColor(Color.GREEN)
                    3 -> imageView3?.setBackgroundColor(Color.GREEN)
                    4 -> imageView4?.setBackgroundColor(Color.GREEN)
                    5 -> imageView5?.setBackgroundColor(Color.GREEN)
                    6 -> imageView6?.setBackgroundColor(Color.GREEN)
                    7 -> imageView7?.setBackgroundColor(Color.GREEN)
                    8 -> imageView8?.setBackgroundColor(Color.GREEN)
                    9 -> imageView9?.setBackgroundColor(Color.GREEN)
                }
            }
        }else {
            for(i in 0 until contadorO){
                when(posicionesO[i]){
                    1 -> imageView1?.setBackgroundColor(Color.GREEN)
                    2 -> imageView2?.setBackgroundColor(Color.GREEN)
                    3 -> imageView3?.setBackgroundColor(Color.GREEN)
                    4 -> imageView4?.setBackgroundColor(Color.GREEN)
                    5 -> imageView5?.setBackgroundColor(Color.GREEN)
                    6 -> imageView6?.setBackgroundColor(Color.GREEN)
                    7 -> imageView7?.setBackgroundColor(Color.GREEN)
                    8 -> imageView8?.setBackgroundColor(Color.GREEN)
                    9 -> imageView9?.setBackgroundColor(Color.GREEN)
                }
            }
        }
    }

    fun deshabilitar(){
        imageView1?.isEnabled=false
        imageView2?.isEnabled=false
        imageView3?.isEnabled=false
        imageView4?.isEnabled=false
        imageView5?.isEnabled=false
        imageView6?.isEnabled=false
        imageView7?.isEnabled=false
        imageView8?.isEnabled=false
        imageView9?.isEnabled=false
    }

    fun reiniciar(view: View){
        val intento=intent
        finish()
        startActivity(intento)
    }
}