package com.projects.codelabbasics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
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
    var isSelected by remember {
        mutableStateOf(false)
    }
    val backgroundColor by animateColorAsState(
        targetValue = if(isSelected) MaterialTheme.colors.secondary else MaterialTheme.colors.surface
    )
    Text(
        text = "Hello $name!",
        modifier = Modifier
            .padding(24.dp)
            .clickable { isSelected = !isSelected }
            .background(backgroundColor)
    )
}

@Composable
fun MyScreenContent(names: List<String> = List(1000){ "Android #$it"}) {
    val counterState = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxHeight(1f)) {
        NameList(names = names, modifier = Modifier.weight(1f))
        Counter(count = counterState.value,
            updateCount = {
                counterState.value = it
            })
    }
}

@Composable
fun NameList(names:List<String>,modifier:Modifier){
    LazyColumn(modifier = modifier) {
        items(items = names){
            Greeting(name = it)
            Divider(color = Color.Black)
        }
    }
}

// create a container that has all the common configurations
@Composable
fun MyApp(content: @Composable () -> Unit) {
    CodeLabBasicsTheme {
        Surface(color = MaterialTheme.colors.surface) {
            content()
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = {
        updateCount(count + 1)
    },
    colors = ButtonDefaults.buttonColors(
        if(count>5) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    )) {
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