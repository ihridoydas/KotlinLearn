package com.example.jetpackcomposepractice

const val DETAIL_ARGUMENT_KEY = "id"
const val DETAIL_ARGUMENT_KEY2 = "name"


sealed class Screen(val route:String){
    object Home : Screen(route = "home_screen")
    //This is -> Required Argument
    object Detail :Screen(route = "detail_screen/{$DETAIL_ARGUMENT_KEY}/{$DETAIL_ARGUMENT_KEY2}"){
        //Single argument
//        fun passId(id:Int):String{
//           // return "detail_screen/$id"
//            return this.route.replace(oldValue = "{$DETAIL_ARGUMENT_KEY}", newValue = id.toString())
//        }
        //Pass Multiple Argument(Required Argument)
        fun passNameAndId(
            id:Int,
            name:String
        ):String{
          return "detail_screen/$id/$name"
        }
    }


}
