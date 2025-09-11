package com.mkj.sooooorrrrryyyyy.ui

sealed class Routes(val route: String) {
    object ChoosingScreen : Routes("ChooseApology")
    object FriendInput : Routes("splash")
    object  FriendScreen: Routes("SorryScreen")
    object LoverInput : Routes("LoveInputScreenLove")
    object  LoverScreen: Routes("LoveDisplayScreenLove")
}
