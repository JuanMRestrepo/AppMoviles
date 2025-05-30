package com.unieventos.ui.clientes.tabs

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.unieventos.R
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.ui.clientes.componentsClient.CommentsItem
import com.unieventos.ui.components.DropdownMenu
import com.unieventos.ui.components.MapComposable
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditReportTab(
    id: String,
    onNavigateBack: () -> Unit
) {

    val context = LocalContext.current
    val userMap = SharedPreferencesUtils.getPreference(context)
    val userId = userMap.get("userId") ?: ""

    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    val reports by reportsViewModel.reports.collectAsState()
    val currentUser by usersViewModel.currentUser.collectAsState()
    val reportResult by reportsViewModel.reportResult.collectAsState()

    val originalReport  = reports.find { it.id == id } ?: return

    var isEditing by remember { mutableStateOf(false) }
    var editedTitle by remember { mutableStateOf(originalReport.title) }
    var editedDescription by remember { mutableStateOf(originalReport.description) }
    var editedCategory by remember { mutableStateOf(originalReport.category) }
    var editedLocation by remember { mutableStateOf(originalReport.location) }
    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val lista = originalReport.images
    val imageList: List<String> = originalReport.images

    var showComments by remember { mutableStateOf(false) }
    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val state = rememberCarouselState { lista.count() }

    val liked = originalReport.likedUsers.contains(userId)
    val isSaved = currentUser?.savedReportIds?.contains(originalReport.id) ?: false

    val editedImages = remember { originalReport.images.toMutableStateList() }

    var showImageUploader by remember { mutableStateOf(false) }
    var uploadingImage by remember { mutableStateOf(false) }

    val categories = listOf(
        "Security",
        "Medical emergencies",
        "Infrastructure",
        "Pets",
        "Community"
    )

    LaunchedEffect(reportResult) {
        when (reportResult) {
            is RequestResult.Success -> {
                Toast.makeText(context, "Reporte actualizado", Toast.LENGTH_SHORT).show()
                delay(1000)
                reportsViewModel.resetReportResult()
                onNavigateBack()
            }
            is RequestResult.Failure -> {
                Toast.makeText(context, "Error al actualizar", Toast.LENGTH_SHORT).show()
                reportsViewModel.resetReportResult()
            }
            else -> {}
        }
    }

    fun validate(): Boolean {
        return when {
            editedTitle.isBlank() -> {
                errorMessage = "El título es obligatorio"
                false
            }
            editedDescription.isBlank() -> {
                errorMessage = "La descripción es obligatoria"
                false
            }
            editedCategory.isBlank() -> {
                errorMessage = "Debes seleccionar una categoría"
                false
            }
            editedImages.isEmpty() -> {
                errorMessage = "Debe haber al menos una imagen"
                false
            }
            else -> true
        }
    }

    Scaffold(
        floatingActionButton = {
            if (!isEditing) {
                FloatingActionButton(
                    onClick = { showComments = true },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Comment,
                        contentDescription = stringResource(id = R.string.commentsLbl)
                    )
                }
            }
        },
        bottomBar = {
            if (isEditing) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            isEditing = false
                            editedTitle = originalReport.title
                            editedDescription = originalReport.description
                            editedCategory = originalReport.category
                            editedLocation = originalReport.location
                            editedImages.clear()
                            editedImages.addAll(originalReport.images)                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    ) {
                        Text("Cancelar")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            if (validate()) {
                                val updatedReport = Report(
                                    id = originalReport.id,
                                    title = editedTitle,
                                    description = editedDescription,
                                    category = editedCategory,
                                    state = originalReport.state,
                                    images = editedImages,
                                    comments = originalReport.comments,
                                    location = editedLocation,
                                    date = originalReport.date,
                                    idUser = originalReport.idUser,
                                    likeCount = originalReport.likeCount,
                                    likedUsers = originalReport.likedUsers
                                )
                                reportsViewModel.updateReport(updatedReport)
                            } else {
                                showError = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFEE53935))
                    ) {
                        Text("Guardar Cambios")
                    }
                }
            }
        }
    ){padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.saveLabel),
                        tint = Color(0xFEE53935),
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                onNavigateBack()
                            }
                    )
                    Text(
                        text = originalReport.title,
                        color = Color(0xFEE53935),
                        fontSize = 25.sp,
                        modifier = Modifier.weight(1f, fill = false) // Agregado weight para evitar superposición
                    )
                    Row {
                        if (!isEditing) {
                            Icon(
                                imageVector = Icons.Filled.Edit, // Icono de lápiz
                                contentDescription = "Editar reporte",
                                tint = Color(0xFEE53935),
                                modifier = Modifier
                                    .size(25.dp)
                                    .clickable { isEditing = true }
                                    .padding(end = 8.dp)
                            )
                        }

                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = stringResource(id = R.string.saveLabel),
                            tint = Color(0xFEE53935),
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    usersViewModel.toggleSaveReport(userId, originalReport.id)
                                }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(360.dp)
                        .fillMaxHeight()
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(7.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (isEditing) {
                            OutlinedTextField(
                                value = editedTitle,
                                onValueChange = { editedTitle = it },
                                label = { Text("Título") },
                                modifier = Modifier.fillMaxWidth(),
                                isError = showError && editedTitle.isBlank()
                            )
                        } else {
                            OutlinedTextField(
                                value = originalReport.title,
                                onValueChange = { },
                                label = { Text("Título") },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true
                            )
                        }

                        // Descripción (editable solo en modo edición)
                        if (isEditing) {
                            OutlinedTextField(
                                value = editedDescription,
                                onValueChange = { editedDescription = it },
                                label = { Text(stringResource(id = R.string.detailsLabel)) },
                                modifier = Modifier.fillMaxWidth(),
                                isError = showError && editedDescription.isBlank()
                            )
                        } else {
                            OutlinedTextField(
                                value = originalReport.description,
                                onValueChange = { },
                                label = { Text("Description") },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true
                            )
                        }

                        // Categoría (editable solo en modo edición)
                        if (isEditing) {
                            Text(
                                text = stringResource(id = R.string.categoryLbl),
                                fontWeight = FontWeight.Bold
                            )
                            DropdownMenu(
                                modifier = Modifier.fillMaxWidth(),
                                value = editedCategory,
                                onValueChange = { editedCategory = it },
                                items = categories,
                                message = R.string.selectLblCategory,
                                isError = showError && editedCategory.isBlank()
                            )
                        } else {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                    .height(60.dp)
                                    .padding(start = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.categoryLbl) + ": "
                                )
                                Text(text = originalReport.category)
                            }
                        }

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(id = R.string.addressLabel),
                                    color = Color(0xFEE53935),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = stringResource(id = R.string.locationLbl),
                                    tint = Color(0xFEE53935),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            if (isEditing && editedLocation != originalReport.location) {
                                Text(
                                    text = "Ubicación modificada",
                                    color = Color(0xFF4CAF50), // Verde
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                var mapViewportState = rememberMapViewportState {
                                    setCameraOptions {
                                        zoom(15.0)
                                        center(Point.fromLngLat(
                                            if (isEditing) editedLocation.longitude else originalReport.location.longitude,
                                            if (isEditing) editedLocation.latitude else originalReport.location.latitude
                                        ))
                                    }
                                }

                                val currentLocationReport = remember(editedLocation, isEditing) {
                                    Report(
                                        id = originalReport.id,
                                        title = originalReport.title,
                                        description = originalReport.description,
                                        category = originalReport.category,
                                        state = originalReport.state,
                                        images = originalReport.images,
                                        comments = originalReport.comments,
                                        location = if (isEditing) editedLocation else originalReport.location,
                                        date = originalReport.date,
                                        idUser = originalReport.idUser,
                                        likeCount = originalReport.likeCount,
                                        likedUsers = originalReport.likedUsers
                                    )
                                }

                                MapComposable(
                                    modifier = Modifier.fillMaxSize(),
                                    mapViewportState = mapViewportState,
                                    centerUserLocation = false,
                                    reports = listOf(currentLocationReport),
                                    activateClick = isEditing,
                                    onMapClickListener = { point ->
                                        if (isEditing) {
                                            editedLocation = Location(point.latitude(), point.longitude())
                                        }
                                    }
                                )
                            }

                            if (isEditing) {
                                Text(
                                    text = "Haz clic en el mapa para cambiar la ubicación",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.Gray
                                )
                            }
                        }

                        OutlinedTextField(
                            value = originalReport.state.toString(),
                            onValueChange = {},
                            label = {
                                Text(
                                    text = stringResource(id = R.string.stateLbl),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Photos (${if (isEditing) editedImages.size else originalReport.images.size})",
                                    color = Color(0xFEE53935),
                                    fontSize = 20.sp,
                                )

                                if (isEditing) {
                                    ReportImageUploader(
                                        onImageUploaded = { newImageUrl ->
                                            editedImages.add(newImageUrl) // Añade directamente
                                        },
                                        uploadingImage = uploadingImage,
                                        onUploadingChange = { value -> uploadingImage = value }
                                    )
                                }
                            }
                        }

                        if (isEditing) {
                            // Vista editable de imágenes
                            LazyRow (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            ) {
                                items(editedImages) { imageUrl ->
                                    Box(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .size(160.dp)
                                    ) {
                                        AsyncImage(
                                            model = imageUrl,
                                            contentDescription = "Imagen del reporte",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(16.dp))
                                        )

                                        // Botón para eliminar imagen
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Eliminar imagen",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .size(30.dp)
                                                .clickable {
                                                    if (editedImages.size > 1) {
                                                        editedImages.remove(imageUrl)
                                                    } else {
                                                        Toast.makeText(context, "Debe haber al menos una imagen", Toast.LENGTH_SHORT).show()
                                                    }
                                                }
                                                .background(Color.White.copy(alpha = 0.7f), CircleShape)
                                                .padding(4.dp)
                                        )
                                    }
                                }
                            }
                        } else {
                            // Vista normal (carousel)
                            HorizontalMultiBrowseCarousel(
                                state = state,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp),
                                itemSpacing = 8.dp,
                                preferredItemWidth = 186.dp,
                                contentPadding = PaddingValues(horizontal = 16.dp),
                            ) { i ->
                                val imageUrl  = lista[i]
                                ElevatedCard(
                                    modifier = Modifier
                                        .width(186.dp)
                                        .height(140.dp),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    AsyncImage(
                                        model = imageUrl,
                                        contentDescription = "Reporte imagen ${i + 1}",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(16.dp))
                                    )
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(start = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        reportsViewModel.toggleLike(originalReport.id, userId)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = stringResource(id = R.string.likeLbl),
                                    tint = if (originalReport.likedUsers.contains(userId)) Color(0xFEE53935) else Color.Gray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 5.dp),
                                    text = "${originalReport.likeCount}"
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = stringResource(id = R.string.usersIcon),
                                    tint = Color(0xFEE53935),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Column {
                                Text(
                                    text = stringResource(id = R.string.creatorLbl),
                                    color = Color(0xFEE53935),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = usersViewModel.getNameById(originalReport.idUser),
                                    fontSize = 14.sp
                                )
                            }
                        }
                        if (showError) {
                            Text(
                                text = errorMessage,
                                color = Color.Red,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    if (showComments) {
        CommentsItem(
            reportId = originalReport.id,
            state = sheetState,
            dismissModalSheet = { showComments = false }
        )
    }
}

@Composable
fun ReportImageUploader(
    onImageUploaded: (String) -> Unit,
    uploadingImage: Boolean, // Recibimos el estado
    onUploadingChange: (Boolean) -> Unit // Recibimos el setter
) {
    val context = LocalContext.current

    val config = mapOf(
        "cloud_name" to "dh7g3xwjb",
        "api_key" to "778739682889816",
        "api_secret" to "gjF2ZZqghHezSoUBwVK6-NsNNY4"
    )

    val scope = rememberCoroutineScope()
    val cloudinary = remember { Cloudinary(config) }

    val fileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        uri?.let {
            scope.launch(Dispatchers.IO) {
                onUploadingChange(true)
                try {
                    val inputStream = context.contentResolver.openInputStream(it)
                    inputStream?.use { stream ->
                        val result = cloudinary.uploader().upload(stream, ObjectUtils.emptyMap())
                        val imageUrl = result["secure_url"].toString()
                        onImageUploaded(imageUrl)
                    }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error subiendo imagen", Toast.LENGTH_SHORT).show()
                } finally {
                    onUploadingChange(false) // Actualizamos el estado a false
                }
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
    ) {
        if(it) {
            fileLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Button(
        onClick = {
            val permissionCheckResult = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            if(permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                fileLauncher.launch("image/*")
            } else {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                } else {
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        },
        enabled = !uploadingImage
    ) {
        Text("Agregar imagen")
    }
}