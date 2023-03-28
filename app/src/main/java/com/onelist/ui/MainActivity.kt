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
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.onelist.MainViewModel
import com.onelist.R
import com.onelist.dto.Category
import com.onelist.dto.Item
import com.onelist.dto.User
import com.onelist.ui.theme.OneListTheme

class MainActivity : ComponentActivity() {

    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val viewModel: MainViewModel by viewModel<MainViewModel>()

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
                label = { Text(stringResource(R.string.item_name)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text(stringResource(R.string.quantity)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text(stringResource(R.string.category)) },
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
                Text(text = stringResource(id = R.string.submit))
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    signIn()
                }
                    ){

                Text(text = stringResource(id = R.string.sign_in))
            }
        }
    }

    @Composable
    private fun ItemRow(item: Item) {
        //TODO Will be the container for a single item

        var itemName by remember { mutableStateOf(item.name) }
        var itemId by remember { mutableStateOf(item.itemID) }
        var quantity by remember { mutableStateOf(item.quantity) }
        var category by remember { mutableStateOf(item.quantity) }
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
                    text = itemName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(2.dp)
                ) //Name
                Text(text = "Qty: $quantity", modifier = Modifier.padding(2.dp)) //Quantity

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
    private fun CategoryHeader(category: Category) {
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
                text = category.name,
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
        val listItems: List<Item> = listOf(
            Item("ID", "Bread", listOf("ID1", "ID2"), 1, true),
            Item("ID", "Apples", listOf("ID1", "ID2"), 5, false),
            Item("ID", "Toilet Paper", listOf("ID1", "ID2"), 3, true)
        )
        LazyColumn {
            stickyHeader {
                //TODO Make separate function for header
                CategoryHeader(category = Category("ID", "Groceries"))
            }

            items(listItems) { item ->
                ItemRow(item)
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
    fun ListView() { //Main Shopping List View
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
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
                    Icon(painterResource(id = R.drawable.ic_add_foreground), "add")
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
    
    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DefaultDialogPreview() {
        OneListTheme {
            ListView()
            ItemDialogue(item = null)
        }
    }

    @Preview(showBackground = true, name = "test", device = "spec:width=411dp,height=891dp", showSystemUi = true)
    @Composable
    fun DarkDialogPreview() {
        OneListTheme(darkTheme = true) {
            ListView()
            ItemDialogue(item = Item("1", "Bread", listOf("1"), 1, true))
        }
    }
    
    private fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        if (FirebaseAuth.getInstance().currentUser == null) {
            signInLauncher.launch(signInIntent)
        } else {
            // User is already signed in
        }
    }

    private val signInLauncher = registerForActivityResult (
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        signInResult(result)
    }


    private fun signInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            firebaseUser = FirebaseAuth.getInstance().currentUser
            firebaseUser?.let {
                val user = User(it.uid, it.displayName!!)
                viewModel.user = user
                viewModel.saveUser(user)
            }
        } else {
            val error = response?.error?.errorCode ?: "unknown error"
            Log.e("MainActivity.kt", "Error logging in: $error")
        }
    }
}
