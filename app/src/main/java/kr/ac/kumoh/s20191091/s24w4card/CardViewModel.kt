package kr.ac.kumoh.s20191091.s24w4card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class CardViewModel : ViewModel() {//()도 붙여야함

    //(1)
    //    private val _cards = IntArray(5){0}//5개 모두 0이됨
    private var _cards = MutableLiveData<IntArray>(IntArray(5) { 0 })

    //(1)val cards
    val cards: LiveData<IntArray>
        get() = _cards //복사를 해서 줌(원본에는 영향x)

//    fun shuffle(){
//        // var로 변경, size를 0으로 변경
//        var newCards = IntArray(0)
////        val newCards = IntArray(5) { 0 }
//
//// (1)       for(i in _cards.indices)
////            _cards[i] = Random.nextInt(52)
//
////        for (i in newCards.indices)
//////
////            newCards[i] = Random.nextInt(52)
////
////        _cards.value = newCards
//
//        while (newCards.size < 5) {
//            val num = Random.nextInt(52)
//            if (!newCards.contains(num))
//                newCards = newCards.plus(num)
//        }
//
//        newCards.sort()
//
//        _cards.value = newCards
//    }

    fun shuffle() {
        val newCards = mutableSetOf<Int>()

        while (newCards.size < 5) {
            newCards.add(Random.nextInt(52))
        }

        _cards.value = newCards.sorted().toIntArray()
    }
}