package com.example.raionchallange.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.raionchallange.data.model.Mood

@Composable
fun EditMoodDialog(
    mood: Mood,
    onDismiss: () -> Unit,
    onConfirm: (id: Int, name: String, description: String) -> Unit
) {
    var moodName by remember { mutableStateOf(mood.name) }
    var moodDescription by remember { mutableStateOf(mood.description) }
    var isError by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Edit Mood",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = moodName,
                    onValueChange = { 
                        moodName = it
                        isError = false
                    },
                    label = { Text("Nama Mood") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isError && moodName.isBlank(),
                    supportingText = if (isError && moodName.isBlank()) {
                        { Text("Nama mood tidak boleh kosong") }
                    } else null
                )

                OutlinedTextField(
                    value = moodDescription,
                    onValueChange = { moodDescription = it },
                    label = { Text("Deskripsi") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 3
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Batal")
                    }
                    
                    Button(
                        onClick = {
                            if (moodName.isBlank()) {
                                isError = true
                            } else {
                                onConfirm(mood.id, moodName, moodDescription)
                            }
                        }
                    ) {
                        Text("Update")
                    }
                }
            }
        }
    }
}