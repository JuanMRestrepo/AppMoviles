package com.unieventos.ui.clientes.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.unieventos.R

@Composable
fun CreateReportTab(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val imageUrl = "https://okdiario.com/img/2024/10/21/rutas-personalizadas-5-e1729497886596.jpg"
        val title = stringResource(id = R.string.titleLbl)
        val description = stringResource(id = R.string.descriptionLbl)
        val category = stringResource(id = R.string.categoryLbl)
        val uploadImage = stringResource(id = R.string.uploadImageLbl)
        val select = stringResource(id = R.string.selectLbl)
        val createNewReport = stringResource(id = R.string.createLbl)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.newReportLbl),
            color = Color.Red,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(title) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text(description) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Menu, contentDescription = category)
            Spacer(modifier = Modifier.width(8.dp))
            Text(category, modifier = Modifier.weight(1f))
            Icon(Icons.Default.ArrowDropDown, contentDescription = category)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {  }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Image, contentDescription = uploadImage)
            Spacer(modifier = Modifier.width(8.dp))
            Text(uploadImage, modifier = Modifier.weight(1f))
            Icon(Icons.Outlined.CloudUpload, contentDescription = uploadImage)
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

        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = stringResource(id = R.string.mapImage),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(8.dp))

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
