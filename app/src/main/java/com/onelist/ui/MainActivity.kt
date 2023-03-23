package com.onelist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ItemName() { //Example Form
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
fun ItemRow(name: String){
    //TODO Will be the container for a single item
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 1.dp,
                bottom = 1.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colors.onBackground)
        ) {
            Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(2.dp)) //Name
            Text(text = "Quantity", modifier = Modifier.padding(2.dp)) //Quantity

        }
    }
}

@Composable
fun CategoryHeader(name: String){
    //TODO Header for each category
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondary)
            .border(1.dp, MaterialTheme.colors.onBackground)
    ){
        Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.W700, color = MaterialTheme.colors.onPrimary,modifier = Modifier.padding(2.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemList() { //Example List
    //TODO Will be replaced with a list of items
    val listItems = listOf("Bread", "Cheddar Cheese", "Apples", "Toilet Paper", "Hand Soap")
    LazyColumn {
        stickyHeader {
            //TODO Make separate function for header
            CategoryHeader("Test Header")
        }

        items(listItems) { item ->
            ItemRow(item)
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
                ItemList()
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
