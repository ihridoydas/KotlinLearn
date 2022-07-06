package com.example.jetpackcomposepractice

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.jetpackcomposepractice.ui.theme.AnotherActivity
import com.example.jetpackcomposepractice.ui.theme.JetPackComposePracticeTheme
import com.example.jetpackcomposepractice.ui.theme.SplashFont
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        // ColumnExample()
                        //LazyRowExample()
                        //LazyColumnExample()
                        //BoxExample()
                        // MaterialUIApp()
                        //ImageAssetExample()
                        // ImageInternetExample()
                        //IconExample()
                        //CardExample()
                        // StateFullExample()
                        //HelloScreen()
                        //ButtonExample()
                        //TextFeildExample()
                        //Registration()
                        //MyComposable()

                        //Use Hoisting with stateful and stateless
//                        FirstName()
//                        lastName()

                        //LiveData with viewModel
                        FirstScreen()

                        //Navigate Another Button
                        Button(onClick = {
                            val navigate = Intent(this@MainActivity, AnotherActivity::class.java)
                            startActivity(navigate)
                        }) {
                            Text(text = "Navigate Another Activity")
                        }

                        /*
                       Column {
                           Greeting("Android")
                           FirstText()
                           ListOfName()
                           SimpleText()
                           LongText()
                           RowText()
                        }
                         */


                    }

                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun FirstText() {
    Column {
        Text(text = "Hello My name is Hridoy")
        Text(text = "Hello My name is Chandra")
        Text(text = "Hello My name is Das \n")
    }
}

@Composable
fun ListOfName() {
    val names = listOf<String>("Hridoy", "Chandra", "Das")

    Column {
        for (name in names) {
            Text(text = "Hello $name", fontSize = 30.sp)
        }
    }
}

//Add simple Text

@Composable
fun SimpleText() {
    Text(
        text = "Hello Kotlin ",
        fontSize = 30.sp,
        color = Color.Red,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraBold,
        fontFamily = SplashFont,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Color.Green)
            .width(410.dp),
    )
}

@Composable
fun LongText() {
    Text(text = "Jetpack_Compose".repeat(10), fontSize = 30.sp)
}

@Composable
fun RowText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .horizontalScroll(rememberScrollState())
            .background(Color.LightGray),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = "dasu ", modifier = Modifier.background(color = Color.Green))
        Text(text = "rido ", modifier = Modifier.background(color = Color.Red))
        Text(text = "chandra ", modifier = Modifier.background(color = Color.Blue))
        Text(text = "dasu ", modifier = Modifier.background(color = Color.Green))
        Text(text = "rido ", modifier = Modifier.background(color = Color.Red))
        Text(text = "chandra ", modifier = Modifier.background(color = Color.Blue))
        Text(text = "dasu ", modifier = Modifier.background(color = Color.Green))
        Text(text = "rido ", modifier = Modifier.background(color = Color.Red))
        Text(text = "chandra ", modifier = Modifier.background(color = Color.Blue))
        Text(text = "dasu ", modifier = Modifier.background(color = Color.Green))
        Text(text = "rido ", modifier = Modifier.background(color = Color.Red))
        Text(text = "chandra ", modifier = Modifier.background(color = Color.Blue))
        Text(text = "dasu ", modifier = Modifier.background(color = Color.Green))
        Text(text = "rido ", modifier = Modifier.background(color = Color.Red))
        Text(text = "chandra ", modifier = Modifier.background(color = Color.Blue))
        Text(text = "dasu ", modifier = Modifier.background(color = Color.Green))
        Text(text = "rido ", modifier = Modifier.background(color = Color.Red))
        Text(text = "chandra ", modifier = Modifier.background(color = Color.Blue))

    }
}


//if it is column use ->.verticalScroll(rememberScrollState())
//if it is Row use -> .horizontalScroll(rememberScrollState())
@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "First ", fontSize = 30.sp)
        for (i in 0..50) {
            Text(text = "Item $i", fontSize = 30.sp)
        }
        Text(text = "last ", fontSize = 30.sp)

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposePracticeTheme {
        Column {
//            Greeting("Android")
//            FirstText()
//            ListOfName()
//            SimpleText()
//            LongText()
//            RowText()
        }
    }
}

//Lazy Row use When you need some data which data you will load partially

@Composable
fun LazyRowExample() {
    LazyRow(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 100.dp),
        horizontalArrangement = Arrangement.spacedBy(50.dp)
    )
    {
        item {
            Text(text = " First Item ", fontSize = 30.sp)
        }
        items(50) { i ->
            Text(text = "Item $i", fontSize = 30.sp)
        }
    }
}

@Composable
fun LazyColumnExample() {
    LazyColumn(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        item {
            Text(text = " First Item", fontSize = 30.sp)
        }
        items(100) { i ->
            Text(text = "Item $i", fontSize = 30.sp)
        }
    }

}

@Composable
fun BoxExample() {
    Box(
        modifier = Modifier
            .fillMaxSize(0.5f)
            .background(color = Color.Yellow)
    ) {

        Text(text = "this is Outer Box ..........")
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .background(color = Color.Blue)
        ) {

            Text(text = "this is Outer Box ..........")
        }
    }

}

@Composable
fun MaterialUIApp() {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "App Bar")
            })
        }
    ) {

    }
}

@Composable
fun ImageAssetExample() {
    Image(
        painter = painterResource(id = R.drawable.myname),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .border(5.5.dp, Color.Green, CircleShape)
            .size(300.dp)
            .clip(CircleShape)
    )

}

//Collecting by Pixabay from internet
@Composable
fun ImageInternetExample() {
    Image(
        painter = rememberAsyncImagePainter(
            model = "https://cdn.pixabay.com/photo/2022/06/19/04/25/cat-7271017_1280.jpg"
        ),
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .border(5.5.dp, Color.Green, CircleShape)
            .size(300.dp)
            .clip(CircleShape)
    )

}

//Set Icon and clickable button

@Composable
fun IconExample() {
    Icon(
        Icons.Filled.Menu,
        contentDescription = "Menu"
        //You can use Modifier and Color what you want
    )
}

@Composable
fun CardExample() {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Gray,
        border = BorderStroke(5.dp, color = Color.Red),
        contentColor = Color.White
    ) {
        Column(modifier = Modifier.padding(40.dp)) {
            Text(
                text = "This is a Card",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "This is a another Card",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
        }
    }

}

//State Full material Design

@Composable
fun StateFullExample() {
    var name: String by remember {
        mutableStateOf("")
    }
    Column {
        OutlinedTextField(value = name, onValueChange = { name = it })
        Text(text = name, fontSize = 30.sp)
    }

}
// Test Hoisting

@Composable
fun HelloScreen() {
    var name: String by remember {
        mutableStateOf("")
    }
    HelloContent(name = name, onNameChange = { name = it })
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column {
        OutlinedTextField(value = name, onValueChange = onNameChange)
        Text(text = name, fontSize = 30.sp)
    }
}

// Button
@Composable
fun ButtonExample() {
    Column {
        //Normal Button
        Button(
            onClick = { Log.d("Button", "Button is Clicked") },
            modifier = Modifier.padding(40.dp),
            //You can Content padding
            contentPadding = PaddingValues(start = 20.dp, 30.dp, 20.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Magenta),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Blue)

        ) {

            Text(text = "Button One")
        }

        //Text button
        TextButton(
            onClick = { Log.d("Button", "Button is Clicked") },
            modifier = Modifier.padding(40.dp),
            //You can Content padding
            contentPadding = PaddingValues(start = 20.dp, 30.dp, 20.dp, 50.dp),
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Green),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Blue)
        ) {

            Text(text = "Button Two")
        }

        //OutLine Button
        OutlinedButton(
            onClick = { Log.d("Button", "Button is Clicked") },
            modifier = Modifier.padding(30.dp),
            //You can Content padding
            contentPadding = PaddingValues(start = 40.dp, 20.dp, 20.dp, 20.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Green),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Blue)
        ) {

            Text(text = "Outline Button")
        }

        //Icon Button
        IconButton(
            onClick = { Log.d("Button", "Button is Clicked") },
            modifier = Modifier
                .padding(30.dp)
                .then(Modifier.size(50.dp))
                .border(1.dp, Color.Red, shape = CircleShape)

        ) {
            Icon(Icons.Default.Add, "Icon button", tint = Color.Red)
        }

        FloatingActionButton(
            onClick = { Log.d("Button", "Button is Clicked") },
            modifier = Modifier.padding(30.dp),
            backgroundColor = Color.Red
        ) {
            Icon(Icons.Default.Add, "Icon button")
        }


    }

}

//Text FieldExample with label and PlaceHolder
@Composable
fun TextFeildExample() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)

    ) {
        item {
            TextField(
                value = "sddasda",
                onValueChange = {},
                label = {
                    Text(
                        text = "Name"
                    )
                },
                placeholder = {
                    Text(text = "Write your Name")
                },
                textStyle = TextStyle(color = Color.Blue),
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Transparent
                ),
//                readOnly = true,
//                singleLine = true


            )
        }

        item {
            //OutLine Text Field
            OutlinedTextField(
                value = "sddasda",
                onValueChange = {},
                label = {
                    Text(
                        text = "Name"
                    )
                },
                placeholder = {
                    Text(text = "Write your Name")
                },
                textStyle = TextStyle(color = Color.Blue),
                leadingIcon = {

                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Transparent
                ),
//                readOnly = true,
//                singleLine = true


            )
        }

        item {
            //OutLine Text Field
            OutlinedTextField(
                value = "Pasword",
                onValueChange = {},
                label = {
                    Text(
                        text = "PassWord"
                    )
                },
                placeholder = {
                    Text(text = "Write your Password")
                },
                textStyle = TextStyle(color = Color.Blue),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Transparent
                ),
//                readOnly = true,
//                singleLine = true

                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)

            )
        }
    }
}

//Registration Button with Mutable state
@Composable
fun Registration() {
    var name: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        item {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(text = "Name")
                },
                placeholder = {
                    Text(text = "Write Your Name")
                },
                textStyle = TextStyle(color = Color.Blue),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
        }
        item {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(text = "Email")
                },
                placeholder = {
                    Text(text = "Write Your Email")
                },
                textStyle = TextStyle(color = Color.Blue),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "")
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
        }
        item {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(text = "Password")
                },
                placeholder = {
                    Text(text = "Write Your Password")
                },
                textStyle = TextStyle(color = Color.Blue),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
                },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Green,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        item {
            Button(
                onClick = {
                    Log.d(
                        "submitButton",
                        "Name: $name, Email: $email, Password: $password"
                    )
                },
                contentPadding = PaddingValues(start = 20.dp, top = 10.dp, end = 20.dp, 10.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White),
                shape = CircleShape
            ) {
                Text(text = "Submit")
            }
        }
    }
}

//State change with remember
@Composable
fun MyComposable() {
    var myvalue by remember {
        mutableStateOf(false)
    }
    Button(onClick = { myvalue = !myvalue }) {
        Text(text = "$myvalue")
        Log.d("MSG", "This is message")
    }
}


//State hoisting(stateless and stateFull)
@Composable
fun ScreenContent(
    name: String?,
    onNameChange: (String?) -> Unit,
    labelname :String?
) {

    TextField(
        value = name!!,
        onValueChange = onNameChange,
        label = { Text(labelname!!)}
    )
}

@Composable
fun FirstName(){
    var name by remember {
        mutableStateOf("")
    }
    ScreenContent(name = name, onNameChange = {name = it!!},labelname = "First Name")

}

@Composable
fun lastName(){
    var lastName by remember {
        mutableStateOf("")
    }
    ScreenContent(name = lastName, onNameChange ={lastName = it!!} , labelname = "Last Name")
}

//LiveData With ViewModel
class MyViewModel : ViewModel(){
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    fun onNameChanged(newName:String){
        _name.value = newName
    }
}

@Composable
fun FirstScreen(myViewModel: MyViewModel = viewModel()){
    val name: String by myViewModel.name.observeAsState("")
    ScreenContent(name = name,
        onNameChange = {
            myViewModel.onNameChanged(it!!) }, labelname = "Good Name")


}






