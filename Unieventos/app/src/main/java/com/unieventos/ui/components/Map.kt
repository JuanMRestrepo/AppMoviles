package com.unieventos.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.mapbox.geojson.Point
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.rememberIconImage
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.DefaultViewportTransitionOptions
import com.unieventos.R
import com.unieventos.model.Report

@Composable
fun MapComposable(
    modifier: Modifier = Modifier,
    centerUserLocation: Boolean = false,
    mapViewportState: MapViewportState,
    reports: List<Report>,
    onNavigateToDetail: (String) -> Unit = {},
    activateClick: Boolean = false,
    onMapClickListener: (Point) -> Unit = {}
){
    var clickedPoint by remember { mutableStateOf<Point?>(null) }

    var markerResourceId by remember {
        mutableStateOf(R.drawable.red_marker)
    }

    var marker = rememberIconImage(key = markerResourceId, painter = painterResource(markerResourceId))

    MapboxMap(
        modifier = modifier,
        mapViewportState = mapViewportState,
        onMapClickListener = {
            if(activateClick) {
                onMapClickListener(it)
                clickedPoint = it
            }
            true
        }
    ) {

        if(centerUserLocation){
            MapEffect (Unit) { mapView ->
                mapView.location.updateSettings {
                    locationPuck = createDefault2DPuck(withBearing = true)
                    enabled = true
                    puckBearing = PuckBearing.COURSE
                    puckBearingEnabled = true
                }
                mapViewportState.transitionToFollowPuckState(
                    defaultTransitionOptions = DefaultViewportTransitionOptions.Builder().maxDurationMs(10).build()
                //300
                )
            }
        }

        clickedPoint?.let {
            PointAnnotation(
                point = clickedPoint!!
            ) {
                iconImage = marker
            }
        }

        reports.forEach{ report ->
            PointAnnotation(
                point = Point.fromLngLat(report.location.longitude, report.location.latitude),
            ){
                iconImage = marker
                interactionsState.onClicked {
                    onNavigateToDetail(report.id)
                    true
                }
            }
        }
    }
}