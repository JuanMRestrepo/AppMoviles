package com.unieventos.ui.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.ui.components.topBars.TopBarDefect

@Composable
fun RestartPassword1(
    onNavigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToRestart2: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE6E6E6)),
        ) {

            var email by rememberSaveable { mutableStateOf("") }
            var code by rememberSaveable { mutableStateOf("") }
            val sendCode = stringResource(id = R.string.sendCodeLbl);
            val validateAccount = stringResource(id = R.string.validateAccountLbl);

            TopBarDefect (
                onNavigateBack,
                navigateToHome = navigateToHome
            )

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

                        Box(
                            modifier = Modifier
                                .width(280.dp)
                                .height(120.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.save_report),
                                contentDescription = stringResource(id = R.string.imageLogo),
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(90.dp)
                            )

                            HorizontalDivider(
                                color = Color(0xFEE53935),
                                thickness = 1.dp,
                                modifier = Modifier.
                                padding(top = 70.dp)
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.recoverLabel),
                            color = Color(0xFEE53935),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .padding(start = 40.dp)
                                .align(alignment = Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Text(
                            text = stringResource(id = R.string.yourEmailLabel),
                            color = Color.Black,
                            fontSize = 13.sp,
                            modifier = Modifier
                                .padding(start = 40.dp)
                                .align(alignment = Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

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

                        Button(
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .width(160.dp)
                                .padding(top = 20.dp)
                                .height(50.dp),
                            onClick = {

                            },
                        ) {
                            Text(sendCode, color = Color.White,
                                fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            text = stringResource(id = R.string.enterCodeLbl),
                            color = Color.Black,
                            fontSize = 13.sp,
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        TextFieldForm(
                            value = code,
                            onValueChange = {
                                code = it
                            },
                            supportingText = stringResource(id = R.string.codeValidation),
                            label = stringResource(id = R.string.codeSent),
                            onValidate = {
                                !Patterns.EMAIL_ADDRESS.matcher(it).matches()
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            isPassword = false
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Text(
                            text = stringResource(id = R.string.resendCode),
                            color = Color(0xFFFF4B3A),
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable { }
                        )

                        Spacer(modifier = Modifier.height(35.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .width(300.dp)
                                .height(60.dp),
                            onClick = {
                                navigateToRestart2()
                            },
                        ) {
                            Text(validateAccount, color = Color.White,
                                fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

