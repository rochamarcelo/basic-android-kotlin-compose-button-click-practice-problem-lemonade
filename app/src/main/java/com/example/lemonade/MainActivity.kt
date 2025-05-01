package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var stageIndex by remember { mutableIntStateOf(0) }
    val stages by remember { mutableStateOf(listOf(
        LemonadeStage(imageResource = R.drawable.lemon_tree, textResource = R.string.stage_1_text, 1),
        RandomTouchLemonadeStage(imageResource = R.drawable.lemon_squeeze, textResource = R.string.stage_2_text, 2),
        LemonadeStage(imageResource = R.drawable.lemon_drink, textResource = R.string.stage_3_text, 3),
        LemonadeStage(imageResource = R.drawable.lemon_restart, textResource = R.string.stage_4_text, 0),
    ))}
    val stage = stages[stageIndex];
    val moveNext = {
        if (stage.touch()) {
            stageIndex = stage.nextStageIndex;
        }
    }
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.nature_1)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            onClick = { moveNext() },
            shape = RoundedCornerShape(35.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.nature_5),
                contentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified,
            )
        ) {
            Image(
                painter = painterResource(stage.imageResource),
                contentDescription = "Lemon Tree",
            )
        }
        Text(
            text = stringResource(stage.textResource),
            fontFamily = FontFamily.Serif,
            fontSize = 15.sp,
            color = colorResource(R.color.nature_3),
            modifier = Modifier.padding(top = 20.dp),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}