package com.yelloyew.crocodile

import androidx.lifecycle.ViewModel

class ViewModel: ViewModel() {

    var timerCount: Long = 180000

    var gameController: Int = 0
    /* 1 - популярные слова, 2 - серьёзная игра, 3 - 18+, 4 - хардкор, 5 - правила */
    var pastGameController: Int = 0

    var pressToCard = true
    //индикатор надо ли показать карту "нажмите чтобы увидеть"
    var pastPressToCard = true

    //последнее слово
    var currentWord: String = ""

}