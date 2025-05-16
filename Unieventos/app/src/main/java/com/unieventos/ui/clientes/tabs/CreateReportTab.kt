package com.unieventos.ui.clientes.tabs

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
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
import com.unieventos.ui.components.AlertMessage
import com.unieventos.ui.components.AlertType
import com.unieventos.ui.components.DropdownMenu
import com.unieventos.ui.components.MapComposable
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils
import com.unieventos.viewmodel.ReportsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

@Composable
fun CreateReportTab(
    navigateBack: () -> Unit,
){
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {

        val context = LocalContext.current

        val reportsViewModel = LocalMainViewModel.current.reportsViewModel
        val reportResult by reportsViewModel.reportResult.collectAsState()

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
        var imagesSelected = remember { mutableStateListOf<String>() }

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
        LaunchedEffect(title, description, category, imagesSelected) {
            newReport.title = title
            newReport.description = description
            newReport.category = category
            newReport.idUser = userMap.get("userId") ?: ""
            newReport.state = ReportState.PENDING
            newReport.images = imagesSelected.toList() // Asegúrate que esto se actualice
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
                imagesSelected.isEmpty() -> {  // Nueva validación
                    errorMessage = "Debes subir al menos una imagen"
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

                ReportImages(
                    reportsViewModel = reportsViewModel,
                    reportResult = reportResult,
                    context = context,
                    onImageSelected = {
                        imagesSelected.add(it)
                        showError = false
                    },
                    onNavigateBack = navigateBack
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ){
                    if (imagesSelected.isNotEmpty()) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(vertical = 8.dp)
                        ) {
                            items(imagesSelected) { imageUrl ->
                                AsyncImage(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .padding(end = 8.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    model = imageUrl,
                                    contentDescription = "Imagen del reporte"
                                )
                            }
                        }
                    }
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
                            newReport.images = imagesSelected.toList() // Esto es lo más sencillo
                            reportsViewModel.createReport(newReport)
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


@Composable
fun ReportImages(
    reportsViewModel: ReportsViewModel,
    reportResult: RequestResult?,
    context: Context,
    onImageSelected: (String) -> Unit,
    onNavigateBack: () -> Unit,
){

    var image by remember { mutableStateOf("") }

    val config = mapOf(
        "cloud_name" to "dh7g3xwjb",
        "api_key" to "778739682889816",
        "api_secret" to "gjF2ZZqghHezSoUBwVK6-NsNNY4"
    )

    val scope = rememberCoroutineScope()

    val cloudinary = Cloudinary(config)

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri:Uri? ->
        uri?.let {
            scope.launch(Dispatchers.IO) {
                val inputStream = context.contentResolver.openInputStream(it)
                inputStream?.use { stream ->
                    val result = cloudinary.uploader().upload(stream, ObjectUtils.emptyMap())
                    val imageUrl = result["secure_url"].toString()
                    image = imageUrl
                    onImageSelected(imageUrl)
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ){
        if(it){
            Toast.makeText(context, "Permiso concedido", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                val permissionCheckResult = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES)
                }else {
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)

                }

                if(permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                    fileLauncher.launch("image/*")
                }else {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                    }else {
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }
            }
        ) {
            Text (
                text = "Seleccionar imagen"
            )
        }

        when(reportResult){
            null ->{
            }
            is RequestResult.Success ->{
                AlertMessage(
                    modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                    type = AlertType.SUCCESS,
                    message = (reportResult as RequestResult.Success).message
                )
                LaunchedEffect(Unit) {
                    delay(2000)
                    reportsViewModel.resetReportResult()
                    onNavigateBack()
                }
            }
            is RequestResult.Failure ->{
                AlertMessage(
                    modifier = Modifier.padding(end = 12.dp, start = 12.dp),
                    type = AlertType.ERROR,
                    message = (reportResult as RequestResult.Failure).message
                )
                LaunchedEffect(Unit) {
                    delay(3500)
                    reportsViewModel.resetReportResult()
                }
            }
            is RequestResult.Loading ->{
                LinearProgressIndicator()
            }
        }
    }
}

