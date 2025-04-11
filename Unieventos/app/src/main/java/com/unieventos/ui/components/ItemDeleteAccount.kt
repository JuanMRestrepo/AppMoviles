package com.unieventos.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.unieventos.R

@Composable
fun ItemDeleteAccount(
    showDeleteDialog: Boolean,
    onDismissRequest: () -> Unit
) {
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(
                    text = stringResource(id = R.string.deleteAccountLbl),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            text = {
                Text(stringResource(id = R.string.deleteMessageLbl))
            },
            confirmButton = {
                TextButton (
                    onClick = {
                        onDismissRequest()
                        //navigateToLogIn()
                    }
                ) {
                    Text(stringResource(id = R.string.deleteLbl), color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(stringResource(id = R.string.closeLbl))
                }
            }
        )
    }
}