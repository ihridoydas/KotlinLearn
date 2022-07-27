package com.example.jetpackcomposepractice

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.jetpackcomposepractice.albumFeature.AlbumDetailsActivity
import com.example.jetpackcomposepractice.customUI.CustomComponent
import com.example.jetpackcomposepractice.feature_note.NoteActivity
import com.example.jetpackcomposepractice.learnTesting.TestingActivity
import com.example.jetpackcomposepractice.navigationPassingDataNavHost.PassingDataNavHost
import com.example.jetpackcomposepractice.nestedNavigation.NestedNavigation
import com.example.jetpackcomposepractice.retrofitAPI.RetrofitActivity
import com.example.jetpackcomposepractice.screen.NavGraphActivity
import com.example.jetpackcomposepractice.todo.ui.TodoActivity
import com.example.jetpackcomposepractice.todoMVVM.TodoMainActivity
import com.example.jetpackcomposepractice.ui.theme.JetPackComposePracticeTheme
import com.example.jetpackcomposepractice.ui.theme.Shapes
import com.example.jetpackcomposepractice.ui.theme.SplashFont
import com.example.jetpackcomposepractice.uiPractice.loginScreen.LoginActivity
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposePracticeTheme {
                //A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
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
                        //FirstScreen()

                        //BoxSimpleExample()
                        //  SuperScriptSample("Hello", "world!")

                        //ExpendableCardExample()

                        //GoogleButton()

                        // CoilImage()

                        //Gradient Button Use with parameters
                        /*
                        GradiantButtonFirst(
                            text = "Button",
                            textColor = Color.White,
                            gradient = Brush.horizontalGradient(
                                colors = listOf(
                                    color1, color2
                                )
                            ),
                            onClick = {
                                Log.d("Check","Clicked")
                            },
                        )

                         */

                        //Navigate Another Button (By activity)
                        /*
                        Button(onClick = {
                            val navigate = Intent(this@MainActivity, LazyColumnActivity::class.java)
                            startActivity(navigate)
                        }) {
                            Text(text = "Navigate Lazy Column Activity")
                        }


                       Column {
                           Greeting("Android")
                           FirstText()
                           ListOfName()
                           SimpleText()
                           LongText()
                           RowText()
                        }
                         */

                        //Learn Custom Circle indicator
                        //CustomCircleIndicator()


                        //Learn Pass Argument with Navigation
                        // Argument has two type
                        //1. Optional Argument ->  (not need pass argument to another screen)
                        // 2. Required argument (you might need everytime need -> value to pass when you pass data)


                        //Note Screen

                        //State Practice

//                        ColorBox(
//                            Modifier.fillMaxSize ()
//                        )


                        //ScreenSwitch()
                        //UseSwitch()

                        //TODO--Button of main Activity

                        @Composable
                        fun DisplayButton(
                            onClick: () -> Unit,
                            modifier: Modifier = Modifier,
                            text: String,
                        ) {
                            Button(
                                onClick = onClick,
                                modifier
                                    .fillMaxWidth()
                                    .align(alignment = Alignment.CenterHorizontally)
                                    .padding(start = 5.dp, end = 5.dp)
                            ) {
                                Text(text = text)

                            }

                        }

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, TodoActivity::class.java)
                            startActivity(navigate)
                        }, text = "Navigate Todo(Room)Activity")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, RetrofitActivity::class.java)
                            startActivity(navigate)
                        }, text = "Navigate Retrofit Activity")


                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, NoteActivity::class.java)
                            startActivity(navigate)
                        }, text = "Notes Activity")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, NestedNavigation::class.java)
                            startActivity(navigate)
                        }, text = "Nested Navigation")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, NavGraphActivity::class.java)
                            startActivity(navigate)
                        }, text = "NavGraph argument")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, PassingDataNavHost::class.java)
                            startActivity(navigate)
                        }, text = "Passing Data Via NavHost")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, AlbumDetailsActivity::class.java)
                            startActivity(navigate)
                        }, text = "Album Details")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(navigate)
                        }, text = "Login UI")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, TestingActivity::class.java)
                            startActivity(navigate)
                        }, text = "UI Test")

                        DisplayButton(onClick = {
                            val navigate = Intent(this@MainActivity, TodoMainActivity::class.java)
                            startActivity(navigate)
                        }, text = "Todo MVVM")

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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
    labelname: String?
) {

    TextField(
        value = name!!,
        onValueChange = onNameChange,
        label = { Text(labelname!!) }
    )
}

@Composable
fun FirstName() {
    var name by remember {
        mutableStateOf("")
    }
    ScreenContent(name = name, onNameChange = { name = it!! }, labelname = "First Name")

}

@Composable
fun lastName() {
    var lastName by remember {
        mutableStateOf("")
    }
    ScreenContent(name = lastName, onNameChange = { lastName = it!! }, labelname = "Last Name")
}

//LiveData With ViewModel
class MyViewModel : ViewModel() {
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name
    fun onNameChanged(newName: String) {
        _name.value = newName
    }
}

@Composable
fun FirstScreen(myViewModel: MyViewModel = viewModel()) {
    val name: String by myViewModel.name.observeAsState("")
    ScreenContent(
        name = name,
        onNameChange = {
            myViewModel.onNameChanged(it!!)
        }, labelname = "Good Name"
    )
}

@Composable
fun BoxSimpleExample() {
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .width(100.dp)
            .height(100.dp)
    ) {

    }

}

// SuperScript and SubsCript
@Composable
fun SuperScriptSample(
    normalText: String,
    superText: String
) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            )
        ) {
            append(normalText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.overline.fontSize,
                fontWeight = FontWeight.Normal,
                //baselineShift = BaselineShift.Superscript,
                baselineShift = BaselineShift.Subscript,

                )
        ) {
            append(superText)
        }
    })
}

//Expandable Card View
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpendableCardExample() {
    var expendedState by remember {
        mutableStateOf(false)
    }
    val rotationState by animateFloatAsState(targetValue = if (expendedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.medium,
        onClick =
        {
            expendedState = !expendedState
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //First Text
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = "My Title",
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                    )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expendedState = !expendedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "My Arrow Drop Down"
                    )

                }

            }
            //Second Text
            if (expendedState) {
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                            "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                            "when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                            " It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                            "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with " +
                            "desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,

                    )
            }

        }

    }
}

//Button With Progress Bar
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GoogleButton(
    text: String = "Sign Up With Google.",
    loadingText: String = "Creating an Account..."
) {
    var clicked by remember {
        mutableStateOf(false)
    }
    Surface(
        onClick = {
            clicked = !clicked
        },
        shape = Shapes.medium,
        border = BorderStroke(1.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "My Picture and Logo",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (clicked) loadingText else text)
            if (clicked) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
            }
        }

    }
}

//Image show and Transactions and Transformations
@Composable
fun CoilImage() {
    Box(
        modifier = Modifier
            .height(350.dp)
            .width(350.dp),
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://cdn.pixabay.com/photo/2022/07/03/08/56/bird-7298690_1280.jpg")
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.myname)
                    error(R.drawable.error)
                    crossfade(1000)
                    transformations(
                        // GrayscaleTransformation()
                        //RoundedCornersTransformation(20f)

                    )

                }).build()
        )
        //use Loading when image load Slow
        val painterState = painter.state
        Image(painter = painter, contentDescription = "this is bird")
//        if(painterState is AsyncImagePainter.State.Loading){
//            CircularProgressIndicator()
//        }

    }
}


//Learn Gradiant button Jetpack Compose
//Button
@Composable
fun GradiantButtonFirst(
    text: String,
    textColor: Color,
    gradient: Brush,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        onClick = {
            onClick()
        }) {
        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 70.dp, vertical = 25.dp),
            contentAlignment = Alignment.Center,

            ) {
            Text(text = text, color = textColor, fontSize = 20.sp)
        }

    }

}


@Composable
fun CustomCircleIndicator() {
    val maxChar = 3
    var value by remember {
        mutableStateOf(0)
    }
    CustomComponent(
        indicatorValue = value,
        //backgroundIndicatorStrokeWidth = 50f
    )

    TextField(
        value = value.toString(),
        onValueChange = {
            value = if (it.isNotEmpty() && it.length <= maxChar) {
                it.toInt()
            } else {
                0
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )

}

//Color Change With UI State
@SuppressLint("UnrememberedMutableState")
@Composable
fun ColorBox(modifier: Modifier = Modifier) {
    val color = remember {
        mutableStateOf(Color.Yellow)
    }

    Box(
        modifier = modifier
            .background(color.value)
            .clickable {
                color.value = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    alpha = 1f

                )
            }
    )
}

@Composable
fun ScreenSwitch() {
    var checked by remember {
        mutableStateOf(false)
    }
    MySwitch(checked = checked, onChange = {
        checked = it
    })
    if (checked) {
        Text(text = "its checked")
    } else {
        Text(text = "not Checked")
    }

}

@Composable
fun MySwitch(
    checked: Boolean,
    onChange: (Boolean) -> Unit
) {
    Switch(
        checked = checked,
        onCheckedChange = {
            onChange(it)
        })

}

@Composable
fun SwitchButton(
    checked: Boolean,
    onChanged: (Boolean) -> Unit

) {
    Switch(checked = checked, onCheckedChange = {
        onChanged(it)
    })
}

@Composable
fun UseSwitch() {
    val checked = remember {
        mutableStateOf(true)
    }
    SwitchButton(checked = checked.value, onChanged = {
        checked.value = it
    })
    if (checked.value) {
        Column {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Column {
                    Button(onClick = { /*TODO*/ })
                    {
                        Text(text = "Button1")

                    }
                    Button(onClick = { /*TODO*/ })
                    {
                        Text(text = "Button2")

                    }
                    Button(onClick = { /*TODO*/ })
                    {
                        Text(text = "Button3")

                    }
                }
            }

        }

    } else {

        //Text(text = "test")
    }

}







