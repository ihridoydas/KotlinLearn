package com.example.jetpackcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class LazyColumnActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            Button(onClick = {
//                val navigate = Intent(this@LazyColumnActivity,MainActivity::class.java)
//                startActivity(navigate)
//            }) {
//                Text(text = "Navigate Main Activity")
//            }

            //Start create Lazy Column
            /*
            val personRepository = PersonRepository()
            val getAllData = personRepository.getAllData()

            LazyColumn(
                contentPadding = PaddingValues(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                items(items = getAllData){person->
                    CustomItem(person = person)
                }
            }
             */

            //Start create Lazy Column(With Sticky Header)
            val sections = listOf("A","B","C","D","E","F","G","H")

            LazyColumn(
                contentPadding = PaddingValues(all = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){

                sections.forEach{section->
                    stickyHeader {
                        Text(text = "Section $section", modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(12.dp)
                        )
                    }
                    items(count = 10){
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = "Item $it form the section $section")
                    }

                }
            }


        }
    }
}
