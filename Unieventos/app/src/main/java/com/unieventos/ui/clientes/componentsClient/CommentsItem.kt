package com.unieventos.ui.clientes.componentsClient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsItem(
    state: SheetState,
    dismissModalSheet: () -> Unit
){
    ModalBottomSheet(
        sheetState = state,
        onDismissRequest = dismissModalSheet,
    ) {

        var comment by rememberSaveable { mutableStateOf("") }

        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            ListItem(
                headlineContent = {
                    Text(text = "Nombre persona")
                },
                supportingContent = {
                    Text(text = "Comentario")
                },
                leadingContent = {
                    Icon(
                        modifier = Modifier.width(50.dp).height(50.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                }
            )
            ListItem(
                headlineContent = {
                    Text(text = "Nombre persona")
                },
                supportingContent = {
                    Text(text = "Comentario")
                },
                leadingContent = {
                    Icon(
                        modifier = Modifier.width(50.dp).height(50.dp),
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                }
            )

            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = comment,
                    onValueChange = { comment = it}
                )
                IconButton(
                    onClick = { /* */ }
                ) {
                    Icon(
                        modifier = Modifier.width(50.dp),
                        imageVector = Icons.AutoMirrored.Default.Send ,
                        contentDescription = null
                    )
                }
            }
        }
    }
}