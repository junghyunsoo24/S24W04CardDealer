package kr.ac.kumoh.s20191091.s24w4card

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.s20191091.s24w4card.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        val model = ViewModelProvider(this)[CardViewModel::class.java]

        val imgcards = arrayOf(
            mainBinding.imgCard1,
            mainBinding.imgCard2,
            mainBinding.imgCard3,
            mainBinding.imgCard4,
            mainBinding.imgCard5
        )

        model.cards.observe(this, Observer { cardList ->
            cardList.forEachIndexed { index, cardName ->
                imgcards[index].setImageResource(
                    resources.getIdentifier(
                        cardName,
                        "drawable",
                        packageName
                    )
                )
            }
        })

//        model.cardRank.observe(this, Observer { rank ->
//            mainBinding.textRank?.text ?:  = rank.ifEmpty { "족보를 확인할 수 없습니다" }
//        })

        mainBinding.btnDeal.setOnClickListener {
            model.shuffle()
            model.check()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}