package com.unieventos.ui.clientes.componentsClient

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.model.Comment
import com.unieventos.ui.navigation.LocalMainViewModel
import com.unieventos.utils.SharedPreferencesUtils
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsItem(
    reportId: String,
    state: SheetState,
    dismissModalSheet: () -> Unit
) {
    val context = LocalContext.current
    val userId = remember {
        SharedPreferencesUtils.getPreference(context).get("userId") ?: ""
    }
    val reportsViewModel = LocalMainViewModel.current.reportsViewModel
    val usersViewModel = LocalMainViewModel.current.usersViewModel

    val reports by reportsViewModel.reports.collectAsState()
    val comments = remember(reports) {
        reports.find { it.id == reportId }?.comments ?: emptyList()
    }

    var newComment by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = dismissModalSheet,
        sheetState = state
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ){
            // Lista de comentarios
            LazyColumn(modifier = Modifier.heightIn(max = 400.dp)) {
                items(comments.size) { index ->
                    val comment = comments[index]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Usuario",
                            modifier = Modifier.size(24.dp)
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = usersViewModel.getNameById(comment.userId),
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = comment.content)
                            Text(
                                text = SimpleDateFormat("dd/MM HH:mm", Locale.getDefault())
                                    .format(comment.creationDate),
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Campo para nuevo comentario
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                OutlinedTextField(
                    value = newComment,
                    onValueChange = { newComment = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Escribe tu comentario...") }
                )

                IconButton(
                    onClick = {
                        if (newComment.isNotBlank()) {
                            val comment = Comment(
                                content = newComment,
                                userId = userId,
                                reportId = reportId
                            )
                            reportsViewModel.addComment(reportId, comment)
                            newComment = ""
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Enviar comentario")
                }
            }
        }
    }
}

@Composable
fun CommentCard(comment: Comment) {
    val usersViewModel = LocalMainViewModel.current.usersViewModel
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuario",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = usersViewModel.getNameById(comment.userId),
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = formatter.format(comment.creationDate),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}