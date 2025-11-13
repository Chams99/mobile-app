package com.example.tp1

@Composable
fun RecordsPage() {
    val context = LocalContext.current
    val db = remember { DB.get(context) }
    val scope = rememberCoroutineScope()

    var items by remember { mutableStateOf(listOf<UserForm>()) }

    LaunchedEffect(Unit) {
        scope.launch {
            items = db.dao().getAll()
        }
    }

    Column(Modifier.padding(16.dp)) {
        Text("Saved Records")

        Spacer(Modifier.height(12.dp))

        items.forEach { user ->
            Text("${user.name} - ${user.email} - ${user.country}")
            Spacer(Modifier.height(8.dp))
        }
    }
}
