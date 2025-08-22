package com.medicalheatmap.smartmattressapp.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Mattress Monitor") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { Icon(Icons.Default.Dashboard, "Dashboard") },
                    label = { Text("Dashboard") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { Icon(Icons.Default.Analytics, "Prediction") },
                    label = { Text("AI Prediction") }
                )
                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { Icon(Icons.Default.History, "History") },
                    label = { Text("History") }
                )
                NavigationBarItem(
                    selected = selectedTab == 3,
                    onClick = { selectedTab = 3 },
                    icon = { Icon(Icons.Default.Thermostat, "Temperature") },
                    label = { Text("Temperature") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> DashboardTab()
                1 -> PredictionTab()
                2 -> HistoryTab()
                3 -> TemperatureTab()
            }
        }
    }
}

@Composable
fun DashboardTab() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Heatmap Section
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Pressure Heatmap",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Simulated Heatmap
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF2196F3),
                                        Color(0xFF4CAF50),
                                        Color(0xFFFFEB3B),
                                        Color(0xFFFF9800),
                                        Color(0xFFF44336)
                                    )
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        // Body silhouette overlay
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Head
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.3f))
                            )
                            // Shoulders
                            Box(
                                modifier = Modifier
                                    .width(100.dp)
                                    .height(30.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.White.copy(alpha = 0.3f))
                            )
                            // Torso
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(80.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Red.copy(alpha = 0.5f))
                            )
                            // Hips
                            Box(
                                modifier = Modifier
                                    .width(90.dp)
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFF9800).copy(alpha = 0.5f))
                            )
                            // Legs
                            Row {
                                Box(
                                    modifier = Modifier
                                        .width(35.dp)
                                        .height(80.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.Yellow.copy(alpha = 0.4f))
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .width(35.dp)
                                        .height(80.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.Yellow.copy(alpha = 0.4f))
                                )
                            }
                        }
                    }

                    // Legend
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LegendItem(Color(0xFF2196F3), "Low")
                        LegendItem(Color(0xFF4CAF50), "Normal")
                        LegendItem(Color(0xFFFFEB3B), "Medium")
                        LegendItem(Color(0xFFFF9800), "High")
                        LegendItem(Color(0xFFF44336), "Critical")
                    }
                }
            }
        }

        // Active Alerts Section
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = "Alerts",
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Active Alerts",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    AlertItem(
                        "High pressure detected on lower back (12 min)",
                        Icons.Default.ErrorOutline,
                        Color.Red
                    )
                    AlertItem(
                        "Increased pressure predicted on left heel - cooling activated",
                        Icons.Default.AcUnit,
                        Color.Blue
                    )
                }
            }
        }
    }
}

@Composable
fun PredictionTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Psychology,
                        contentDescription = "AI",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AI Pressure Prediction",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Predicted pressure zones
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Simulated prediction visualization
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color.Green.copy(alpha = 0.3f))
                        )
                        Box(
                            modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Red.copy(alpha = 0.6f))
                        )
                        Text(
                            text = "⚠️ Shoulders",
                            fontSize = 12.sp,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Yellow.copy(alpha = 0.4f))
                        )
                        Box(
                            modifier = Modifier
                                .width(110.dp)
                                .height(50.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Green.copy(alpha = 0.3f))
                        )
                        Row {
                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFFF9800).copy(alpha = 0.5f))
                            )
                            Spacer(modifier = Modifier.width(15.dp))
                            Box(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color.Green.copy(alpha = 0.3f))
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF3E0)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Predicted Issues:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("• Anticipated pressure build-up in shoulders in 10 minutes")
                        Text("• Lower back pressure may increase in 25 minutes")
                        Text("• Recommendation: Adjust position or activate cooling")
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryTab() {
    val historyItems = remember {
        listOf(
            HistoryItem("Cooling triggered on hips", "3:45 PM", Icons.Default.AcUnit),
            HistoryItem("High pressure alert resolved", "2:30 PM", Icons.Default.CheckCircle),
            HistoryItem("Position adjustment recommended", "1:15 PM", Icons.Default.SwapVert),
            HistoryItem("Cooling activated on lower back", "12:00 PM", Icons.Default.AcUnit),
            HistoryItem("Pressure spike detected", "11:30 AM", Icons.Default.Warning),
            HistoryItem("System calibrated", "10:00 AM", Icons.Default.Settings),
            HistoryItem("Night mode activated", "9:00 PM", Icons.Default.NightsStay),
            HistoryItem("Morning position reset", "7:00 AM", Icons.Default.Refresh)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(historyItems) { item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = item.message,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "Today, ${item.time}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TemperatureTab() {
    var temperature by remember { mutableStateOf(15f) }
    var selectedZone by remember { mutableStateOf("Full Mattress") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Thermostat,
                        contentDescription = "Temperature",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Temperature Control",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Zone Selection
                Text(
                    text = "Select Zone",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ZoneChip("Full Mattress", selectedZone) { selectedZone = it }
                    ZoneChip("Upper Body", selectedZone) { selectedZone = it }
                    ZoneChip("Lower Body", selectedZone) { selectedZone = it }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Temperature Display
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF2196F3),
                                    Color(0xFF64B5F6)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${temperature.toInt()}°C",
                            fontSize = 64.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = selectedZone,
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Temperature Slider
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Cool", fontSize = 14.sp)
                        Text("Temperature Adjustment", fontWeight = FontWeight.Medium)
                        Text("Warm", fontSize = 14.sp)
                    }

                    Slider(
                        value = temperature,
                        onValueChange = { temperature = it },
                        valueRange = 0f..20f,
                        steps = 19,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("0°C", fontSize = 12.sp, color = Color.Gray)
                        Text("20°C", fontSize = 12.sp, color = Color.Gray)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Quick Presets
                Text(
                    text = "Quick Presets",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PresetButton("Sleep", 18f) { temperature = it }
                    PresetButton("Cool", 10f) { temperature = it }
                    PresetButton("Neutral", 15f) { temperature = it }
                    PresetButton("Off", 20f) { temperature = it }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Apply Button
                Button(
                    onClick = { /* Simulated action */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Apply Settings")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE8F5E9)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Status",
                    tint = Color(0xFF4CAF50)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Cooling System Active",
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Current temperature: ${temperature.toInt()}°C",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun LegendItem(color: Color, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = label, fontSize = 12.sp)
    }
}

@Composable
fun AlertItem(message: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = message,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZoneChip(
    text: String,
    selectedZone: String,
    onSelect: (String) -> Unit
) {
    FilterChip(
        selected = selectedZone == text,
        onClick = { onSelect(text) },
        label = { Text(text, fontSize = 12.sp) }
    )
}

@Composable
fun PresetButton(
    label: String,
    temp: Float,
    onSelect: (Float) -> Unit
) {
    OutlinedButton(
        onClick = { onSelect(temp) },
        modifier = Modifier,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(label, fontSize = 12.sp)
    }
}

data class HistoryItem(
    val message: String,
    val time: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)