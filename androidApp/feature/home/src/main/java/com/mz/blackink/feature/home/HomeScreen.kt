package com.mz.blackink.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val conversations by viewModel.conversations.collectAsStateWithLifecycle()
    Scaffold(
        topBar = { TopAppBar(title = { Text("BlackInk") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = viewModel::createDraft) {
                Text("New draft", modifier = Modifier.padding(horizontal = 20.dp))
            }
        },
    ) { insets ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(insets),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, top = 24.dp, bottom = 112.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("A fresh page.", style = MaterialTheme.typography.headlineLarge)
                    Text("Your space starts here.", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Android starter · Local demo",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge,
                    )
                    Text("Drafts stay in memory and disappear when the app closes. Messaging is not connected yet.")
                }
            }
            if (conversations.isEmpty()) {
                item {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text("Nothing here yet", style = MaterialTheme.typography.titleLarge)
                            Text("Create a draft to try the app's feature, ViewModel, and repository flow.")
                        }
                    }
                }
            }
            items(conversations, key = { it.id }) { conversation ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(conversation.title, style = MaterialTheme.typography.titleMedium)
                        Text(conversation.preview)
                    }
                }
            }
        }
    }
}
