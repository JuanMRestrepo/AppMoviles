package com.unieventos.ui.clientes.tabs

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.extension.compose.style.MapStyle
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.unieventos.R

@Composable
fun HomeUserTab(
    onNavitageToDetail: (String) -> Unit
){

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

    var markerResourceId by remember {
        mutableStateOf(R.drawable.red_marker)
    }

    var marker = rememberIconImage( key = markerResourceId, painter = painterResource(markerResourceId))

    MapboxMap(
        modifier = Modifier.fillMaxSize(),
        mapViewportState = mapViewportState,
        style = { MapStyle(style = "mapbox://styles/mapbox/streets-v12") }
    ) {

        MapEffect (Unit) { mapView ->
            mapView.location.updateSettings {
                locationPuck = createDefault2DPuck(withBearing = true)
                enabled = true
                puckBearing = PuckBearing.COURSE
                puckBearingEnabled = true
            }
            mapViewportState.transitionToFollowPuckState()
        }



        PointAnnotation(
            point = Point.fromLngLat(-75.64, 4.46)
        ) {
            iconImage = marker
            interactionsState.onClicked {
                onNavitageToDetail("123")
                true
            }
        }

        PointAnnotation(
            point = Point.fromLngLat(-75.57, 4.60)
        ) {
            iconImage = marker
        }
    }
}