package com.onelist.ui

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.onelist.MainViewModel
import com.onelist.R
import com.onelist.dto.Item
import com.onelist.dto.User
import com.onelist.ui.theme.OneListTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchItems()
            val items by viewModel.items.observeAsState(initial = emptyList())
            OneListTheme {
                ListView(items)
            }
        }
    }

    @Composable
    private fun ItemRow(item: Item) {
        //TODO Will be the container for a single item

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
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(2.dp)
                ) //Name
                Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(2.dp)) //Quantity

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
    private fun CategoryHeader(categoryName: String) {
        //TODO Header for each category

        var headerColor = MaterialTheme.colors.primaryVariant
        if (categoryName.lowercase() == "purchased"){headerColor = MaterialTheme.colors.secondary}

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
                .background(color = headerColor)
        ) {
            Text(
                text = categoryName,
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
    fun ItemList(items : List<Item> = ArrayList<Item>()) { //Example List

        LazyColumn {
            stickyHeader {
                //TODO Make separate function for header
                CategoryHeader("Test Header")
            }

            items(items) { item ->
                if(!item.purchased){ItemRow(item)}
            }

            stickyHeader {
                //TODO Make separate function for header
                CategoryHeader("Purchased")
            }
            
            items(items) {item ->
                if(item.purchased){ItemRow(item)}
            }
        }
    }

    @Composable
    fun ItemDialogue(item: Item?){
        //TODO Dialogue for adding items

        var itemName by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        val context = LocalContext.current

        if(item != null){
            itemName = item.name
            quantity = item.quantity.toString()
        }

        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = if(item == null) "Add Item" else "Edit Item", fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(2.dp)) },
            text = {
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
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "$itemName $quantity",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
                {
                    Text(text = "Submit")
                }
            },
            dismissButton = {
                Button(
                    onClick = { /*TODO*/ }
                )
                {
                    Text(text = "Cancel")
                }
            }
        )
    }

    @Composable
    fun ListView(items : List<Item> = ArrayList<Item>()) { //Main Shopping List View
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
                    ItemList(items)
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.size(80.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add New Item",
                        modifier = Modifier.size(52.dp)
                    )
                }

            }
        )
    }

    private val previewData = listOf(
            Item("123", "Orange", listOf("1"), 3, false ),
            Item("123", "Bread", listOf("1"), 1, false),
            Item("123", "Toilet Paper", listOf("1"), 2, false),
        Item("123", "Detergent", listOf("1"), 1, true),

    )

    private val previewItem = Item("123", "Apple", listOf("1"), 3, false )

    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DefaultPreview() {
        OneListTheme {
            ListView(previewData)
        }
    }

    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DarkPreview() {
        OneListTheme(darkTheme = true) {
            ListView(previewData)
        }
    }
    
    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DefaultDialogPreview() {
        OneListTheme {
            ListView(previewData)
            ItemDialogue(item = null)
        }
    }

    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DarkDialogPreview() {
        OneListTheme(darkTheme = true) {
            ListView(previewData)
            ItemDialogue(previewItem)
        }
    }
}
