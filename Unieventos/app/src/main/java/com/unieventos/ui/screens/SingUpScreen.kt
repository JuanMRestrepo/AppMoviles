package com.unieventos.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.components.DropdownMenu

@Composable
fun SingUpScreen(
    navigateToLogIn: () -> Unit
) {
    Scaffold { padding ->
        SingUpForm(padding, navigateToLogIn)
    }
}

@Composable
fun SingUpForm(padding: PaddingValues, navigateToLogIn: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE6E6E6))
            .padding(padding)
    ) {
        val cities = listOf("Armenia", "Pereira", "Manizales")
        var name by rememberSaveable { mutableStateOf("") }
        var city by rememberSaveable { mutableStateOf("") }
        var addres by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }

        Image(
            painter = painterResource(id = R.drawable.fondo),
            contentDescription = "imagen de fondo",
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                ),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    color = Color(0xFFFF4B3A).copy(alpha = 0.35f),
                    shape = RoundedCornerShape(
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                ),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_report),
                contentDescription = stringResource(id = R.string.imageLogo),

                modifier = Modifier
                    .width(200.dp)
                    .height(220.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .padding(top = 130.dp)
                    .width(355.dp)
                    .height(700.dp),
                color = Color.White,
                shape = RoundedCornerShape(40.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp)
                                    .align(Alignment.CenterStart),
                                colors = ButtonDefaults.buttonColors(Color(0xFFD3CED5)),
                                shape = RoundedCornerShape(20.dp),
                                onClick = {
                                    navigateToLogIn()
                                }
                            ) {
                                Text("Log in", color = Color.White)
                            }
                            Button(
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(50.dp)
                                    .align(Alignment.CenterEnd),
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text("Sign Up", color = Color.White)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Create New Account",
                        color = Color.Black,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") },
                        modifier = Modifier.width(280.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Gray,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    DropdownMenu(
                        value = city,
                        onValueChange = {
                            city = it
                        },
                        items = cities
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = addres,
                        onValueChange = { addres = it },
                        label = { Text(text = stringResource(id = R.string.address)) },
                        modifier = Modifier.width(280.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Gray,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = stringResource(id = R.string.emailLabel)) },
                        modifier = Modifier.width(280.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Gray,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = stringResource(id = R.string.passwordLabel)) },
                        modifier = Modifier.width(280.dp),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Black,
                            unfocusedIndicatorColor = Color.Gray,
                            disabledIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                        shape = RoundedCornerShape(30.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                            .height(60.dp),
                        onClick = {
                            Toast.makeText(context, "Registrado con éxito", Toast.LENGTH_SHORT).show()
                        },
                    ) {
                        Text("Sign Up", color = Color.White)
                    }
                }
            }
        }
    }
}
