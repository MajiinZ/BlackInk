package com.mz.blackink.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mz.blackink.shared.domain.Conversation
import com.mz.blackink.shared.domain.Message
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

private val InkBlue = Color(0xFF517DA2)
private val AccentBlue = Color(0xFF4A97D0)
private val SecondaryText = Color(0xFF87939E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val conversations by viewModel.conversations.collectAsStateWithLifecycle()
    val messages by viewModel.messages.collectAsStateWithLifecycle()
    var selectedId by rememberSaveable { mutableStateOf<String?>(null) }
    var searching by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }
    var filter by rememberSaveable { mutableIntStateOf(0) }
    var showNewChat by rememberSaveable { mutableStateOf(false) }
    var showAbout by rememberSaveable { mutableStateOf(false) }
    var newName by rememberSaveable { mutableStateOf("") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selected = conversations.firstOrNull { it.id == selectedId }
    val openChat: (String) -> Unit = { id -> viewModel.markRead(id); selectedId = id }
    BackHandler(selectedId != null || searching || drawerState.isOpen) {
        when {
            drawerState.isOpen -> scope.launch { drawerState.close() }
            selectedId != null -> selectedId = null
            else -> { searching = false; query = "" }
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = selected == null,
        drawerContent = {
            ModalDrawerSheet(drawerContainerColor = Color.White) {
                Column(Modifier.fillMaxWidth().background(InkBlue).padding(24.dp)) {
                    Box(Modifier.size(64.dp).background(Color(0xFF77A7CF), CircleShape), contentAlignment = Alignment.Center) {
                        Text("BI", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Medium)
                    }
                    Spacer(Modifier.height(18.dp))
                    Text("BlackInk", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    Text("Your conversations, together", color = Color(0xFFD3E4F2), fontSize = 13.sp)
                }
                Spacer(Modifier.height(12.dp))
                DrawerLink("All chats", Icons.Default.ChatBubbleOutline) {
                    filter = 0; selectedId = null; scope.launch { drawerState.close() }
                }
                DrawerLink("New conversation", Icons.Default.Edit) {
                    showNewChat = true; scope.launch { drawerState.close() }
                }
                DrawerLink("Saved Messages", Icons.Default.BookmarkBorder) {
                    openChat("saved"); scope.launch { drawerState.close() }
                }
                HorizontalDivider(Modifier.padding(vertical = 12.dp))
                DrawerLink("About BlackInk", Icons.Default.Info) {
                    showAbout = true; scope.launch { drawerState.close() }
                }
                Spacer(Modifier.weight(1f))
                Text("BlackInk for Android\nLocal demo · 1.0", Modifier.padding(28.dp), color = SecondaryText, fontSize = 12.sp)
            }
        },
    ) {
        if (selected != null) {
            ConversationScreen(selected, messages.filter { it.conversationId == selected.id },
                onBack = { selectedId = null }, onSend = { viewModel.sendMessage(selected.id, it) },
                onInfo = { showAbout = true })
        } else {
            Scaffold(
                topBar = {
                    Column(Modifier.background(InkBlue)) {
                        TopAppBar(
                            title = {
                                if (searching) {
                                    TextField(value = query, onValueChange = { query = it }, placeholder = { Text("Search", color = Color(0xFFCCDEED)) },
                                        singleLine = true,
                                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent,
                                            focusedTextColor = Color.White, unfocusedTextColor = Color.White, cursorColor = Color.White,
                                            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                                        modifier = Modifier.fillMaxWidth())
                                } else Text("BlackInk", fontWeight = FontWeight.Medium, fontSize = 22.sp)
                            },
                            navigationIcon = {
                                IconButton(onClick = {
                                    if (searching) { searching = false; query = "" }
                                    else scope.launch { drawerState.open() }
                                }) { Icon(if (searching) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu, if (searching) "Close search" else "Open menu") }
                            },
                            actions = {
                                if (!searching) IconButton(onClick = { searching = true }) { Icon(Icons.Default.Search, "Search chats") }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = InkBlue, titleContentColor = Color.White, navigationIconContentColor = Color.White, actionIconContentColor = Color.White),
                        )
                        PrimaryTabRow(selectedTabIndex = filter, containerColor = InkBlue, contentColor = Color.White,
                            indicator = {
                                TabRowDefaults.PrimaryIndicator(Modifier.tabIndicatorOffset(filter), color = Color.White, width = 42.dp)
                            }, divider = {}) {
                            listOf("All chats", "Unread", "Groups").forEachIndexed { index, title ->
                                Tab(selected = filter == index, onClick = { filter = index }, selectedContentColor = Color.White, unselectedContentColor = Color(0xFFBDD3E5),
                                    text = { Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                                        Text(title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                                        if (index == 1) {
                                            val count = conversations.count { it.unreadCount > 0 }
                                            if (count > 0) Box(Modifier.background(Color.White.copy(alpha = .2f), CircleShape).padding(horizontal = 6.dp, vertical = 1.dp)) {
                                                Text(count.toString(), fontSize = 11.sp)
                                            }
                                        }
                                    } })
                            }
                        }
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { showNewChat = true }, shape = CircleShape, containerColor = AccentBlue, contentColor = Color.White) {
                        Icon(Icons.Default.Edit, "New conversation")
                    }
                },
            ) { insets ->
                val visible = conversations.filter {
                    (filter != 1 || it.unreadCount > 0) && (filter != 2 || it.isGroup) &&
                        (it.title.contains(query, true) || it.preview.contains(query, true))
                }.sortedByDescending { it.isPinned }
                LazyColumn(Modifier.fillMaxSize().padding(insets), contentPadding = PaddingValues(bottom = 88.dp)) {
                    items(visible, key = { it.id }) { conversation ->
                        ChatRow(conversation) { openChat(conversation.id) }
                    }
                    if (visible.isEmpty()) item {
                        Column(Modifier.fillMaxWidth().padding(48.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Icon(Icons.Default.ChatBubbleOutline, null, Modifier.size(44.dp), tint = SecondaryText)
                            Text(if (query.isNotEmpty()) "No chats found" else "You're all caught up", fontWeight = FontWeight.Medium)
                            Text(if (query.isNotEmpty()) "Try a different name or message." else "Your unread conversations will appear here.", color = SecondaryText)
                        }
                    }
                    item {
                        Text("Sample conversations · Local demo", Modifier.fillMaxWidth().padding(22.dp), textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = SecondaryText, fontSize = 11.sp)
                    }
                }
            }
        }
    }
    if (showNewChat) AlertDialog(onDismissRequest = { showNewChat = false; newName = "" },
        title = { Text("New conversation") },
        text = { Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Start a local conversation. Messages stay in this demo session.", color = SecondaryText)
            OutlinedTextField(value = newName, onValueChange = { newName = it }, label = { Text("Name") }, singleLine = true)
        } },
        confirmButton = { TextButton(enabled = newName.isNotBlank(), onClick = {
            viewModel.createConversation(newName) { openChat(it) }; newName = ""; showNewChat = false
        }) { Text("Create") } },
        dismissButton = { TextButton(onClick = { showNewChat = false; newName = "" }) { Text("Cancel") } })
    if (showAbout) AlertDialog(onDismissRequest = { showAbout = false }, title = { Text("BlackInk") },
        text = { Text("A Telegram-inspired Android interface. These are sample conversations. Messages are stored in memory for this session; they are not sent to other people. Accounts, encryption, calls, and a backend are not connected yet.") },
        confirmButton = { TextButton(onClick = { showAbout = false }) { Text("Got it") } })
}

@Composable
private fun DrawerLink(title: String, icon: ImageVector, onClick: () -> Unit) {
    NavigationDrawerItem(label = { Text(title) }, selected = false, onClick = onClick,
        icon = { Icon(icon, null, tint = InkBlue) }, modifier = Modifier.padding(horizontal = 12.dp))
}

@Composable
private fun Avatar(conversation: Conversation, size: Int = 56) {
    Box(Modifier.size(size.dp).background(Color(conversation.avatarColor), CircleShape), contentAlignment = Alignment.Center) {
        if (conversation.id == "saved") Icon(Icons.Default.BookmarkBorder, null, Modifier.size((size / 2).dp), tint = Color.White)
        else Text(conversation.title.split(" ").take(2).mapNotNull { it.firstOrNull()?.toString() }.joinToString(""),
            color = Color.White, fontSize = (size * .34).sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun ChatRow(conversation: Conversation, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = onClick).padding(start = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Avatar(conversation)
        Spacer(Modifier.width(14.dp))
        Column(Modifier.weight(1f)) {
            Row(Modifier.fillMaxWidth().heightIn(min = 79.dp).padding(top = 14.dp, bottom = 13.dp, end = 16.dp)) {
                Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (conversation.isGroup) { Icon(Icons.Default.Group, null, Modifier.size(17.dp)); Spacer(Modifier.width(4.dp)) }
                        Text(conversation.title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f, fill = false))
                        if (conversation.isMuted) Icon(Icons.Default.NotificationsOff, "Muted", Modifier.padding(start = 5.dp).size(14.dp), tint = Color(0xFFABB5BD))
                    }
                    Text(conversation.preview, fontSize = 14.sp, color = SecondaryText, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                Spacer(Modifier.width(10.dp))
                Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        if (conversation.isOutgoing) Icon(Icons.Default.Check, "Local message", Modifier.size(15.dp), tint = AccentBlue)
                        Text(conversation.time, fontSize = 11.sp, color = if (conversation.unreadCount > 0 && !conversation.isMuted) AccentBlue else SecondaryText)
                    }
                    if (conversation.unreadCount > 0) {
                        Box(Modifier.heightIn(min = 21.dp).widthIn(min = 21.dp).background(if (conversation.isMuted) Color(0xFFB9C2C9) else AccentBlue, CircleShape).padding(horizontal = 6.dp, vertical = 2.dp), contentAlignment = Alignment.Center) {
                            Text(conversation.unreadCount.toString(), color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                        }
                    } else if (conversation.isPinned) Icon(Icons.Default.PushPin, "Pinned", Modifier.size(16.dp), tint = Color(0xFFABB5BD))
                }
            }
            HorizontalDivider(color = Color(0xFFEDF0F2))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConversationScreen(conversation: Conversation, messages: List<Message>, onBack: () -> Unit, onSend: (String) -> Unit, onInfo: () -> Unit) {
    var draft by rememberSaveable(conversation.id) { mutableStateOf("") }
    val listState = rememberLazyListState()
    LaunchedEffect(messages.size) { if (messages.isNotEmpty()) listState.animateScrollToItem(messages.size) }
    Scaffold(
        modifier = Modifier.imePadding(),
        containerColor = Color(0xFFDCE6DC),
        topBar = {
            TopAppBar(title = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Avatar(conversation, 40)
                    Column {
                        Text(conversation.title, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 17.sp, fontWeight = FontWeight.Medium)
                        Text(if (conversation.id == "saved") "Your personal notes" else "local demo conversation", color = Color(0xFFCEE0EE), fontSize = 12.sp)
                    }
                }
            }, navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back to chats") } },
                actions = { IconButton(onClick = onInfo) { Icon(Icons.Default.Info, "Conversation information") } },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = InkBlue, titleContentColor = Color.White, navigationIconContentColor = Color.White, actionIconContentColor = Color.White))
        },
        bottomBar = {
            Surface(color = Color.White) {
                Row(Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 10.dp, vertical = 6.dp), verticalAlignment = Alignment.Bottom) {
                    TextField(value = draft, onValueChange = { draft = it }, placeholder = { Text("Message") }, modifier = Modifier.weight(1f), maxLines = 5,
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent))
                    IconButton(onClick = { if (draft.isNotBlank()) { onSend(draft); draft = "" } }, enabled = draft.isNotBlank(), modifier = Modifier.padding(bottom = 4.dp)) {
                        Icon(Icons.AutoMirrored.Filled.Send, "Send local message", tint = if (draft.isNotBlank()) AccentBlue else Color(0xFFB7C1CA))
                    }
                }
            }
        },
    ) { insets ->
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize().padding(insets), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                Box(Modifier.fillMaxWidth().padding(vertical = 12.dp), contentAlignment = Alignment.Center) {
                    Text("Demo conversation", Modifier.background(Color(0xFF94AB98), RoundedCornerShape(14.dp)).padding(horizontal = 12.dp, vertical = 5.dp), color = Color.White, fontSize = 12.sp)
                }
            }
            items(messages, key = { it.id }) { message ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = if (message.isOutgoing) Arrangement.End else Arrangement.Start) {
                    Surface(color = if (message.isOutgoing) Color(0xFFE3FFC9) else Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = if (message.isOutgoing) 16.dp else 4.dp, bottomEnd = if (message.isOutgoing) 4.dp else 16.dp),
                        shadowElevation = .5.dp, modifier = Modifier.widthIn(max = 300.dp)) {
                        Column(Modifier.padding(start = 12.dp, end = 10.dp, top = 9.dp, bottom = 6.dp)) {
                            Text(message.text, fontSize = 15.sp, lineHeight = 21.sp)
                            Text(message.time, Modifier.align(Alignment.End).padding(top = 3.dp), color = Color(0xFF819779), fontSize = 10.sp)
                        }
                    }
                }
            }
        }
    }
}
