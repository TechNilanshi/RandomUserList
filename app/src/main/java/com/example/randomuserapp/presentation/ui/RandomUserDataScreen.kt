package com.example.randomuserapp.presentation.ui

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.randomuserapp.R
import com.example.randomuserapp.domain.model.User
import com.example.randomuserapp.presentation.viewModel.UserListDataViewModel

// Display the user list using LazyColumn
@SuppressLint("SuspiciousIndentation")
@Composable
fun UserList(navController: NavController,viewModel: UserListDataViewModel = hiltViewModel(), onUserClick: (User) -> Unit) {
    val lazyPagingItems = viewModel.usersFlow.collectAsLazyPagingItems()
        LazyColumn {
            items(lazyPagingItems.itemCount) { index ->
                // Retrieve the User object using the index
                val user = lazyPagingItems[index]
                user?.let {
                    UserCardScreen(navController,user = it, onClick = { onUserClick(it) })
                }
            }

        // Handle loading state
        lazyPagingItems.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item {
                      //  CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        Text(
                            text = "Error: ${(loadState.append as LoadState.Error).error.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserCardScreen(navController: NavController,user: User, onClick: () -> Unit) {
    val userImage = user.picture.thumbnail
    val name = user.name.first +" " +user.name.last
    val address = user.location.street.number.toString() + " , " + user.location.street.name + " , " + user.location.city + " , " +
   user.location.state +" , "+ user.location.country


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick ={
                navController.navigate(
                    "detail/${Uri.encode(userImage)}/${Uri.encode(name)}/${Uri.encode(address)}"
                )
            } ) ,
        shape = MaterialTheme.shapes.medium.copy(CornerSize(16.dp)),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile picture
            val painter = rememberImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(userImage)
                    .crossfade(true)
                    .placeholder(R.drawable.user_image) // Placeholder image
                    .error(R.drawable.baseline_error_24) // Error image
                    .build()
            )
            Image(
                painter = painter,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 16.dp),
                        contentScale = ContentScale.Crop
            )

            // User details
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = address,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


