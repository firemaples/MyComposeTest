package com.firemaples.mycomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firemaples.mycomposetest.ui.theme.MyComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FullPreview()
        }
    }
}

@Preview
@Composable
fun FullPreview() {
    MyComposeTestTheme {
        var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

        if (shouldShowOnBoarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnBoarding = !shouldShowOnBoarding })
        } else {
            Greetings()
        }
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
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        var expended by rememberSaveable { mutableStateOf(false) }
//        val extraPadding by animateDpAsState(
//            targetValue = if (expended) 48.dp else 0.dp,
//            animationSpec = spring(
//                dampingRatio = Spring.DampingRatioMediumBouncy,
//                stiffness = Spring.StiffnessLow,
//            )
//        )

        Row(
            modifier = Modifier
                .padding(24.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow,
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(text = name)

                if (expended) {
                    Text(
                        text = "However, hard-coding strings is a bad practice and you should get them from the strings.xml file.\n" +
                                "\n" +
                                "You can use \"Extract string resource\" on each string, available in \"Context Actions\" in Android Studio to do this automatically.\n" +
                                "\n" +
                                "Alternatively, open app/src/res/values/strings.xml and add the following resources:"
                    )
                }
            }

            IconButton(onClick = { expended = !expended }) {
                val text =
                    if (expended) stringResource(R.string.txt_show_less)
                    else stringResource(R.string.txt_show_more)
                Icon(
                    imageVector = if (expended) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = text
                )
            }
//            OutlinedButton(onClick = { expended = !expended }) {
//                Text(text = if (expended) "Show less" else "Show more")
//            }
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


