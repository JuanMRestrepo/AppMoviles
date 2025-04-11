package com.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R

@Composable
fun SettingsTab() {

    val settings = stringResource(id = R.string.spanishLbl)
    var isDarkMode by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf(settings) }

    val coral = Color(0xFFFF6F61)

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column (modifier = Modifier.fillMaxWidth().background(Color.White)){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(start = 15.dp, top = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.settingsLbl),
                    color = Color(0xFFFF4A3D),
                    fontSize = 25.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding( top = 40.dp),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .width(340.dp)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        stringResource(id = R.string.languageLbl),
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            language + (if (language == "Español") "(usa)" else ""),
                            modifier = Modifier.weight(1f),
                            color = if (isDarkMode) Color.LightGray else Color.DarkGray
                        )
                    }

                    Divider(color = if (isDarkMode) Color.DarkGray else Color.LightGray.copy(alpha = 0.3f))

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        stringResource(id = R.string.locationLbl),
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(id = R.string.colombiaLbl),
                            modifier = Modifier.weight(1f),
                            color = if (isDarkMode) Color.LightGray else Color.DarkGray
                        )
                    }

                    Divider(color = if (isDarkMode) Color.DarkGray else Color.LightGray.copy(alpha = 0.3f))
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}