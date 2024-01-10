package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var pos: MutableList<ImageView>
    private lateinit var determineKeyImagePosition: MutableList<Char>
    private var playerXTurn = true
    var count = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        pos= mutableListOf<ImageView>(binding.pos11,binding.pos12,binding.pos13,
                                      binding.pos21,binding.pos22,binding.pos23,
                                      binding.pos31,binding.pos32,binding.pos33)
        // a for blank image,x for X,0 for 0 i.e. which image is at which position
        //initially image is blank i.e. a
        //this is char flag used to determine the state of an image during runtime
        determineKeyImagePosition = mutableListOf<Char>('a','a','a',
                                                       'a','a','a',
                                                       'a','a','a')

        gameplay()

    }

    private fun reset(){
        binding.btnPlayer0.isVisible = true
        binding.btnPlayerX.isVisible = true
        count = 0
        for (j in 0..8){
            pos[j].setImageResource(R.drawable.baseline_rectangle_24)
            determineKeyImagePosition[j]='a'
        }
    }
    private fun gameplay(){
        reset()
        var array = arrayOf("afh","afhs")
        playerXTurn = false
        binding.btnPlayer0.setOnClickListener{
            playerXTurn = false


            binding.btnPlayer0.isVisible = false
            binding.btnPlayerX.isVisible = false

        }
        binding.btnPlayerX.setOnClickListener{
            playerXTurn = true
//            array = checkResults()
//            setDialog(array[0],array[1])
            binding.btnPlayerX.isVisible = false
            binding.btnPlayer0.isVisible = false
        }
        pos.forEachIndexed { i,imageviews ->


            imageviews.setOnClickListener {
                if (playerXTurn && determineKeyImagePosition[i]=='a') {
                    setX(i)
                    count++
                    playerXTurn = false
                    determineKeyImagePosition[i]='X'
                    array = checkResults()
                    setDialog(array[0],array[1])
                } else if (!playerXTurn && determineKeyImagePosition[i]=='a') {
                    set0(i)
                    count++
                    playerXTurn = true
                    determineKeyImagePosition[i]='0'
                    array = checkResults()
                    setDialog(array[0],array[1])
                }


            }

        }
    }




    private fun checkResults():Array<String>{

        Log.i("MainActivity","it enter in check results")
        val b0 = determineKeyImagePosition[0]
        val b1 = determineKeyImagePosition[1]
        val b2 = determineKeyImagePosition[2]
        val b3 = determineKeyImagePosition[3]
        val b4 = determineKeyImagePosition[4]
        val b5 = determineKeyImagePosition[5]
        val b6 = determineKeyImagePosition[6]
        val b7 = determineKeyImagePosition[7]
        val b8 = determineKeyImagePosition[8]
        //horizontal

        if(b0 ==b1 && b1==b2 && b2!='a'){
            return arrayOf(b0.toString(),"win")
            //setDialog("${b0}","win")
        } else if(b3 ==b4 && b4==b5 && b5!='a' ){
            return arrayOf(b3.toString(),"win")
            //setDialog("${b3}","win")
        }else if(b6 ==b7 && b7==b8 && b8!='a' ){
            return arrayOf(b6.toString(),"win")
            //setDialog("${b6}","win")
        }
        //vertical
        else if(b0 ==b3 && b3==b6 && b6!='a' ){
            return arrayOf(b0.toString(),"win")
            //setDialog("${b3}","win")
        }else if(b1 ==b4 && b4==b7 && b7!='a' ){
            return arrayOf(b1.toString(),"win")
            //setDialog("${b1}","win")
        }else if(b2 ==b5 && b5==b8 && b8!='a' ){
            return arrayOf(b2.toString(),"win")
            //setDialog("${b2}","win")
        }
        //cross
        else if(b0 ==b4 && b4==b8 && b8!='a' ){
            return arrayOf(b0.toString(),"win")
            //setDialog("${b0}","win")
        }else if(b2 ==b4 && b4==b6 && b6!='a' ){
            return arrayOf(b2.toString(),"win")
            //setDialog("${b2}","win")
        }
        else if(count==9){
            return arrayOf("draw","draw")
            //setDialog("","draw")
        }


        return arrayOf("","")
    }
    private fun setDialog(playerSymbol:String, winOrDraw:String){
        Log.i("MainActivity","it enters in dialog")
        if(playerSymbol !=""&&winOrDraw !=""){
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("------RESULTS------")
            if (winOrDraw == "win") {
                dialog.setMessage("\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 PLAYER${playerSymbol} WON!!!\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89")
            } else if (winOrDraw == "draw") {
                dialog.setMessage("\uD83C\uDFF3️\uD83C\uDFF3️\uD83C\uDFF3️ DRAW \uD83C\uDFF3️\uD83C\uDFF3️\uD83C\uDFF3️")
            }
            dialog.setCancelable(false)
            dialog.setNeutralButton("      \uD83D\uDC40 NEW GAME \uD83D\uDC40 ") { _, _ ->
                reset()

            }
            dialog.show()
        }

    }

    private fun setX(i:Int){
        pos[i].setImageResource(R.drawable.x_cross)
    }

    private fun set0(i:Int){
        pos[i].setImageResource(R.drawable.circle)
    }


}