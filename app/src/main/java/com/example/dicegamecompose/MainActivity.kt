package com.example.dicegamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dicegamecompose.ui.theme.DiceGameComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    DiceGameComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val diceValue1 = remember { mutableStateOf(6) }
                val diceValue2 = remember { mutableStateOf(6)}
                Text("Player", modifier = Modifier.padding(10.dp))
                DieCast(
                    diceValue1.value,
                    onClick = { diceValue1.value = rollDice() }
                )
                Divider(
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )
                Text("Computer", modifier = Modifier.padding(10.dp))
                DieCast(
                    diceValue2.value,
                    onClick = { diceValue2.value = rollDice() }
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    DiceGameComposeTheme {
        MainContent()
    }
}

@Composable
fun DieCast(n: Int, onClick: () -> Unit) {
    val drawableRes = when (n) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_3
    }

    Image(
        painter = painterResource(id = drawableRes),
        contentDescription = "Dice Game!",
        modifier = Modifier
            .clickable { onClick() }
            .size(300.dp)
    )
}


fun rollDice() : Int {
    return Random.nextInt(1,7)
}


