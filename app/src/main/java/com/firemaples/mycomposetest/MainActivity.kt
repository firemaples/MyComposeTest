package com.firemaples.mycomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Greetings()
        }
    }
}

@Preview
@Composable
fun FullPreview() {
    var shouldShowOnBoarding by remember { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnBoarding = !shouldShowOnBoarding })
    } else {
        Greetings()
    }
}

@Preview(showBackground = true, name = "Greetings", widthDp = 320)
@Composable
fun MainContent() {
    Greetings()
}

//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "Night")
//@Composable
//fun MainContentDark() {
//    App()
//}

@Composable
fun Greetings(names: List<String> = List(1000) { "#$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        val expended = remember { mutableStateOf(false) }
        val extraPadding = if (expended.value) 48.dp else 0.dp

        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }

            OutlinedButton(onClick = { expended.value = !expended.value }) {
                Text(text = if (expended.value) "Show less" else "Show more")
            }
        }

    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyComposeTestTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MaterialTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}


