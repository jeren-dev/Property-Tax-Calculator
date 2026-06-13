package com.example.propertytaxcalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalculateTaxActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CalculateTaxScreen(this)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculateTaxScreen(
    activity: CalculateTaxActivity
) {
    val dbHelper = remember { DatabaseHelper(activity) }

    var ownerName by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }

    val propertyTypes = listOf("Residential", "Commercial", "Industrial")
    val ageGroups = listOf("0-5 Years", "5-10 Years", "10-20 Years", "Above 20 Years")
    val occupancies = listOf("Self Occupied", "Rented")
    val zones = listOf("Urban", "Semi Urban", "Rural")

    var propertyType by remember { mutableStateOf(propertyTypes[0]) }
    var buildingAge by remember { mutableStateOf(ageGroups[0]) }
    var occupancy by remember { mutableStateOf(occupancies[0]) }
    var zone by remember { mutableStateOf(zones[0]) }

    var calculatedTaxAmount by remember { mutableStateOf<Double?>(null) }

    // Cyber-Minimalism Color Token Array Map
    val baseDarkBackground = Color(0xFF070A13)
    val cardBackground = Color(0xFF0F1526)
    val neonMintAccent = Color(0xFF00E676)
    val electricBlue = Color(0xFF00B0FF)
    val slateSubtext = Color(0xFF94A3B8)
    val inputFieldBorder = Color(0xFF1E293B)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TAX EVALUATION PIPELINE",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF060616),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(baseDarkBackground)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Asset Structural Specifications Card Container
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, inputFieldBorder, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = cardBackground),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "REGISTRY PARAMETERS",
                            fontSize = 12.sp,
                            color = electricBlue,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            fontFamily = FontFamily.Monospace,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Owner Name Input Field
                        OutlinedTextField(
                            value = ownerName,
                            onValueChange = { ownerName = it },
                            label = { Text("Asset Owner Name") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = neonMintAccent,
                                unfocusedBorderColor = inputFieldBorder,
                                focusedLabelColor = neonMintAccent,
                                unfocusedLabelColor = slateSubtext
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Area Input Field
                        OutlinedTextField(
                            value = area,
                            onValueChange = { area = it },
                            label = { Text("Total Area (Sq.Ft.)") },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = neonMintAccent,
                                unfocusedBorderColor = inputFieldBorder,
                                focusedLabelColor = neonMintAccent,
                                unfocusedLabelColor = slateSubtext
                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Dynamic Segment Dropdown Selectors
                        DropdownField("Property Framework", propertyTypes, propertyType) { propertyType = it }
                        DropdownField("Chronological Age Factor", ageGroups, buildingAge) { buildingAge = it }
                        DropdownField("Occupancy Status", occupancies, occupancy) { occupancy = it }
                        DropdownField("Geographic Regional Zone", zones, zone) { zone = it }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Core Engine Calculation Trigger Button
                Button(
                    onClick = {
                        if (ownerName.trim().isEmpty() || area.trim().isEmpty()) {
                            Toast.makeText(activity, "Error: Missing required telemetry parameters", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val areaValue = area.toDoubleOrNull() ?: 0.0
                        if (areaValue <= 0) {
                            Toast.makeText(activity, "Error: Invalid area metrics detected", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        val unitRate = when (propertyType) {
                            "Residential" -> 1.5
                            "Commercial" -> 3.5
                            else -> 2.5
                        }

                        val ageFactor = when (buildingAge) {
                            "0-5 Years" -> 1.0
                            "5-10 Years" -> 0.9
                            "10-20 Years" -> 0.8
                            else -> 0.7
                        }

                        val occupancyFactor = when (occupancy) {
                            "Rented" -> 1.2
                            else -> 1.0
                        }

                        val zoneFactor = when (zone) {
                            "Urban" -> 1.5
                            "Semi Urban" -> 1.2
                            else -> 1.0
                        }

                        val assessedValue = areaValue * unitRate * ageFactor * occupancyFactor * zoneFactor
                        val taxAmount = assessedValue * 0.15
                        calculatedTaxAmount = taxAmount

                        val currentDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())

                        dbHelper.insertTax(
                            PropertyTax(
                                ownerName,
                                propertyType,
                                areaValue,
                                buildingAge,
                                occupancy,
                                zone,
                                taxAmount,
                                currentDate
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = neonMintAccent),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "EXECUTE & WRITE TO DATA ENGINE",
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp,
                        fontFamily = FontFamily.Monospace
                    )
                }

                // Cyberpunk Premium Summary Receipt Card
                calculatedTaxAmount?.let { amount ->
                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, neonMintAccent, RoundedCornerShape(12.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF09121F)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ReceiptLong,
                                    contentDescription = null,
                                    tint = neonMintAccent,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "CALCULATED STATEMENT",
                                    fontSize = 13.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.5.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }

                            Divider(
                                color = inputFieldBorder,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 14.dp)
                            )

                            Text(
                                text = "TOTAL ASSESSED LIABILITY",
                                fontSize = 11.sp,
                                color = slateSubtext,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 1.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = "₹ %,.2f".format(amount),
                                fontSize = 34.sp,
                                color = neonMintAccent,
                                fontWeight = FontWeight.Black,
                                fontFamily = FontFamily.SansSerif,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Transaction records synced onto secure relational cache.",
                                fontSize = 11.sp,
                                color = Color(0xFF475569),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 12.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(vertical = 6.dp)) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(label) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Color(0xFF00B0FF),
                    unfocusedBorderColor = Color(0xFF1E293B),
                    focusedLabelColor = Color(0xFF00B0FF),
                    unfocusedLabelColor = Color(0xFF94A3B8)
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            // Custom Styled Matrix Dropdown Panel
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(Color(0xFF0F1526))
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                color = if (item == selectedItem) Color(0xFF00E676) else Color.White,
                                fontWeight = if (item == selectedItem) FontWeight.Bold else FontWeight.Normal,
                                fontFamily = FontFamily.SansSerif
                            )
                        },
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        },
                        colors = MenuDefaults.itemColors(
                            textColor = Color.White,
                            leadingIconColor = Color.White,
                            trailingIconColor = Color.White
                        )
                    )
                }
            }
        }
    }
}