package com.unieventos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.unieventos.ui.components.ItemEvento
import com.unieventos.ui.components.ItemPersona

@Composable
fun HomeScreen(){
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            ItemEvento(
                nombre = "Concierto de Cris Valencia",
                ciudad = "Armenia",
            )
            ItemEvento(
                nombre = "Obra de teatro humor",
                ciudad = "Pereira",
            )
            ItemEvento(
                nombre = "Concierto de banda de rock ADC",
                ciudad = "Armenia",
            )
        }
    }
}