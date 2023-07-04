package com.example.dicegamecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.dicegamecompose.ui.theme.AppTheme
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
    val diceValue1 = remember { mutableStateOf(6) }
    val diceValue2 = remember { mutableStateOf(6) }
    val hasRolled = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Player", modifier = Modifier.padding(10.dp))
                DieCast(diceValue1.value)
                Divider(
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                    thickness = 1.dp,
                    color = Color.Black
                )
                Text("Computer", modifier = Modifier.padding(10.dp))
                DieCast(diceValue2.value)
                Button(
                    onClick = {
                        diceValue1.value = rollDice()
                        diceValue2.value = rollDice()
                        showDialog.value = true
                    }
                ) {
                    Text(text = "ROLL!")
                }
                if (showDialog.value) {
                    println(hasRolled.value)
                    ShowRollResult(
                        value1 = diceValue1.value,
                        value2 = diceValue2.value,
                        onDismiss = {showDialog.value = false}
                    )
                    hasRolled.value = false
                }

            }

        }
    }
}

@Composable
fun ShowRollResult(value1: Int, value2: Int, onDismiss: () -> Unit) {
    val winText: String = when {
        value1 > value2 -> "Player 1 wins!"
        value1 < value2 -> "Player 2 wins!"
        else -> "Draw"
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text ="Result") },
        text = { Text(winText) },
        confirmButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("OK")
            }

        }
    )


}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    DiceGameComposeTheme {
        MainContent()
    }
}

@Composable
fun DieCast(n: Int) {
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
            .size(300.dp)
    )
}


fun rollDice() : Int {
    return Random.nextInt(1,7)
}


