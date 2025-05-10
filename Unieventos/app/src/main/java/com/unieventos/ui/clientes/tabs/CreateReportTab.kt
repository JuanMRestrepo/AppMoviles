package com.unieventos.ui.clientes.tabs

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.unieventos.R
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.model.ReportState
import com.unieventos.ui.clientes.componentsClient.ImageUploader
import com.unieventos.ui.components.DropdownMenu
import com.unieventos.ui.components.MapComposable
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.SharedPreferencesUtils

@Composable
fun CreateReportTab(
    navigateToLogIn: () -> Unit,
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {

        val context = LocalContext.current

        val reportsViewModel = LocalMainViewModel.current.reportsViewModel
        val newReport = remember { Report() }

        val userMap = SharedPreferencesUtils.getPreference(context)

        var titleLbl = stringResource(id = R.string.titleLbl)
        var descriptionLbl = stringResource(id = R.string.descriptionLbl)
        val uploadImageLbl = stringResource(id = R.string.uploadImageLbl)
        val selectLbl = stringResource(id = R.string.selectLbl)
        val createNewReportLbl = stringResource(id = R.string.createLbl)

        var title by rememberSaveable { mutableStateOf("") }
        var description by rememberSaveable { mutableStateOf("") }
        var category by rememberSaveable { mutableStateOf("") }
        var locationSelected by rememberSaveable { mutableStateOf(false) }
        var showError by rememberSaveable { mutableStateOf(false) }
        var errorMessage by rememberSaveable { mutableStateOf("") }

        val categories = listOf(
            "Security",
            "Medical emergencies",
            "Infrastructure",
            "Lighting",
            "Pets",
            "Community"
        )

        var mapViewportState = rememberMapViewportState {
            setCameraOptions {
                zoom(9.0)
                center( Point.fromLngLat(-75.67, 4.53))
            }
        }

        // Actualización dinámica del objeto newReport
        LaunchedEffect(title, description, category) {
            newReport.title = title
            newReport.description = description
            newReport.category = category
            newReport.idUser = userMap.get("userId") ?: ""
            newReport.state = ReportState.PENDING
        }

        // Función para validar todos los campos
        fun validateFields(): Boolean {
            return when {
                title.isBlank() -> {
                    errorMessage = "El título es obligatorio"
                    false
                }
                description.isBlank() -> {
                    errorMessage = "La descripción es obligatoria"
                    false
                }
                category.isBlank() -> {
                    errorMessage = "Debes seleccionar una categoría"
                    false
                }
                !locationSelected -> {
                    errorMessage = "Debes seleccionar una ubicación en el mapa"
                    false
                }
                else -> true
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.newReportLbl),
            color = Color.Red,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier.width(350.dp)
        ){
            Column() {

                var uploadedImageUri by remember { mutableStateOf<Uri?>(null) }

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                        showError = false
                    },
                    label = { Text(titleLbl) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    isError = showError && title.isBlank()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                        showError = false
                    },
                    label = { Text(descriptionLbl) },
                    modifier = Modifier
                        .fillMaxWidth(),
                    isError = showError && description.isBlank()
                )

                Spacer(modifier = Modifier.height(16.dp))

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    value = category,
                    onValueChange = {
                        category = it
                        showError = false
                    },
                    items = categories,
                    message = R.string.selectLblCategory,
                    isError = showError && category.isBlank()
                )

                Spacer(modifier = Modifier.height(10.dp))

                ImageUploader (
                    modifier = Modifier.fillMaxWidth()
                ){ uri ->
                    uploadedImageUri = uri
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(selectLbl, modifier = Modifier.weight(1f))
                    Icon(Icons.Outlined.Place, contentDescription = uploadImageLbl, tint = Color.Black)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                ){
                    MapComposable(
                        modifier = Modifier.fillMaxSize(),
                        mapViewportState = mapViewportState,
                        centerUserLocation = true,
                        reports = listOf(),
                        activateClick = true,
                        onMapClickListener = { point ->
                            locationSelected = true
                            showError = false
                            newReport.location = Location(point.latitude(), point.longitude(), "Armenia")
                        }
                    )
                }

                // Mostrar mensaje de error si hay campos faltantes
                if (showError) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(top = 30.dp)
                    ,
                    onClick = {
                        if (validateFields()) {
                            reportsViewModel.createReport(newReport)
                            Toast.makeText(context, "Report created", Toast.LENGTH_SHORT).show()
                            navigateToLogIn()
                        } else {
                            showError = true
                        }
                    },
                ) {
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(createNewReportLbl, color = Color.White)
                }
            }
        }
    }
}
