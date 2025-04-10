package com.unieventos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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

    var isDarkMode by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf("Español") }
    //var dataSaving by remember { mutableStateOf(true) }

    val coral = Color(0xFFFF6F61)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (isDarkMode) Color(0xFF121212) else Color(0xFFFFF5F5)
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

                    // Language Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Language",
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                            .clickable {
                                // Toggle between English and Spanish
                                language = if (language == "Español") "English" else "Español"
                            },
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
                        "Location",
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
                            "Colombia",
                            modifier = Modifier.weight(1f),
                            color = if (isDarkMode) Color.LightGray else Color.DarkGray
                        )
                    }

                    Divider(color = if (isDarkMode) Color.DarkGray else Color.LightGray.copy(alpha = 0.3f))

                    /*
                    Text(
                        "Appearance",
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Choose dark or light theme",
                            modifier = Modifier.weight(1f),
                            color = if (isDarkMode) Color.LightGray else Color.DarkGray
                        )
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { isDarkMode = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = coral,
                                checkedTrackColor = coral.copy(alpha = 0.5f)
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Accessibility",
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
                            "Accessibility options",
                            modifier = Modifier.weight(1f),
                            color = if (isDarkMode) Color.LightGray else Color.DarkGray
                        )
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = "Forward",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Divider(color = if (isDarkMode) Color.DarkGray else Color.LightGray.copy(alpha = 0.3f))

                    // Data Saving Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Data saving",
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                "Automatically adjust settings to save mobile data",
                                modifier = Modifier.weight(1f),
                                color = if (isDarkMode) Color.LightGray else Color.DarkGray
                            )
                            Switch(
                                checked = dataSaving,
                                onCheckedChange = { dataSaving = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = coral,
                                    checkedTrackColor = coral.copy(alpha = 0.5f)
                                )
                            )
                        }
                    }

                    Divider(color = if (isDarkMode) Color.DarkGray else Color.LightGray.copy(alpha = 0.3f))

                    // Send Feedback Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Send feedback",
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.clickable { /* Acción para enviar feedback */ }
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(color = if (isDarkMode) Color.DarkGray else Color.LightGray.copy(alpha = 0.3f))

                    // About Section
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "About",
                        color = coral,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.clickable { /* Acción para mostrar información */ }
                    )

                     */
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}