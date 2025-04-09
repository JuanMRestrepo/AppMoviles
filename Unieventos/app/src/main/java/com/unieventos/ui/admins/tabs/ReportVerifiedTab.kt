package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.unieventos.R
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.model.ReportState
import com.unieventos.ui.clientes.componentsClient.ReportCardItem
import java.time.LocalDateTime

@Composable
fun ReportVerifiedTab(
    navigateToDetail: (String) -> Unit
){
    var lista = listOf<Report>(
        Report(
            "1",
            "Reporte 1",
            "Descripcion 1",
            ReportState.ACCEPTED,
            listOf("https://i1.sndcdn.com/artworks-qJ5IFyKat8H70Vkz-tYUbnQ-t500x500.jpg"),
            Location(1.0, 2.0),
            LocalDateTime.now()
        ),
        Report(
            "2",
            "Reporte 2",
            "Descripcion 2",
            ReportState.ACCEPTED,
            listOf("https://picsum.photos/200/300"),
            Location(1.0, 2.0),
            LocalDateTime.now()
        ),
        Report(
            "3",
            "Reporte 3",
            "Descripcion 3",
            ReportState.ACCEPTED,
            listOf("https://picsum.photos/200/300"),
            Location(1.0, 2.0),
            LocalDateTime.now()
        )
    )

    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(lista) {
            ReportCardItem (
                report = it,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Composable
fun ReportCard(
    report: Report,
    navigateToDetail: (String) -> Unit
){
    OutlinedCard(
        onClick = {
            navigateToDetail(report.id)
        },
        modifier = Modifier.fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ){

            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .height(100.dp),
                model = report.images[0],
                contentDescription = stringResource(id = R.string.imageReport),
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    fontWeight = FontWeight.Bold,
                    text = report.title
                )
                Text(
                    text = report.description
                )
            }
        }
    }
}