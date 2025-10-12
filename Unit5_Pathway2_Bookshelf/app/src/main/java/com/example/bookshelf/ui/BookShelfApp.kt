package com.example.bookshelf.ui

import android.telecom.Call
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookshelf.BookShelfApplication


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfApp() {
    val scroll = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val appViewModel: AppViewModel = viewModel(factory = AppViewModel.factory)
    val navHost: NavHostController = rememberNavController()
    val popPack by navHost.currentBackStackEntryAsState()
    val current = Screen.valueOf(
        popPack?.destination?.route ?: Screen.Home.name
    )
    val uiState = appViewModel.uiState
    val query by remember { appViewModel::query }
    Scaffold(
        topBar = {
            if (current.name == "Home") {
               ListAppBar(
                    scroll = scroll,
                    query = query,
                    onQueryChange = {
                        appViewModel.updateQuery(it)
                    },
                    onSearch = { appViewModel.loadBooks(it) }
                )
            } else {
                DetailsAppBar(
                    current = current,
                    canNavigateBack = navHost.previousBackStackEntry != null,
                    navigateUp = { navHost.navigateUp() })
            }
        }) { innerPadding ->
        NavHost(
            navController = navHost,
            startDestination = Screen.Home.name,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        ) {
            composable(route = Screen.Home.name) {
                BookListScreen(
                    contentPaddingValues = innerPadding,
                    uiState = appViewModel.uiState,
                    reloadList = { appViewModel.loadBooks(it) },
                    onClickAction = {
                        appViewModel.loadBookDetail(it)
                        navHost.navigate(Screen.DetailBook.name)
                    }
                )
            }
            composable(route = Screen.DetailBook.name) {
                BookDetailScreen(
                    uiState = appViewModel.uiState,
                    contentPadding = innerPadding,
                    reloadBook = {
                        appViewModel.loadBookDetail(it)
                    }
                )
            }

        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAppBar(
    scroll: TopAppBarScrollBehavior,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,

    ) {
    val focusManager = LocalFocusManager.current
    CenterAlignedTopAppBar(
        title = {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text("Tìm kiếm sách...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(query)
                    focusManager.clearFocus()
                }),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)

            )
        },
        scrollBehavior = scroll
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsAppBar(
    current: Screen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(current.title)
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}