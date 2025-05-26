package com.unieventos.ui.admins.tabs

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.unieventos.ui.components.MapComposable
import com.unieventos.ui.navigation.LocalMainViewModel

@Composable
fun HomeAdminTab(
    onNavigateToDetail: (String) -> Unit
){

    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    val reports by reportsViewModel.reports.collectAsState()

    val context = LocalContext.current
    val permission = android.Manifest.permission.ACCESS_FINE_LOCATION
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ){  isGranted ->
        hasPermission = isGranted
        if (!isGranted) {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        if(!hasPermission) {
            permissionLauncher.launch(permission)
        }
    }

    var mapViewportState = rememberMapViewportState {
        setCameraOptions {
            zoom(8.0)
            center( Point.fromLngLat(-75.64, 4.46))
            pitch(45.0)
        }
    }

    MapComposable(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewportState,
        centerUserLocation = true,
        reports = reports,
        onNavigateToDetail = onNavigateToDetail
    )
}
