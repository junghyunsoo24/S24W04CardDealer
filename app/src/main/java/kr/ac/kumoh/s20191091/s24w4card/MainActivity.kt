package kr.ac.kumoh.s20191091.s24w4card

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Discouraged
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.ac.kumoh.s20191091.s24w4card.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Lifecycle!!!!","oncreate")
        enableEdgeToEdge()

        //setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.btnDeal.setOnClickListener {
//            mainBinding.imgCard1.setImageResource(R.drawable.c_2_of_hearts)
//            Log.i("CARD!!", "c: ${getCardName(32)}")
//            Log.i("CARD!!", R.drawable.c_10_of_clubs.toString())
//            Log.i("CARD!!", R.drawable.c_10_of_diamonds.toString())
            val c = Random.nextInt(52);
            val res = resources.getIdentifier(
                getCardName(c),
                "drawable",
                packageName
            )
            mainBinding.imgCard1.setImageResource(res)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getCardName(c: Int) : String {
        val shape = when (c / 13) {
            0 -> {
                "spades"
            }
            1 -> "diamonds"
            2 -> "hearts"
            3 -> "clubs"
            else -> "error"
        }

        val number = when (c % 13) {
            0 -> "ace"
            in 1..9 -> (c % 13 + 1).toString()
            10 -> "jack"
            11 -> "queen"
            12 -> "king"
            else -> "error"

        }
        return "c_${number}_of_${shape}"
    }

    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle!!!", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle!!!", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle!!!", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle!!!", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Lifecycle!!!", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle!!!", "onDestroy")
    }
}
//        R{
//            drawable{
//              c2_of_hearts = x(상수)
//        }
//              rayout{
//                  activity.main = y
//              }
//        }
//        xml: @drawble/c_of_heart
//        kotlin: R.drawble.c_of_heart
//android 는 세상에 하나뿐인 걸 나타내기 위해서 나타냄
//margin 이랑 padding 중요 경치는 가운데로 찍기 때문에 land(가로)scape
//portrait(초상화->세로)
//onstart와 onresume은 기존껄 사용