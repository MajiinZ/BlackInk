package com.mz.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mz.designsystem.theme.BlackInkTheme
import com.mz.designsystem.theme.extended
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BlackInkIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit

) {
    OutlinedIconButton(
        onClick = onClick,
        modifier = modifier
            .size(45.dp),
        shape = RoundedCornerShape(8.dp),
        colors = IconButtonDefaults.outlinedIconButtonColors(
            contentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.extended.textSecondary
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
        ),
        content = content
    )

}

@Composable
@Preview
fun BlackInkButtonPreview() {
    BlackInkTheme {
        BlackInkIconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null
            )
        }
    }
}
@Composable
@Preview
fun BlackInkButtonDarkThemePreview() {
    BlackInkTheme {
        BlackInkIconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = null
            )
        }
    }
}
