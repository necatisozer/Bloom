import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.coil.CoilImage
import com.necatisozer.bloom.R
import com.necatisozer.bloom.data.MockData
import com.necatisozer.bloom.data.Plant
import com.necatisozer.bloom.data.Theme
import com.necatisozer.bloom.ui.components.BloomCard
import com.necatisozer.bloom.ui.components.BloomOutlinedTextField
import com.necatisozer.bloom.ui.screen.home.HomeViewModel
import com.necatisozer.bloom.ui.theme.BloomTheme

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val viewState by homeViewModel.viewState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
            item {
                BloomOutlinedTextField(
                    value = viewState.searchText,
                    onValueChange = homeViewModel::onSearchTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    placeholder = {
                        Text(text = stringResource(id = R.string.home_search_placeholder))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.home_search_placeholder),
                            modifier = Modifier.size(18.dp),
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.home_theme_title),
                    modifier = Modifier
                        .paddingFromBaseline(top = 32.dp, bottom = 8.dp)
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h1,
                )
            }
            item {
                val themes = viewState.themes

                if (themes != null) {
                    ThemesRow(themes)
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                )
                {
                    Text(
                        text = stringResource(id = R.string.home_plants_title),
                        modifier = Modifier
                            .paddingFromBaseline(top = 32.dp, bottom = 12.dp),
                        style = MaterialTheme.typography.h1,
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.FilterList,
                            contentDescription = null,
                        )
                    }
                }
            }
            items(viewState.plants.orEmpty()) { checkablePlant ->
                PlantView(
                    plant = checkablePlant.data,
                    checked = checkablePlant.checked,
                    onCheckedChange = {
                        homeViewModel.onPlantCheckedChange(checkablePlant.data, it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                )
            }
        }
    }
}

@Composable
private fun ThemesRow(themes: List<Theme>) {
    LazyRow(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(themes) { theme ->
            BloomCard(
                modifier = Modifier
                    .size(136.dp)
                    .clickable { },
            ) {
                Column {
                    CoilImage(
                        data = theme.image,
                        contentDescription = theme.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(96.dp),
                        contentScale = ContentScale.Crop,
                        fadeIn = true,
                    )
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = theme.name,
                            modifier = Modifier.padding(horizontal = 16.dp),
                            style = MaterialTheme.typography.h2,
                            maxLines = 2,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PlantView(
    plant: Plant,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (image, title, description, checkbox) = createRefs()

        CoilImage(
            data = plant.image,
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.small)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                },
            fadeIn = true,
            contentScale = ContentScale.Crop
        )

        Text(
            text = plant.name,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(image.end, margin = 16.dp)
                }
                .paddingFromBaseline(top = 24.dp),
            style = MaterialTheme.typography.h2,
        )

        Text(
            text = plant.description,
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                }
                .paddingFromBaseline(top = 16.dp),
            style = MaterialTheme.typography.body1,
        )

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.constrainAs(checkbox) {
                top.linkTo(parent.top, margin = 16.dp)
                bottom.linkTo(parent.bottom, margin = 24.dp)
                end.linkTo(parent.end)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlantViewPreview() {
    var checked: Boolean by remember { mutableStateOf(false) }

    BloomTheme {
        PlantView(MockData.plants[0], checked, { checked = it })
    }
}