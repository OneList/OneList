package com.onelist.ui

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.onelist.MainViewModel
import com.onelist.R
import com.onelist.dto.Item
import com.onelist.ui.theme.OneListTheme
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.onelist.dto.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.onelist.dto.Photo

class MainActivity : ComponentActivity() {

    private var firebaseUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    private var uri: Uri? = null
    private lateinit var currentImagePath: String
    private val viewModel: MainViewModel by viewModel<MainViewModel>()
    private var showDialog: Boolean by mutableStateOf(false)


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
        var showDropdown: Boolean by remember {mutableStateOf(false)}
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
                    .width(315.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(2.dp),
                )
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
                IconButton(
                    onClick = {
                        showDropdown = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "Item Options",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
                DropdownMenu(expanded = showDropdown, onDismissRequest = { showDropdown = false }) {
                    DropdownMenuItem(
                        onClick = {
                            viewModel.selectedItem = item
                            showDialog = true }
                    ){
                        Text(text = stringResource(R.string.edit))
                    }
                    DropdownMenuItem(
                        onClick = {
                            Toast.makeText(context, "${item.name} Deleted", Toast.LENGTH_SHORT).show()
                            viewModel.deleteItem(item)
                        }
                    ){
                        Text(text = stringResource(R.string.delete))
                    }
                    DropdownMenuItem(
                        onClick = {
                            takePhoto()
                        }
                    ){
                        Text(text = stringResource(R.string.add_image))
                    }
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
    fun ItemDialogue(){
        var itemName by remember(viewModel.selectedItem.itemID) { mutableStateOf(viewModel.selectedItem.name) }
        var itemQuantity by remember(viewModel.selectedItem.itemID) { mutableStateOf(viewModel.selectedItem.quantity.toString()) }
        val context = LocalContext.current
        var dialogTitle = stringResource(R.string.add_item)

        if(itemQuantity == "0"){
            itemQuantity = ""
        }


        //If item has an ID, change to "Edit Item"
        if(viewModel.selectedItem.itemID.isNotEmpty()){
            dialogTitle = stringResource(R.string.edit_item)
        }

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = dialogTitle, fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(2.dp)) },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text(stringResource(R.string.item_name)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        label = { Text(stringResource(R.string.Quantity)) },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val validation = viewModel.validateItemInfoInDialog(itemName, itemQuantity)
                        if(!validation.first){
                            Toast.makeText(context, getString(validation.second), Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        viewModel.selectedItem.name = itemName
                        viewModel.selectedItem.quantity = itemQuantity.toInt()
                        viewModel.saveItem(viewModel.selectedItem)
                        showDialog = false
                    }
                )
                {
                    Text(text = stringResource(R.string.submit))
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false}
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
    fun ListView(items : List<Item> = ArrayList<Item>()) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "OneList") },
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    elevation = 0.dp,
                    actions = {
                        IconButton(onClick = {
                            signIn()
                        }) {
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
                    if(showDialog)
                    {
                        ItemDialogue()
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.selectedItem = Item()
                        showDialog = true
                              },
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

    private fun takePhoto() {
        if (hasCameraPermission() == PackageManager.PERMISSION_GRANTED && hasExternalStoragePermission() == PackageManager.PERMISSION_GRANTED) {
            invokeCamera()
        } else {
            // request permissions
            requestMultiplePermissionsLauncher.launch(arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ))
        }
    }

    private val requestMultiplePermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) {
            resultsMap ->
        var permissionGranted = false

        resultsMap.forEach { permission, isGranted ->
            if (!isGranted) {
                return@forEach
            }
            permissionGranted  = isGranted
        }

        if (permissionGranted) {
            invokeCamera()
        } else {
            Toast.makeText(this, "Unable to take photo until granted permission", Toast.LENGTH_LONG).show()
        }
    }

    private fun invokeCamera() {
        var file = createImageFile()
        try {
            uri =
                FileProvider.getUriForFile(this, "com.onelist.fileprovider", file)
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
        }
        cameraLauncher.launch(uri)
    }

    private fun createImageFile() : File {
        val timestamp = SimpleDateFormat("yyyMMdd_HHmmss").format(Date())
        val imageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("Item_$timestamp", ".jpg",
            imageDirectory).apply {
            currentImagePath = absolutePath
        }
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
            success ->
        if (success) {
            Log.i("MainActivity", "Image Location $uri")
            var strUri = uri.toString()
            val photo = Photo(localUri = strUri)
            viewModel.photos.add(photo)
            viewModel.save()
        } else {
            Log.e("MainActivity", "IMage not saved $uri")
        }
    }

    fun hasCameraPermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
    fun hasExternalStoragePermission() = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    private fun signIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val signinIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        if (FirebaseAuth.getInstance().currentUser == null) {
            signInLauncher.launch(signinIntent)
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
