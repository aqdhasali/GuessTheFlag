/* Aqdhas Ali
* IIT ID: 20210860
* UOW ID: w1954000
* Video Link : https://drive.google.com/file/d/1cO7N9V7Tc35xTBQFsk5lWFiHHwPnJcrt/view?usp=sharing*/
package com.example.guesstheflag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guesstheflag.ui.theme.GuessTheFlagTheme
import com.example.guesstheflag.ui.theme.Poppins


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessTheFlagTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Display()
                }
            }
        }
    }

    /*Function to display the 4 buttons*/
    @Preview
    @Composable
    fun Display(){
        //Declaring variables
        val context = LocalContext.current
        var switchState by remember { mutableStateOf(false) } //variable to hold the state of the switch



        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEFB8C8)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Text(
                text = "FLAG GAMES",
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6650a4),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Row(Modifier.width(200.dp),
                horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp)
                    ,
                    onClick = {
                        val i = Intent(context,GuessCountry :: class.java)
                        i.putExtra("SWITCH_STATE", switchState)
                        context.startActivity(i)
                    },
                    colors  = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4)
                    ),
                    shape = RoundedCornerShape(5.dp)
                )
                {
                    Text(
                        text = "Guess The Country",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    onClick = {
                        val i = Intent(context,GuessHints :: class.java)
                        i.putExtra("SWITCH_STATE", switchState)
                        context.startActivity(i)
                    },
                    colors  = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4)
                    ),
                    shape = RoundedCornerShape(5.dp)
                )
                {
                    Text(
                        text = "Guess Hints",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    onClick = {
                        val i = Intent(context,GuessFlag :: class.java)
                        i.putExtra("SWITCH_STATE", switchState)
                        context.startActivity(i)
                    },
                    colors  = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4)
                    ),
                    shape = RoundedCornerShape(5.dp)
                )
                {
                    Text(
                        text = "Guess The Flag",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    onClick = {
                        val i = Intent(context,AdvancedLevel :: class.java)
                        i.putExtra("SWITCH_STATE", switchState)
                        context.startActivity(i)
                    },
                    colors  = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6650a4)
                    ),
                    shape = RoundedCornerShape(5.dp)
                )
                {
                    Text(
                        text = "Advanced Level",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Timer",
                color = Color(0xFF6650a4),
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
            Switch(        //https://developer.android.com/reference/android/widget/Switch
                checked = switchState,
                onCheckedChange = {isChecked ->
                    switchState = isChecked
                },
                modifier = Modifier
                    .padding(16.dp),
                colors = SwitchDefaults.colors(  //https://kotlinandroid.org/android-jetpack-compose-switch-colors/
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )



        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    GuessTheFlagTheme {

    }
}


