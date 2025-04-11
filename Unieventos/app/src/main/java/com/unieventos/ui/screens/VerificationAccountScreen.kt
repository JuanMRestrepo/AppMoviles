package com.unieventos.ui.screens

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.components.ButtonHandlingSing
import com.unieventos.ui.components.ItemText
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.ui.components.topBars.TopBarDefect

@Composable
fun VerificationAccountScreen(
    onNavigateBack: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToLogIn: () -> Unit
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE6E6E6)),
        ) {

            var code by rememberSaveable { mutableStateOf("") }
            val verificationLbl = stringResource(id = R.string.verificationLbl);
            val verificationLbl2 = stringResource(id = R.string.verificationLbl2);

            TopBarDefect(
                onNavigateBack,
                navigateToHome = navigateToHome
            )

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ItemText(
                        supportingText = verificationLbl
                    )
                    ItemText(
                        supportingText = verificationLbl2
                    )
                }

                Surface(
                    modifier = Modifier
                        .padding(top = 110.dp)
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

                        val infoBtnLogin = stringResource(id = R.string.logInBtn)
                        val infoBtnSignup = stringResource(id = R.string.singUpText)
                        val validateAccountLbl = stringResource(id = R.string.validateAccountLbl)

                        ButtonHandlingSing(
                            navigateToLogIn = navigateToLogIn,
                            infoBtnLogin = infoBtnLogin,
                            infoBtnSignup = infoBtnSignup
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = stringResource(id = R.string.enterCodeLbl),
                            color = Color.Black,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.height(60.dp))

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
                            modifier = Modifier.clickable {
                            }
                        )

                        Spacer(modifier = Modifier.height(35.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(top = 30.dp)
                            ,
                            onClick = { navigateToLogIn() },
                        ) {
                            Text(validateAccountLbl, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
