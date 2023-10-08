package com.debduttapanda.material3explore

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawer
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.debduttapanda.material3explore.ui.theme.Material3ExploreTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItem by remember { mutableIntStateOf(0) }

                    NavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            Button(onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }) {
                                Text("Close")
                            }
                        },
                    ) {
                        val appBarTitle = "m3 1.0.0-alpha03(Top app bar)"
                        Scaffold(
                            topBar = {
                                when(selectedItem){
                                    0-> SmallTopAppBar(title = { Text(appBarTitle) })
                                    1-> CenterAlignedTopAppBar(title = { Text(appBarTitle) })
                                    2-> MediumTopAppBar(title = { Text(appBarTitle) })
                                    3-> LargeTopAppBar(title = { Text(appBarTitle) })
                                }
                            },
                            bottomBar = {
                                NavigationBar {
                                    NavigationBarItem(
                                        icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
                                        label = { Text("Favorite") },
                                        selected = selectedItem == 0,
                                        onClick = { selectedItem = 0 }
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
                                        onClick = { selectedItem = 1 },
                                        label = {Text("Account")}
                                    )
                                    NavigationBarItem(
                                        icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
                                        label = { Text("Settings") },
                                        selected = selectedItem == 2,
                                        onClick = { selectedItem = 2 }
                                    )
                                    NavigationBarItem(
                                        icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
                                        label = { Text("Search") },
                                        selected = selectedItem == 3,
                                        onClick = { selectedItem = 3 }
                                    )
                                }
                            },
                        ) {
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
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
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
                                                scope.launch {
                                                    drawerState.open()
                                                }
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
                    }

                }
            }
        }
    }
}