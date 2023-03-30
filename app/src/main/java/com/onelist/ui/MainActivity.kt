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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.onelist.dto.Item
import com.onelist.ui.theme.OneListTheme

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

    /**
     * Creates an individual item element to be put within the list.
     * @param item The item to be used in this element
     */
    @Composable
    private fun ItemRow(item: Item) {
        val context = LocalContext.current
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
                IconButton(
                    onClick = {
                        val updateItem = item.copy(purchased = !item.purchased) //I do not know why this is necessary, Jetpack Compose sometimes won't recompose unless I do a deep copy
                        viewModel.saveItem(updateItem)
                    }) {
                    Icon(
                        imageVector = if(item.purchased) Icons.Filled.Close else Icons.Filled.Done,
                        contentDescription = "Mark as Purchased",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Item Options",
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

    /**
     * Creates a Header for the specified Category, if "Purchased" is passed as name will create the header with a red color.
     * @param categoryName Name of the category used in the header
     */
    @Composable
    private fun CategoryHeader(categoryName: String) {
        //Set color to primaryVariant, but if header is for "Purchased", change color to secondary
        var headerColor = MaterialTheme.colors.primaryVariant
        if (categoryName.lowercase() == stringResource(R.string.purchased).lowercase()){headerColor = MaterialTheme.colors.secondary}

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

    /**
     * Creates the list of items and the associated headers from the list of items passed in.
     * @param items A list of items from which to take the data and feed into the UI based on their details.
     */
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ItemList(items : List<Item> = ArrayList<Item>()) { //Example List

        LazyColumn {
            stickyHeader {
                CategoryHeader(stringResource(R.string.not_purchased))
            }

            items(items) { item ->
                if(!item.purchased){ItemRow(item)}
            }

            stickyHeader {
                CategoryHeader(stringResource(R.string.purchased))
            }
            
            items(items) {item ->
                if(item.purchased){ItemRow(item)}
            }
        }
    }

    /**
     * Creates a Dialog pop-up that edits the item passed in, or creates a new item if no item is passed.
     * @param item Item to be edited, if null changes dialog to "Add Item" and adds a new item with specified details
     */
    @Composable
    fun ItemDialogue(item: Item?){
        var itemName by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }
        val context = LocalContext.current
        var dialogTitle = stringResource(R.string.add_item)

        //If item is present, update text fields to use the item's current information, and change title to "Edit Item"
        if(item != null) {
            itemName = item.name
            quantity = item.quantity.toString()
            dialogTitle = stringResource(R.string.edit_item)
        }

        AlertDialog(
            onDismissRequest = { /*TODO*/ },
            title = { Text(text = dialogTitle, fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(2.dp)) },
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
                    Text(text = stringResource(R.string.submit))
                }
            },
            dismissButton = {
                Button(
                    onClick = { /*TODO*/ }
                )
                {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }

    /**
     * Creates the Scaffold, the List of Items, and the Add item button.
     * @param items List of items to be passed into the various functions
     */
    @Composable
    fun ListView(items : List<Item> = ArrayList<Item>()) { //Main Shopping List View
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "OneList") },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
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

    //Test Data
    private val previewData = listOf(
            Item("123", "Orange", listOf("1"), 3, false ),
            Item("123", "Bread", listOf("1"), 1, false),
            Item("123", "Toilet Paper", listOf("1"), 2, false),
            Item("123", "Detergent", listOf("1"), 1, true),

    )

    private val previewItem = Item("123", "Apple", listOf("1"), 3, false )

    //Previews
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
