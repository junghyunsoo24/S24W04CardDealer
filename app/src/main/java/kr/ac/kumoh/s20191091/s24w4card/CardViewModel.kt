package kr.ac.kumoh.s20191091.s24w4card

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {//()도 붙여야함
    private val _cards = IntArray(5){0}//5개 모두 0이됨
    val cards
        get() = _cards //복사를 해서 줌(원본에는 영향x)

    fun shuffle(){
        for(i in _cards.indices)
            _cards[i] = Random.nextInt(52)
    }
}