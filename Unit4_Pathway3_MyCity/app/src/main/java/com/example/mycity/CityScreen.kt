import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.CategoryScreen
import com.example.mycity.CityViewModel
import com.example.mycity.PlaceDetailScreen
import com.example.mycity.PlaceListScreen
import com.example.mycity.R
import com.example.mycity.data.StatisticPlace
import com.example.mycity.ui.theme.MyCityTheme

enum class CityScreen(@StringRes val title: Int) {
    Home(title = R.string.home),
    ChoosePlace(R.string.choose_place),
    Information(title = R.string.info_place)
}

@Composable
fun MyCityApp() {

    val viewModel: CityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()
    val popStack by navController.currentBackStackEntryAsState()
    val currentScreen = CityScreen.valueOf(
        popStack?.destination?.route ?: CityScreen.Home.name
    )

    Scaffold(
        topBar = {
            CityAppBar(
                cityScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CityScreen.Home.name,
            modifier = Modifier
                .padding(innerPadding)
        ) {
            composable(route = CityScreen.Home.name) {
                CategoryScreen(
                    listCategory = StatisticPlace.getListCategory,
                    onCategoryClick = {
                        viewModel.updateCurrentCategory(it)
                        navController.navigate(CityScreen.ChoosePlace.name)
                    }
                )
            }
            composable(route = CityScreen.ChoosePlace.name) {
                PlaceListScreen(
                    listPlace = uiState.listPlace,
                    onPlaceClick = {
                        viewModel.updateCurrentPlace(it)
                        navController.navigate(CityScreen.Information.name)
                    }
                )
            }
            composable(route = CityScreen.Information.name) {

                PlaceDetailScreen(
                    place = uiState.selectedPlace,
                    contentPaddingValues = innerPadding
                )

            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityAppBar(
    cityScreen: CityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(cityScreen.title)
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



