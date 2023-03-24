package com.onelist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.onelist.MainViewModel
import com.onelist.R
import com.onelist.ui.theme.OneListTheme

class MainActivity : ComponentActivity() {

    private val viewModelThing: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneListTheme {
                ListView()
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
    private fun ItemRow(name: String) {
        //TODO Will be the container for a single item

        //var itemName by remember { mutableStateOf("") }
        //var quantity by remember { mutableStateOf("") }
        //var category by remember { mutableStateOf("") }
        //val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 1.dp,
                    bottom = 1.dp
                )
        ) {
            Column {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(2.dp)
                ) //Name
                Text(text = "Qty: 1", modifier = Modifier.padding(2.dp)) //Quantity

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {/*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Mark as Purchased",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Edit Item",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
        Divider(
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.3f),
            modifier = Modifier
                .height(1.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        )
    }

    @Composable
    private fun CategoryHeader(name: String) {
        //TODO Header for each category
        Divider(
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .height(1.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primaryVariant)
        ) {
            Text(
                text = name,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(2.dp)
            )
        }
        Divider(
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .height(1.dp)
                .fillMaxHeight()
                .fillMaxWidth()
        )
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
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(Icons.Filled.ArrowBack, "backIcon")
//                    }
//                },
                    elevation = 0.dp,
                    actions = {
                        IconButton(onClick = {/* Do Something*/ }) {
                            Icon(Icons.Filled.Settings, null, tint = MaterialTheme.colors.onPrimary)
                        }
                    }
                )

            },
            content = { paddingValues ->
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
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(80.dp)
                ) {
                    Text(
                        text = "+",
                        fontSize = 52.sp
                    )
                }

            }
        )
    }

    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        OneListTheme {
            ListView()
        }
    }

    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DarkPreview() {
        OneListTheme(darkTheme = true) {
            ListView()
        }
    }
}