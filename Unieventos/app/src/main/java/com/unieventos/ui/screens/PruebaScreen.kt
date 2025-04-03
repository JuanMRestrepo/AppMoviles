package com.unieventos.ui.screens

import android.util.LayoutDirection
import android.util.Patterns
import android.util.Size
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.model.Location
import com.unieventos.model.Report
import com.unieventos.model.ReportState
import com.unieventos.ui.components.TextFieldForm
import java.time.LocalDateTime
import java.time.format.TextStyle

//Pruebas de interfaces

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PruebaScreen(
    navigateToDetail: (String) -> Unit
) {

    var lista = listOf<Report>(
        Report(
            "1",
            "Reporte 1",
            "Descripcion 1",
            ReportState.ACCEPTED,
            listOf("https://picsum.photos/200/300"),
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "UniEventosApp")
                }
            )
        }
    ) {  paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lista) { report ->
                ReportCard(
                    report = report,
                    navigateToDetail = navigateToDetail
                )
            }
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
        Text(
            text = report.title,
            modifier = Modifier.padding(15.dp)
        )
        Text(
            text = report.description,
            modifier = Modifier.padding(15.dp)
        )
    }
}
*/
