package com.unieventos.ui.admins.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.unieventos.R
import com.unieventos.model.ChartData
import com.unieventos.model.ReportState
import com.unieventos.ui.admins.componentsAdmin.GetTestReportsPending
import com.unieventos.ui.admins.componentsAdmin.StatisticsChartCard
import com.unieventos.ui.admins.componentsAdmin.DatePickerDialog
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun StaticsTab() {
    val scrollState = rememberScrollState()
    var showCalendar by rememberSaveable { mutableStateOf(false) }
    var selectedDate by rememberSaveable { mutableStateOf(getCurrentDateFormatted()) }
    val allReportsData = GetTestReportsPending()

    val filteredReports = remember(selectedDate, allReportsData) {
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val selectedLocalDate = LocalDate.parse(selectedDate, formatter)

        allReportsData.filter { report ->
            val reportDate = report.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            reportDate == selectedLocalDate
        }
    }

    val hasErrors = filteredReports.isEmpty()

    val cityChartData = remember(filteredReports) {
        filteredReports.groupBy { it.description }
            .map { (city, reports) ->
                ChartData(
                    label = city,
                    acceptedCount = reports.count { it.state == ReportState.ACCEPTED },
                    pendingCount = reports.count { it.state == ReportState.PENDING }
                )
            }
    }

    val userChartData = remember(filteredReports) {
        filteredReports.groupBy { it.idUser }
            .map { (userId, reports) ->
                ChartData(
                    label = "Usuario $userId",
                    acceptedCount = reports.count { it.state == ReportState.ACCEPTED },
                    pendingCount = reports.count { it.state == ReportState.PENDING }
                )
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.staticsLbl),
                color = Color(0xFFFF4A3D),
                fontSize = 25.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = Color(0xFF6200EA),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.dateLbl),
                        color = Color(0xFF4527A0),
                        fontWeight = FontWeight.Medium
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { showCalendar = true }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(text = selectedDate)
                }
                IconButton(
                    onClick = { showCalendar = true },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = stringResource(id = R.string.selectDateLbl)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "MM/DD/YYYY",
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        StatisticsChartCard(
            title = stringResource(id = R.string.reportsByCityLbl),
            dateRange = "01 Mar 2025 - 30 Aug 2025",
            principal = "1",
            chartData = cityChartData,
            hasErrors = hasErrors
        )

        Spacer(modifier = Modifier.height(16.dp))

        StatisticsChartCard(
            title = stringResource(id = R.string.reportsByUserLbl),
            dateRange = "01 Mar 2025 - 30 Aug 2025",
            principal = "2",
            chartData = userChartData,
            hasErrors = hasErrors
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { /*  */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = stringResource(id = R.string.downloadLbl),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.downloadReportLbl),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }

    if (showCalendar) {
        Dialog(onDismissRequest = { showCalendar = false }) {
            DatePickerDialog (
                onDateSelected = { date ->
                    selectedDate = date
                    showCalendar = false
                },
                onDismiss = { showCalendar = false }
            )
        }
    }
}

fun getCurrentDateFormatted(): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)
    return formatter.format(Date())
}