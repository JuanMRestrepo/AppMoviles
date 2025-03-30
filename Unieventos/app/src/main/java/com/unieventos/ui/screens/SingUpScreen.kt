package com.unieventos.ui.screens

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.unieventos.ui.components.DropdownMenu
import com.unieventos.ui.components.TextFieldForm
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SingUpScreen(){
    val context = LocalContext.current

    Scaffold { padding ->
        SingUpForm(
            padding = padding,
            context = context
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingUpForm(
    padding: PaddingValues,
    context: Context
){
    val ciudades = listOf("Armenia", "Pereira", "Manizales")

    var nombre by rememberSaveable { mutableStateOf("") }

    var ciudad by rememberSaveable { mutableStateOf("") }

    var fechaNacimiento by rememberSaveable { mutableStateOf("") }
    var showDatePicker by rememberSaveable { mutableStateOf(false) }

    var datePickerState = rememberDatePickerState()
    Column (
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextFieldForm(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            supportingText = "El nombre no puede estar vacio",
            label = "Nombre",
            onValidate = { nombre.isBlank() },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = false
        )

        Spacer(modifier = Modifier.height(15.dp))

        //Composable para el combo

        DropdownMenu(
            value = ciudad,
            onValueChange = {
                ciudad = it
            },
            items = ciudades
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = fechaNacimiento,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = "Fecha de nacimiento")
            },
            trailingIcon = {
                IconButton(
                    onClick = { showDatePicker = true }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = "Icono de fecha de nacimiento"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                Toast.makeText(context, "En construccion", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon (
                imageVector = Icons.Rounded.Face,
                contentDescription = "Icono de usuario"
            )
            Text(text = "Registro")
        }

        if(showDatePicker){
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {

                            val selectedDate = datePickerState.selectedDateMillis

                            if(selectedDate != null){
                                val date = Date(selectedDate)
                                val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
                                fechaNacimiento = formattedDate
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text(text="Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                        }
                    ) {
                        Text(text="Cancelar")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
