package com.compose.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.counter.ui.theme.ComposeCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCounterTheme {
                val imagePainter = painterResource(id = R.drawable.bgimg) // add bg img
                // make screen box
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = imagePainter,
                        contentDescription = "Background Image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // make column for counter and buttons
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var count by remember {
                            mutableIntStateOf(0)
                        }
                        var memory by remember {
                            mutableIntStateOf(0)
                        }

                        // animate the counter
                        AnimatedCounter(count = count)
                        // make row for buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // make button for decreasing
                            Button(
                                onClick = {
                                    if (count > 0) {
                                        memory = count--
                                    }
                                },
                                modifier = Modifier.size(75.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.5f))
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 36.sp
                                )
                            }
                            // make button for increasing
                            Button(
                                onClick = {
                                    memory = count++
                                },
                                modifier = Modifier.size(75.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Green.copy(alpha = 0.5f))
                            ) {
                                Text(
                                    text = "+",
                                    fontSize = 36.sp
                                )
                            }
                        }
                        // create new row to add reset below operator buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // make button for reset
                            Button(
                                onClick = {
                                    // set memory to count and then reset it
                                    memory = count
                                    count = 0
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                            ) {
                                Text(
                                    text = "Reset",
                                    color = Color.Black
                                )
                            }
                        }
                        // create new row to add undo reset below reset button
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    // bring back old value of count
                                    count = memory
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                            ) {
                                Text(
                                    text = "Revert",
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}