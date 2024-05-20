package tw.edu.pu.csim.hsiien.a411146376

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.edu.pu.csim.hsiien.a411146376.ui.theme._411146376Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _411146376Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    Main()
                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController){
    val context = LocalContext.current  //取得App的運行環境
    var appear by remember { mutableStateOf(true) }  //背景出現
    var expanded by remember { mutableStateOf(true) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    )
    {
        Text(text = "瑪利亞基金會服務總覽" ,
            color = Color.Blue
        )
        AnimatedVisibility(
            visible = appear,
            exit = fadeOut(
                animationSpec = tween(durationMillis = 10000)
            )

        ) {

            Image(
                painterResource(id = R.drawable.service),
                contentDescription = "button icon",
                modifier = Modifier.size(500.dp)
            )
        }
        Button(onClick = {
            appear = false
            navController.navigate("JumpSecond")
        }) {
            Text(text = "作者:資管系蔡譯嫺")
        }

    }
}


@Composable
fun SecondScreen(navController: NavController) {
    val context = LocalContext.current  //取得App的運行環境
    var appear by remember { mutableStateOf(true) }  //背景出現
    var expanded by remember { mutableStateOf(true) }  //背景延展


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "關於App作者",
            color = Color.Red
        )
        AnimatedVisibility(
            visible = appear,
            enter = fadeIn(animationSpec = tween(durationMillis = 10000))

        ) {
            Image(
                painterResource(id = R.drawable.myself),
                contentDescription = "button icon",
                modifier = Modifier.size(500.dp)
            )
        }
        Button(onClick = {
            appear = true
            navController.navigate("JumpFirst")
        }) {
            Text(text = "服務總覽")
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }
    _411146376Theme {
        // Ensure that the TopAppBar is included in the Surface
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TopAppBar(
                    title = {
                        Image(
                            painter = painterResource(id = R.drawable.maria),
                            contentDescription = "button icon",
                            modifier = Modifier.size(120.dp)
                        )
                    },
                    actions = {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "More")
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("簡介") },
                                onClick = {
                                    navController.navigate("JumpFirst")
                                    showMenu = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("主要機構") },
                                onClick = {
                                    val intent = Intent(context, SecondActivity::class.java)
                                    context.startActivity(intent)
                                    showMenu = false
                                }
                            )
                        }
                    }
                )

                NavHost(navController = navController, startDestination = "JumpFirst") {
                    composable("JumpFirst") {
                        FirstScreen(navController = navController)
                    }
                    composable("JumpSecond") {
                        SecondScreen(navController = navController)
                    }
                }
            }
        }
    }
}

