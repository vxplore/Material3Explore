package com.debduttapanda.material3explore

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.debduttapanda.material3explore.ui.theme.Material3ExploreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DrawerContent(
        selected: Int,
        onItemClick: (Int)->Unit
    ) {
        NavigationDrawerItem(
            label = { Text("Item1") },
            selected = selected == 0,
            onClick = {
                onItemClick(0)
            }
        )
        NavigationDrawerItem(
            label = { Text("Item2") },
            selected = selected == 1,
            onClick = {
                onItemClick(1)
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3ExploreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var dismissableDrawer by remember{
                        mutableStateOf(false)
                    }
                    val drawerState = rememberDrawerState(initialValue = if(dismissableDrawer) DrawerValue.Open else DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedBottomMenu by remember { mutableIntStateOf(0) }
                    var selectedDrawerItem by remember{
                        mutableIntStateOf(0)
                    }
                    val appBarTitle = "m3 1.0.0-alpha06(Top app bar)"
                    val config = LocalConfiguration.current.orientation
                    if(config == Configuration.ORIENTATION_LANDSCAPE){
                        if(dismissableDrawer){
                            DismissibleNavigationDrawer(
                                drawerContent = {
                                    DrawerContent(
                                        selectedDrawerItem
                                    ){
                                        selectedDrawerItem = it
                                        dismissableDrawer = selectedDrawerItem==1
                                    }
                                },
                                drawerState = drawerState
                            ) {
                                Scaffold(
                                    topBar = {
                                        when(selectedBottomMenu){
                                            0-> SmallTopAppBar(title = { Text(appBarTitle) })
                                            1-> CenterAlignedTopAppBar(title = { Text(appBarTitle) })
                                            2-> MediumTopAppBar(title = { Text(appBarTitle) })
                                            3-> LargeTopAppBar(title = { Text(appBarTitle) })
                                        }
                                    },
                                    bottomBar = {
                                        MyBottomBar(selectedBottomMenu){
                                            selectedBottomMenu = it
                                        }
                                    },
                                ) {
                                    PageContent{
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            PermanentNavigationDrawer(drawerContent = {
                                DrawerContent(
                                    selectedDrawerItem
                                ){
                                    selectedDrawerItem = it
                                    dismissableDrawer = selectedDrawerItem==1
                                }
                            }) {

                                Scaffold(
                                    topBar = {
                                        when(selectedBottomMenu){
                                            0-> SmallTopAppBar(title = { Text(appBarTitle) })
                                            1-> CenterAlignedTopAppBar(title = { Text(appBarTitle) })
                                            2-> MediumTopAppBar(title = { Text(appBarTitle) })
                                            3-> LargeTopAppBar(title = { Text(appBarTitle) })
                                        }
                                    },
                                    bottomBar = {
                                        MyBottomBar(selectedBottomMenu){
                                            selectedBottomMenu = it
                                        }
                                    },
                                ) {
                                    PageContent{
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{
                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                DrawerContent(
                                    selectedBottomMenu
                                ){
                                    selectedBottomMenu = it
                                }
                            },
                        ) {
                            val appBarTitle = "m3 1.0.0-alpha06(Top app bar)"
                            Scaffold(
                                topBar = {
                                    when(selectedBottomMenu){
                                        0-> SmallTopAppBar(title = { Text(appBarTitle) })
                                        1-> CenterAlignedTopAppBar(title = { Text(appBarTitle) })
                                        2-> MediumTopAppBar(title = { Text(appBarTitle) })
                                        3-> LargeTopAppBar(title = { Text(appBarTitle) })
                                    }
                                },
                                bottomBar = {
                                    MyBottomBar(selectedBottomMenu){
                                        selectedBottomMenu = it
                                    }
                                },
                            ) {
                                PageContent{
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyBottomBar(
    selectedItem: Int,
    onSelected: (Int)->Unit
){
    //Bottom app bar need to use instead of navigation bar
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
            label = { Text("Favorite") },
            selected = selectedItem == 0,
            onClick = { onSelected(0) }
        )
        NavigationBarItem(
            icon = {
                BadgedBox(
                    badge = {
                        Badge {
                            val badgeNumber = "8"
                            Text(
                                badgeNumber,
                                modifier = Modifier.semantics {
                                    contentDescription = "$badgeNumber new notifications"
                                }
                            )
                        }
                    }) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = "Account"
                    )
                }
            },
            selected = selectedItem == 1,
            onClick = { onSelected(1) },
            label = {Text("Account")}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = selectedItem == 2,
            onClick = { onSelected(2) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = selectedItem == 3,
            onClick = { onSelected(3) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PageContent(
    openDrawer: ()->Unit
){
    Row(){
        var selectedRailItem by remember { mutableIntStateOf(0) }
        val items = listOf("Home", "Search", "Settings", "Account")
        val icons = listOf(Icons.Filled.Home, Icons.Filled.Search, Icons.Filled.Settings, Icons.Filled.Person)
        NavigationRail {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = selectedRailItem == index,
                    onClick = { selectedRailItem = index }
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            item{
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ){
                    Text(
                        "This is a card. Introduced in m3 1.0.0-alpha05",
                        modifier = Modifier
                            .padding(24.dp)
                    )
                }
            }
            item{
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ){
                    Text(
                        "This is a outlined card. Introduced in m3 1.0.0-alpha05",
                        modifier = Modifier
                            .padding(24.dp)
                    )
                }
            }
            item{
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ){
                    Text(
                        "This is a elevated card. Introduced in m3 1.0.0-alpha05",
                        modifier = Modifier
                            .padding(24.dp)
                    )
                }
            }
            item{
                FloatingActionButton(
                    onClick = {

                    },
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 4.dp,
                        focusedElevation = 6.dp,
                        hoveredElevation = 8.dp,
                    )
                ) {
                    Text("New Elevation support")
                }
            }
            item{
                var expanded by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.TopStart)
                ) {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit") },
                            onClick = { /* Handle edit! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Edit,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("Settings") },
                            onClick = { /* Handle settings! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Settings,
                                    contentDescription = null
                                )
                            })
                        DropdownMenuItem(
                            text = { Text("Send Feedback") },
                            onClick = { /* Handle send feedback! */ },
                            leadingIcon = {
                                Icon(
                                    Icons.Outlined.Email,
                                    contentDescription = null
                                )
                            },
                            trailingIcon = { Text("F11", textAlign = TextAlign.Center) })
                    }
                }
            }
            item{
                Text("@NonRestartableComposable")
            }
            item{
                Divider()
            }
            item{
                LinearProgressIndicator()
            }
            item{
                LinearProgressIndicator(progress = 0.5f)
            }
            item{
                CircularProgressIndicator()
            }
            item{
                CircularProgressIndicator(progress = 0.7f)
            }
            item{
                Text("No new feature in 1.0.0-alpha03")
            }
            item{
                var checked by remember{
                    mutableStateOf(false)
                }
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    }
                )
            }
            item{
                var selected by remember{
                    mutableStateOf(0)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected == 0,
                        onClick = {
                            selected = 0
                        }
                    )
                    Text("Male")
                    RadioButton(
                        selected = selected == 1,
                        onClick = {
                            selected = 1
                        }
                    )
                    Text("Female")
                    RadioButton(
                        selected = selected == 2,
                        onClick = {
                            selected = 2
                        }
                    )
                    Text("Other")
                }
            }
            item{
                Button(
                    onClick = {
                        openDrawer()
                    }
                ) {
                    Text("Open Drawer")
                }
            }

            item{
                TextButton(
                    onClick = {

                    }
                ) {
                    Text("Text Button")
                }
            }

            item{
                ElevatedButton(
                    onClick = {

                    }
                ) {
                    Text("Elevated Button")
                }
            }

            item{
                FilledTonalButton(onClick = {

                }) {
                    Text("FilledTonalButton")
                }
            }

            item{
                OutlinedButton(onClick = { /*TODO*/ }) {
                    Text("OutlinedButton")
                }
            }

            item{
                SmallFloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            }

            item{
                FloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            }

            item{
                LargeFloatingActionButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            }

            item{
                ExtendedFloatingActionButton(
                    text = { Text("Hello") }, onClick = { /*TODO*/ }
                )
            }

            item{
                var showDialog by remember {
                    mutableStateOf(false)
                }
                Button(onClick = {
                    showDialog = true
                }) {
                    Text("Open Alert Dialog")
                }
                if(showDialog){
                    AlertDialog(
                        onDismissRequest = {
                            showDialog = false
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                showDialog = false
                            }) {
                                Text("Ok")
                            }
                        },
                        dismissButton = {
                            Button(onClick = {
                                showDialog = false
                            }) {
                                Text("Cancel")
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = ""
                            )
                        },
                        title = {
                            Text("Delete")
                        },
                        text = {
                            Text("Are you sure to delete?")
                        }
                    )
                }
            }
        }
    }
}