package com.example.randomuserapp.presentation.navigation

sealed class ScreenList(val route : String){
    object RandomUserDataScreen : ScreenList("SchoolList")
    object UserListDetailScreen : ScreenList("UserListDetailScreen")

    fun withArgs(vararg args : String): String{
        return buildString {
            append(route)
            args.forEach {
                args ->
                append("/$args")
            }
        }
    }
}
