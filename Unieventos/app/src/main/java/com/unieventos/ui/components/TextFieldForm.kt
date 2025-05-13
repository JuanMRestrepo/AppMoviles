package com.unieventos.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun TextFieldForm(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange:(String) -> Unit,
    supportingText:String,
    label:String,
    onValidate:(String) -> Boolean,
    keyboardOptions: KeyboardOptions,
    isPassword:Boolean,
    trailingIcon: @Composable (() -> Unit)? = null,
    showPassword: Boolean = false
){

    var isError by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = modifier,
        value = value,
        isError = isError,
        singleLine = true,
        supportingText = {
            if(isError){
                Text(text = supportingText)
            }
        },
        visualTransformation = if (isPassword && !showPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        label = {
            Text(text = label)
        },
        onValueChange = {
            onValueChange(it)
            isError = onValidate(it)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Black,
            errorIndicatorColor = Color.Red,
            unfocusedIndicatorColor = Color.Gray,
            disabledIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            disabledContainerColor = Color.White
        )
    )
}
