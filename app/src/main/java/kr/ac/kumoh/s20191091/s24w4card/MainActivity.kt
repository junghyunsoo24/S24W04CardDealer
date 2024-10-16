package kr.ac.kumoh.s20191091.s24w4card

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.Discouraged
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

        val model = ViewModelProvider(this)[CardViewModel::class.java]
        model.cards.observe(this, Observer {
            val res = IntArray(5)

            model.cards.value!!.forEachIndexed{index, num->
                res[index] = resources.getIdentifier(
                    getCardName(num),
                    "drawable",
                    packageName
                )
            }
//        for (i in model.cards.indices) {//index에 복수형
//            res[i] = resources.getIdentifier(
//                getCardName(model.cards[i]),
//                "drawable",
//                packageName
//            )
//        }

            mainBinding.imgCard1.setImageResource(res[0])
            mainBinding.imgCard2.setImageResource(res[1])
            mainBinding.imgCard3.setImageResource(res[2])
            mainBinding.imgCard4.setImageResource(res[3])
            mainBinding.imgCard5.setImageResource(res[4])
        })
        //람다식으로 {}를 씀

        mainBinding.btnDeal.setOnClickListener {
            model.shuffle()

//  obserber로 인해 사용x          model.cards.value!!.forEachIndexed{index, num->
//                res[index] = resources.getIdentifier(
//                    getCardName(num),
//                    "drawable",
//                    packageName
//                )
//            }

//            (1)
//            mainBinding.imgCard1.setImageResource(R.drawable.c_2_of_hearts)
//            Log.i("CARD!!", "c: ${getCardName(32)}")
//            Log.i("CARD!!", R.drawable.c_10_of_clubs.toString())
//            Log.i("CARD!!", R.drawable.c_10_of_diamonds.toString())

//            (2)
//            val c = Random.nextInt(52);
//            val res = resources.getIdentifier(
//                getCardName(c),
//                "drawable",
//                packageName
//            )
//            mainBinding.imgCard1.setImageResource(res)
            
//          (3)
//            val c = IntArray(5)
//            val res = IntArray(5)

            //for (i in 0..4)0부터4까지
            //for (i in 0 until 5)
            //for (i in 0 until c.size)
//            for (i in c.indices) {//index에 복수형
//                c[i] = Random.nextInt(52)
//
//                Log.i("Test", "${c[i]} : " +
//                        "${getCardName(c[i])}")
//
//                res[i] = resources.getIdentifier(
//                    getCardName(c[i]),
//                    "drawable",
//                    packageName
//                )
//            }

//observer로 인해 사용x
//            mainBinding.imgCard1.setImageResource(res[0])
//            mainBinding.imgCard2.setImageResource(res[1])
//            mainBinding.imgCard3.setImageResource(res[2])
//            mainBinding.imgCard4.setImageResource(res[3])
//            mainBinding.imgCard5.setImageResource(res[4])
            //카드에 중복 처리는 되지 않음
            //회전 시, 카드가 초기화됨
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