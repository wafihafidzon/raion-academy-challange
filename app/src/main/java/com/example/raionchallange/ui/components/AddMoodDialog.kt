package com.example.raionchallange.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddMoodDialog(
    onDismiss: () -> Unit,
    onConfirm: (name: String, description: String) -> Unit
) {
    var moodName by remember { mutableStateOf("") }
    var moodDescription by remember { mutableStateOf("") }
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
                    text = "Tambah Mood Baru",
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
                    placeholder = { Text("Contoh: Bahagia") },
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
                    placeholder = { Text("Deskripsi singkat tentang mood ini") },
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
                                onConfirm(moodName, moodDescription)
                            }
                        }
                    ) {
                        Text("Tambah")
                    }
                }
            }
        }
    }
}