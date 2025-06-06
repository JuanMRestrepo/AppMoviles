package com.unieventos.ui.clientes.tabs

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
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
import androidx.compose.material3.TextButton
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.unieventos.R
import com.unieventos.model.ReportState
import com.unieventos.ui.clientes.componentsClient.CommentsItem
import com.unieventos.ui.components.MapComposable
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.RequestResult
import com.unieventos.utils.SharedPreferencesUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailReportTab(
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

    val report = reports.find { it.id == id } ?: return
    val name = usersViewModel.getNameById(report.idUser)

    val lista = report.images
    val imageList: List<String> = report.images

    var showComments by remember { mutableStateOf(false) }
    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val state = rememberCarouselState { lista.count() }

    val liked = report.likedUsers.contains(userId)
    val isSaved = currentUser?.savedReportIds?.contains(report.id) ?: false

    // Nuevos estados para resolver reporte
    var showResolveDialog by remember { mutableStateOf(false) }
    var showRejectDialogResult by rememberSaveable { mutableStateOf(false) }

    val updateResult by reportsViewModel.reportResult.collectAsState()
    val rejectResult by reportsViewModel.updateResult.collectAsState()

    val isCreator = userId == report.idUser

    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = { showComments = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Comment,
                    contentDescription = stringResource(id = R.string.commentsLbl),
                )
            }
        }
    ){padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ){

            LaunchedEffect(updateResult) {
                when (updateResult) {
                    is RequestResult.Success -> {
                        Toast.makeText(
                            context,
                            "Reporte resuelto correctamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        reportsViewModel.resetReportResult()
                    }
                    is RequestResult.Failure -> {
                        Toast.makeText(
                            context,
                            (updateResult as RequestResult.Failure).message,
                            Toast.LENGTH_LONG
                        ).show()
                        reportsViewModel.resetReportResult()
                    }
                    else -> {}
                }
            }

            LaunchedEffect(rejectResult) {
                when (rejectResult) {
                    is RequestResult.Success -> {
                        Toast.makeText(
                            context,
                            "Reporte mandado a revisión",
                            Toast.LENGTH_SHORT
                        ).show()
                        reportsViewModel.resetUpdateResult()
                    }
                    is RequestResult.Failure -> {
                        Toast.makeText(
                            context,
                            (rejectResult as RequestResult.Failure).message,
                            Toast.LENGTH_LONG
                        ).show()
                        reportsViewModel.resetUpdateResult()
                    }
                    else -> {}
                }
            }

            if (showRejectDialogResult) {
                AlertDialog(
                    onDismissRequest = { showRejectDialogResult = false },
                    title = { Text("Submit review") },
                    text = { Text("Are you sure you want to send it for review?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                reportsViewModel.solveReport(report.id, ReportState.PENDING, "")
                                showRejectDialogResult = false
                            }
                        ) {
                            Text("Review", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showRejectDialogResult = false }) {
                            Text(stringResource(R.string.closeLbl))
                        }
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (showResolveDialog) {
                    AlertDialog(
                        onDismissRequest = { showResolveDialog = false },
                        title = { Text(stringResource(R.string.resultReportLbl)) },
                        text = { Text(stringResource(R.string.resultReportSureLbl)) },
                        confirmButton = {
                            TextButton (
                                onClick = {
                                    reportsViewModel.updateReportState(report.id, ReportState.RESOLVED)
                                    showResolveDialog = false
                                }
                            ) {
                                Text(stringResource(R.string.solveLbl), color = Color(0xFF4CAF50))
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showResolveDialog = false }) {
                                Text(stringResource(R.string.closeLbl))
                            }
                        }
                    )
                }
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
                        text = "#${report.title}",
                        color = Color(0xFEE53935),
                        fontSize = 25.sp,
                    )

                    Row {
                        Icon(
                            imageVector = if (isSaved) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = stringResource(id = R.string.saveLabel),
                            tint = Color(0xFEE53935),
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    usersViewModel.toggleSaveReport(userId, report.id)
                                }
                        )
                        if (isCreator) {
                            Icon(
                                imageVector = Icons.Filled.CheckBox,
                                contentDescription = "Marcar como resuelto",
                                tint = Color(0xFF4CAF50),
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        showResolveDialog = true
                                    }
                                    .padding(end = 8.dp)
                            )
                        }
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
                        OutlinedTextField(
                            value = report.description,
                            onValueChange = {},
                            label = {
                                Text(
                                    text = stringResource(id = R.string.detailsLabel),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                        )

                        OutlinedTextField(
                            value = report.category,
                            onValueChange = {},
                            label = {
                                Text(
                                    text = stringResource(id = R.string.categoryLbl),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            )

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

                            Spacer(modifier = Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {

                                var mapViewportState = rememberMapViewportState {
                                    setCameraOptions {
                                        zoom(15.0)
                                        center( Point.fromLngLat(report.location.longitude, report.location.latitude))
                                    }
                                }

                                MapComposable(
                                    modifier = Modifier.fillMaxSize(),
                                    mapViewportState = mapViewportState,
                                    centerUserLocation = false,
                                    reports = listOf(report)
                                )
                            }
                        }

                        OutlinedTextField(
                            value = report.state.toString(),
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
                            trailingIcon = {
                                if (report.state == ReportState.RESOLVED) {
                                    Icon(
                                        imageVector = Icons.Filled.CheckCircle,
                                        contentDescription = "Resuelto",
                                        tint = Color(0xFF4CAF50)
                                    )
                                }
                            }
                        )

                        if (isCreator && report.state == ReportState.REJECTED) {
                            OutlinedTextField(
                                value = report.rejectionReason,
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Razón de rechazo",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                readOnly = true,
                            )

                            Button (
                                onClick = {
                                    showRejectDialogResult = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                            ) {
                                Text("Enviar a revisión")
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                        ) {
                            Text(
                                text = "Photos (${imageList.size})",
                                color = Color(0xFEE53935),
                                fontSize = 20.sp,
                            )
                        }

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

                        Box(
                            modifier = Modifier
                                .fillMaxWidth().padding(start = 20.dp),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        reportsViewModel.toggleLike(report.id, userId)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = stringResource(id = R.string.likeLbl),
                                    tint = if (report.likedUsers.contains(userId)) Color(0xFEE53935) else Color.Gray,
                                    modifier = Modifier.size(20.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 5.dp),
                                    text = "${report.likeCount}"
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
                                    text = name,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        if (showComments) {
                            CommentsItem(
                                reportId = report.id,
                                state = sheetState,
                                dismissModalSheet = { showComments = false }
                            )
                        }
                    }
                }
            }
        }
    }
}
