package com.example.dicegamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.dicegamecompose.ui.theme.DiceGameComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceGameComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        val diceValue = remember { mutableStateOf(1) }
                        DieCast(diceValue.value, onClick = { diceValue.value = rollDice() })

                    }

                }
            }
        }
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
            .clickable { onClick()}
            .fillMaxSize()
    )
}


fun rollDice() : Int {
    return Random.nextInt(1,7)
}

