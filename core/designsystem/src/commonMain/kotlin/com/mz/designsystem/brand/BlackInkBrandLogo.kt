package com.mz.designsystem.brand

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import blackink.core.designsystem.generated.resources.Res
import blackink.core.designsystem.generated.resources.blackink
import blackink.core.designsystem.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.vectorResource

@Composable
fun BlackInkBrandLogo(
    modifier: Modifier = Modifier
){
    Icon(
        imageVector = vectorResource(Res.drawable.blackink),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}