package com.unieventos.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldForm(
    modifier: Modifier = Modifier,
    value:String,
    onValueChange:(String) -> Unit,
    supportingText:String,
    label:String,
    onValidate:(String) -> Boolean,
    keyboardOptions: KeyboardOptions,
    isPassword:Boolean
){

    var isError by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        isError = isError,
        supportingText = {
            if(isError){
                Text(text = supportingText)
            }
        },

        visualTransformation = if(isPassword) {PasswordVisualTransformation()} else {VisualTransformation.None},
        keyboardOptions = keyboardOptions,
        label = {
            Text(text = label)
        },
        onValueChange = {
            onValueChange(it)
            isError = onValidate(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedSupportingTextColor = Color.Black,
            unfocusedSupportingTextColor = Color.Black
        )
    )
}