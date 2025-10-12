package com.example.bookshelf.ui

import android.service.autofill.OnClickAction
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BookItem


enum class Screen(@StringRes val title: Int) {
    Home(title = R.string.home),
    DetailBook(title = R.string.detail)
}

@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    reloadList: (String) -> Unit,
    onClickAction: (String) -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp)
) {

    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> {
            val query = uiState.lastUi?.query ?: ""
            ErrorScreen(retryAction = { reloadList(query) })
        }

        is UiState.BookShelfUi -> {
            val query = uiState.query.trim()

            if (query.isEmpty() || uiState.books.isEmpty()) {

                EmptySearchScreen()
            } else {
                BooksGridScreen(
                    uiState.books,
                    contentPadding = contentPaddingValues,
                    onClickAction = onClickAction
                )
            }
        }
    }
}

@Composable
fun BookDetailScreen(
    modifier: Modifier = Modifier,
    uiState: UiState,
    reloadBook: (String) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Error -> {
            val id = uiState.lastUi?.selectedBook?.id ?: ""
            ErrorScreen(retryAction = { reloadBook(id) })
        }

        is UiState.BookShelfUi -> BookDetailShow(
            uiState.selectedBook,
            contentPadding = contentPadding
        )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun BooksGridScreen(
    books: List<BookItem>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClickAction: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier
            .padding(horizontal = 4.dp)
            .fillMaxSize(),
        contentPadding = contentPadding,
    ) {
        items(items = books, key = { book -> book.id }) { book ->
            BookCard(
                book,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                onClickAction = onClickAction
            )
        }
    }
}

@Composable
fun BookCard(book: BookItem, modifier: Modifier = Modifier, onClickAction: (String) -> Unit) {
    val imageUrl = book.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")
    Log.d("BookCard", "Image URL: $imageUrl")
    Card(
        modifier = modifier
            .clickable { onClickAction(book.id) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl ?: R.drawable.ic_broken_image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.ic_broken_image),
                contentDescription = book.volumeInfo.title ?: "Book cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
            )


            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = book.volumeInfo.title ?: "Không có tiêu đề",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = book.volumeInfo.authors?.joinToString(", ") ?: "Không rõ tác giả",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BookDetailShow(
    bookDetail: BookDetail?,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
) {
    if (bookDetail == null) return

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(
                    bookDetail.volumeInfo.imageLinks?.thumbnail?.replace("http://", "https://")
                        ?: R.drawable.ic_broken_image
                )
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.bookshelf),
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.ic_broken_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.75f)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = bookDetail.volumeInfo.title ?: "Không có tiêu đề",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Tác giả: " + (bookDetail.volumeInfo.authors?.joinToString(", ")
                    ?: "Không rõ"),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = bookDetail.volumeInfo.description ?: "Không có mô tả.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun EmptySearchScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.bookshelf),
            contentDescription = "Welcome Image",
            modifier = Modifier
                .size(250.dp)
                .padding(16.dp)
        )


    }
}