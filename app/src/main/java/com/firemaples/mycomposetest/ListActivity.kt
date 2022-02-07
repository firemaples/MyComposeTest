package com.firemaples.mycomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.firemaples.mycomposetest.ui.theme.MyComposeTestTheme
import kotlinx.coroutines.launch

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MyComposeTestTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    SimpleList()
//                }
//            }

            DefaultPreview()
        }
    }
}

@Composable
fun TopButtonBar(scrollToTop: () -> Unit, scrollToEnd: () -> Unit) {
    Row {
        Button(onClick = scrollToTop) {
            Text(text = "Scroll to the top")
        }

        Button(onClick = scrollToEnd) {
            Text(text = "Scroll to the end")
        }
    }
}

@Composable
fun SimpleList(listSize: Int, scrollState: LazyListState) {
    LazyColumn(state = scrollState) {
        items(listSize) { i ->
            ImageListItem(index = i)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { }, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Logo",
            modifier = Modifier.size(50.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    MyComposeTestTheme {
        Surface {
            Column {
                TopButtonBar(
                    scrollToTop = {
                        scope.launch {
                            scrollState.animateScrollToItem(0)
                        }
                    },
                    scrollToEnd = {
                        scope.launch {
                            scrollState.animateScrollToItem(listSize - 1)
                        }
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))

                SimpleList(listSize, scrollState)
            }
        }
    }
}