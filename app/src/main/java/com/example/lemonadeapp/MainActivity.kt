package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.Green
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme
import com.example.lemonadeapp.ui.theme.Yellow
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Yellow)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontWeight = FontWeight.Bold
        )
    }

    var currentStep by remember {
        mutableStateOf(1)
    }

    var lemonSqueezeCount by remember {
        mutableStateOf(0)
    }

    when (currentStep) {
        1 -> {
            TextAndImage(
                img = painterResource(R.drawable.lemon_tree),
                imgDesc = stringResource(R.string.lemon_tree),
                text =stringResource(R.string.lemon_tree_text),
                onImageClick = {
                    currentStep = 2
                    lemonSqueezeCount = (2..4).random()
                }
            )
        }
        2 -> {
            TextAndImage(
                img = painterResource(R.drawable.lemon_squeeze),
                imgDesc = stringResource(R.string.lemon),
                text = stringResource(R.string.lemon_text),
                onImageClick = {
                    lemonSqueezeCount--
                    if(lemonSqueezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
        }
        3 -> {
            TextAndImage(
                img = painterResource(R.drawable.lemon_drink),
                imgDesc = stringResource(R.string.lemonade),
                text = stringResource(R.string.lemonade_text),
                onImageClick = {
                    currentStep = 4
                }
            )
        }
        4 -> {
            TextAndImage(
                img = painterResource(R.drawable.lemon_restart),
                imgDesc = stringResource(R.string.empty_glass),
                text = stringResource(R.string.empty_glass_text),
                onImageClick = {
                    currentStep = 1
                }
            )
        }
    }
}

@Composable
fun TextAndImage(
    img: Painter,
    imgDesc: String,
    text: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                colors = ButtonDefaults.buttonColors(Green),
                shape = RoundedCornerShape(20)
            ) {
                Image(
                    painter = img,
                    contentDescription = imgDesc,
                    Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = text,
                fontSize = 18.sp
            )
        }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}