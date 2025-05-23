package com.unieventos.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ThumbUp
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.ui.clientes.componentsClient.CommentsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportDetailScreen(
    id: String,
    onNavigateBack: () -> Unit
) {
    var lista = listOf<Int>(
        R.drawable.huecodos,
        R.drawable.hueco,
        R.drawable.huecotres
    )

    var showComments by remember { mutableStateOf(false) }
    var sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val state = rememberCarouselState { lista.count() }

    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = { showComments = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Comment,
                    contentDescription = "Comentarios"
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
                        .padding(start = 20.dp, end = 20.dp, top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Reporte #$id",
                        color = Color(0xFEE53935),
                        fontSize = 25.sp,
                    )
                    Icon(
                        imageVector = Icons.Filled.BookmarkBorder,
                        contentDescription = "Save",
                        tint = Color(0xFEE53935),
                        modifier = Modifier.size(25.dp)
                    )
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
                            value = "Lorem ipsum dolor sit amet" +
                                    " consectetur unde omnis iste natus error sit" +
                                    " voluptatem accusantium doloremque laudantium, totam" +
                                    " rem aperiam, eaque ipsa",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = "Details",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                        )

                        OutlinedTextField(
                            value = "Pets",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = "Category",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Address",
                                    color = Color(0xFEE53935),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = "Location",
                                    tint = Color(0xFEE53935),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Text(
                                text = "Debajo el punte de la 18",
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mapa),
                                    contentDescription = "Map",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = "Location",
                                    tint = Color(0xFEE53935),
                                    modifier = Modifier.size(36.dp)
                                )
                            }
                        }

                        OutlinedTextField(
                            value = "Verified",
                            onValueChange = {},
                            label = {
                                Text(
                                    text = "State",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp)
                        ) {
                            Text(
                                text = "Photos(3)",
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
                            val item = lista[i]
                            ElevatedCard(
                                modifier = Modifier
                                    .width(186.dp)
                                    .height(140.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = item),
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
                            Row {
                                Icon(
                                    imageVector = Icons.Default.ThumbUp,
                                    contentDescription = "Like",
                                    tint = Color(0xFEE53935),
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(start = 5.dp),
                                    text = "12"
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
                                    contentDescription = "User Icon",
                                    tint = Color(0xFEE53935),
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Column {
                                Text(
                                    text = "Creator",
                                    color = Color(0xFEE53935),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "Ramon Salazar",
                                    fontSize = 14.sp
                                )
                            }
                        }

                        /*
                        if (showComments) {
                            CommentsItem(
                                reportId = report.id,
                                state = sheetState,
                                dismissModalSheet = { showComments = false }
                            )
                        }

                         */

                    }
                }
            }
        }
    }
}
