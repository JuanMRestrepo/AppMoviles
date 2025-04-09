package com.unieventos.ui.clientes.componentsClient

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudUpload
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ImageUploader(
    modifier: Modifier,
    onImageSelected: (Uri) -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val uploadImage = "Upload images"
    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            onImageSelected(it)
        }
    }

    ElevatedCard (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { imagePickerLauncher.launch("image/*") }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Image,
                contentDescription = uploadImage,
                tint = Color(0xFFE74C3C)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = selectedImageUri?.lastPathSegment ?: uploadImage,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            FilledTonalButton (
                onClick = { imagePickerLauncher.launch("image/*") },
            ) {
                Icon(
                    imageVector = Icons.Outlined.CloudUpload,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = Color(0xFFE74C3C)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Browse")
            }
        }
    }
}
