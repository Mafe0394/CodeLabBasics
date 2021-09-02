package com.projects.codelabbasics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.codelabbasics.ui.theme.CodeLabBasicsTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(24.dp)
    )
}

@Composable
fun MyScreenContent(names: List<String> = listOf("Android", "there")) {
    val counterState= remember{ mutableStateOf(0)}
    Column {
        for (name in names) {
            Greeting(name = name)
            Divider(color = Color.Black)
        }
        Divider(color = Color.Transparent,thickness = 32.dp)
        Counter(count = counterState.value,
        updateCount = {
            counterState.value=it
        })
    }
}

// create a container that has all the common configurations
@Composable
fun MyApp(content: @Composable () -> Unit) {
    CodeLabBasicsTheme {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun Counter(count:Int,updateCount:(Int)->Unit) {
//    var count by remember {
//        mutableStateOf(1)
//    }
//    val count=remember{ mutableStateOf(0)}
//    Button(onClick = {
//        count.value++
//    }) {
//        Text("Clicked ${count.value} times")
//    }
    Button(onClick = {
        updateCount(count+1)
    }) {
        Text("Clicked ${count} times")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}