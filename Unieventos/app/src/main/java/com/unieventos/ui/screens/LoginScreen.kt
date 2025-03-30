package com.unieventos.ui.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.components.TextFieldForm

@Composable
fun LoginScreen() {

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xF3FFFFFF))
        ) {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            Image(
                painter = painterResource(id = R.drawable.fondo),
                contentDescription = "iamgen de fondo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
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
                    .height(350.dp)
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
                        .offset(y = (-60).dp)
                )
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Surface(
                    modifier = Modifier
                        .padding(top = 270.dp)
                        .width(355.dp)
                        .height(560.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(40.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ){
                                Button(
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(50.dp)
                                        .align(Alignment.CenterEnd),
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(Color(0xFFD3CED5)),
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Text("Sign Up", color = Color.White)
                                }
                                Button(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(50.dp)
                                        .align(Alignment.CenterStart),
                                    colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                                    shape = RoundedCornerShape(20.dp),
                                    onClick = {
                                    }
                                ) {
                                    Text("Log in", color = Color.White)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Welcome to Save Report",
                            color = Color.Black,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 30.dp)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        TextFieldForm(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            supportingText = stringResource(id = R.string.emailValidation),
                            label = stringResource(id = R.string.emailLabel),
                            onValidate = {
                                !Patterns.EMAIL_ADDRESS.matcher(it).matches()
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            isPassword = false
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextFieldForm(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            supportingText = stringResource(id = R.string.passwordValidation),
                            label = stringResource(id = R.string.passwordLabel),
                            onValidate = {
                                password.length < 6
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            isPassword = true
                        )

                        Button(
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp)
                                .height(60.dp),
                            onClick = {

                            },
                        ) {
                            Text("Log in", color = Color.White)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Forgot Password?",
                            color = Color.Black,
                            modifier = Modifier.clickable {
                                /* Acción de singup */
                            }
                        )
                    }
                }
            }
        }
    }
}

