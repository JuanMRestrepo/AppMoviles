package com.unieventos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.components.TextFieldForm

@Composable
fun RestartPassword2(
    navigateToHome: () -> Unit,
    navigateToRestart1: () -> Unit,
    navigateToLogIn: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE6E6E6)),
        ) {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0xFEE53935)),
                contentAlignment = Alignment.Center
            ) {
                Row (
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton (
                        onClick = {
                            navigateToRestart1()
                        }
                    ) {
                        Icon(
                            painter = painterResource(
                                id = android.R.drawable.ic_menu_revert),
                            contentDescription = stringResource(id = R.string.returnIcon),
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            navigateToHome()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = stringResource(id = R.string.homeIcon),
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Surface(
                    modifier = Modifier
                        .width(355.dp)
                        .height(740.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(40.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val changeLbl = stringResource(id = R.string.changePasswordLbl)

                        Box(
                            modifier = Modifier
                                .width(280.dp)
                                .height(180.dp),
                            contentAlignment = Alignment.Center
                        )
                        {

                            Image(
                                painter = painterResource(id = R.drawable.save_report),
                                contentDescription = stringResource(id = R.string.imageLogo),
                                modifier = Modifier
                                    .width(190.dp)
                                    .height(190.dp)
                            )

                            HorizontalDivider(
                                color = Color(0xFEE53935),
                                thickness = 1.dp,
                                modifier = Modifier.
                                    padding(top = 140.dp)

                            )
                        }

                        Text(
                            text = stringResource(id = R.string.recoverLabel),
                            color = Color(0xFEE53935),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(top = 15.dp)
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = stringResource(id = R.string.enterPasswordLbl),
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

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

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(id = R.string.enterPassword2Lbl),
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextFieldForm(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            supportingText = stringResource(id = R.string.passwordValidation),
                            label = stringResource(id = R.string.passwordConfLabel),
                            onValidate = {
                                password.length < 6
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            isPassword = true
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            modifier = Modifier.width(300.dp)
                                .align(alignment = Alignment.Start)
                                .padding(start = 30.dp),
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color(0xFFFF4A3D))) {
                                    append(stringResource(id = R.string.importantLbl))
                                }
                                withStyle(style = SpanStyle(color = Color.Black)) {
                                    append(stringResource(id = R.string.restText))
                                }
                            },
                            fontSize = 15.sp,
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .padding(top = 30.dp)
                                .height(60.dp),
                            onClick = {
                                /* ¿Recuperación de contraseña exitosa? volvemos al inicio
                                *  para iniciar sesión con las credenciales nuevas */
                                navigateToLogIn()
                            },
                        ) {
                            Text(changeLbl, color = Color.White,
                                fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
