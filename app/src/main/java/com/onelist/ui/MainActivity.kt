package com.onelist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.onelist.R
import com.onelist.ui.theme.OneListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneListTheme {
                ListView()
            }
        }
    }
}

@Composable
fun ItemName(name: String) { //Example Form
    //TODO Will be replaced with a list of items
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column {
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text(stringResource(R.string.ItemName)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text(stringResource(R.string.Quantity)) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text(stringResource(R.string.Category)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Toast.makeText(
                    context,
                    "$itemName $quantity $category",
                    Toast.LENGTH_LONG
                ).show()
            }
        )
        {
            Text(text = "Submit")
        }
    }
}

@Composable
fun ListView() { //Main Shopping List View
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "OneList") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                actions = {
                    IconButton(onClick = {/* Do Something*/ }) {
                        Icon(Icons.Filled.Settings, null, tint = MaterialTheme.colors.onPrimary)
                    }
                }
            )

        },
        content = {paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxWidth()
            ) {
                ItemName("Android")
            }
        }
    )
}

@Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true )
@Composable
fun DefaultPreview() {
    OneListTheme {
        ListView()
    }
}

@Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true )
@Composable
fun DarkPreview() {
    OneListTheme(darkTheme = true) {
        ListView()
    }
}
