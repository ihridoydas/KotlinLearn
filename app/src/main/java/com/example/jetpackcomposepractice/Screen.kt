package com.example.jetpackcomposepractice

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"


sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")

    //This is -> Optional Argument
//    //single value
//    object Detail : Screen(route = "detail_screen/?id={id}") {
//        fun passId(id: Int = 0): String {
//            return "detail_screen/?id=$id"
//        }
//
//    }

    //multiple value
    object Detail : Screen(route = "detail_screen/?id={$DETAIL_ARGUMENT_KEY}&name={$DETAIL_ARGUMENT_KEY2}") {
        fun passNameAndId(
            id: Int = 0,
            name:String = "Hridoy"
        ): String {
            return "detail_screen/?id=$id&name=$name"
        }

    }


}
