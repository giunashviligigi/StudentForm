package com.studentforms.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Color(0xFFE94560),
                    secondary = Color(0xFF16C79A),
                    surface = Color(0xFF252547),
                    onSurface = Color(0xFFF5F0E8),
                    surfaceVariant = Color(0xFF3D3D6B),
                    onSurfaceVariant = Color(0xFFF5F0E8)
                )
            ) {
                StudentFormScreen()
            }
        }
    }
}

private val DeepNavy = Color(0xFF0F0F23)
private val SlatePurple = Color(0xFF1A1A3E)
private val CoralAccent = Color(0xFFE94560)
private val MintGreen = Color(0xFF16C79A)
private val SoftCream = Color(0xFFF5F0E8)
private val MutedLavender = Color(0xFF9B8EC4)
private val FieldBg = Color(0xFF252547)

private val AsymmetricShape = RoundedCornerShape(
    topStart = 28.dp,
    topEnd = 8.dp,
    bottomEnd = 28.dp,
    bottomStart = 8.dp
)
private val PillShape = RoundedCornerShape(50)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAgreed by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    val scrollState = rememberScrollState()
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = SoftCream,
        unfocusedTextColor = SoftCream,
        disabledTextColor = SoftCream,
        focusedBorderColor = CoralAccent,
        unfocusedBorderColor = MutedLavender.copy(alpha = 0.5f),
        disabledBorderColor = MutedLavender.copy(alpha = 0.5f),
        focusedLabelColor = MintGreen,
        unfocusedLabelColor = MutedLavender,
        disabledLabelColor = MutedLavender,
        cursorColor = CoralAccent,
        focusedContainerColor = FieldBg,
        unfocusedContainerColor = FieldBg,
        disabledContainerColor = FieldBg
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DeepNavy, SlatePurple, Color(0xFF16213E))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Student Form",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = CoralAccent,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "შეავსეთ თქვენი მონაცემები",
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace,
                color = MutedLavender,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            FormField("Name", nameState, { nameState = it }, "Enter your name", fieldColors)
            Spacer(modifier = Modifier.height(16.dp))
            FormField("Surname", surnameState, { surnameState = it }, "Enter your surname", fieldColors)
            Spacer(modifier = Modifier.height(16.dp))
            FormField("Email", emailState, { emailState = it }, "your.email@example.com", fieldColors)

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = dateState,
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text("Date", fontFamily = FontFamily.Monospace) },
                    placeholder = { Text("DD/MM/YYYY", color = MutedLavender.copy(alpha = 0.6f)) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = AsymmetricShape,
                    colors = fieldColors
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { showDatePicker = true }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "თქვენი ფავორიტი მიმართულება",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                color = MintGreen,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            listOf("Android", "iOS", "Web").forEach { option ->
                DirectionOption(
                    label = option,
                    selected = selectedOption == option,
                    onSelect = { selectedOption = option }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(AsymmetricShape)
                    .background(FieldBg)
                    .border(1.dp, MutedLavender.copy(alpha = 0.3f), AsymmetricShape)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "ვეთანხმები წესებს და პირობებს",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace,
                    color = SoftCream,
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isAgreed,
                    onCheckedChange = { isAgreed = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = SoftCream,
                        checkedTrackColor = MintGreen,
                        uncheckedThumbColor = MutedLavender,
                        uncheckedTrackColor = FieldBg,
                        uncheckedBorderColor = MutedLavender
                    )
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(PillShape)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(CoralAccent, Color(0xFFFF6B6B))
                        )
                    )
                    .clickable {
                        val isValid = nameState.isNotBlank() &&
                            surnameState.isNotBlank() &&
                            emailState.isNotBlank() &&
                            dateState.isNotBlank() &&
                            selectedOption != null &&
                            isAgreed

                        val message = if (isValid) {
                            "მონაცემები გაიგზავნა!"
                        } else {
                            "შეავსეთ ყველა ველი!"
                        }
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Submit",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            dateState = formatDate(millis)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("OK", color = CoralAccent)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Cancel", color = MutedLavender)
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
private fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    colors: androidx.compose.material3.TextFieldColors
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, fontFamily = FontFamily.Monospace) },
        placeholder = { Text(placeholder, color = MutedLavender.copy(alpha = 0.6f)) },
        modifier = Modifier
            .fillMaxWidth()
            .clip(AsymmetricShape),
        shape = AsymmetricShape,
        colors = colors,
        singleLine = true
    )
}

@Composable
private fun DirectionOption(
    label: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) CoralAccent.copy(alpha = 0.15f) else FieldBg)
            .border(
                width = 1.dp,
                color = if (selected) CoralAccent else MutedLavender.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onSelect() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = CoralAccent,
                unselectedColor = MutedLavender
            ),
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            color = if (selected) SoftCream else MutedLavender,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

private fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
