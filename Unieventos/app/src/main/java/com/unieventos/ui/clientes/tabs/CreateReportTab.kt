package com.unieventos.ui.clientes.tabs

import android.net.Uri
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.unieventos.ui.clientes.componentsClient.ImageUploader
import com.unieventos.ui.components.DropdownMenu

@Composable
fun CreateReportTab(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        val title = stringResource(id = R.string.titleLbl)
        val description = stringResource(id = R.string.descriptionLbl)
        val uploadImage = stringResource(id = R.string.uploadImageLbl)
        val select = stringResource(id = R.string.selectLbl)
        val createNewReport = stringResource(id = R.string.createLbl)
        var category by rememberSaveable { mutableStateOf("") }
        val categories = listOf(
            "Security",
            "Medical emergencies",
            "Infrastructure",
            "Lighting",
            "Pets",
            "Community"
        )

        var clickedPoint by remember { mutableStateOf<Point?>(null) }

        var markerResourceId by remember {
            mutableStateOf(R.drawable.red_marker)
        }

        var marker = rememberIconImage( key = markerResourceId, painter = painterResource(markerResourceId))

        var mapViewportState = rememberMapViewportState {
            setCameraOptions {
                zoom(9.0)
                center( Point.fromLngLat(-75.67, 4.53))
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
                    value = "",
                    onValueChange = {},
                    label = { Text(title) },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text(description) },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(),
                    value = category,
                    onValueChange = {
                        category = it
                    },
                    items = categories
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
                    Text(select, modifier = Modifier.weight(1f))
                    Icon(Icons.Outlined.Place, contentDescription = uploadImage, tint = Color.Black)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                ){
                    MapboxMap(
                        modifier = Modifier.fillMaxSize(),
                        mapViewportState = mapViewportState,
                        onMapClickListener = { point ->
                            clickedPoint = point
                            true
                        }
                    ) {
                        clickedPoint?.let {
                            PointAnnotation(
                                point = clickedPoint!!
                            ) {
                                iconImage = marker
                            }
                        }
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFFFF4A3D)),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(top = 30.dp)
                    ,
                    onClick = { },
                ) {
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(createNewReport, color = Color.White)
                }
            }
        }
    }
}
