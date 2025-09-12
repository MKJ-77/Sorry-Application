package com.mkj.sooooorrrrryyyyy.ui

sealed class Routes(val route: String) {
    object ChoosingScreen : Routes("ChooseApology")
//    object FriendInput : Routes("FriendInputScreen")
    object  FriendScreen: Routes("SorryScreen")
    object LoverInput : Routes("LoveInputScreenLove")
    object  LoverScreen: Routes("LoveApologyScreen")
}
