package com.mz.designsystem.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mz.designsystem.theme.BlackInkTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class BlackInkButtonStyle {
    PRIMARY,
    DESTRUCTIVE_PRIMARY,
    DESTRUCTIVE_SECONDARY,
    SECONDARY,
    TEXT,
}

@Composable
fun BlackInkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: BlackInkButtonStyle = BlackInkButtonStyle.PRIMARY,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val colors = when(style){
        BlackInkButtonStyle.PRIMARY -> buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        BlackInkButtonStyle.DESTRUCTIVE_PRIMARY -> buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        BlackInkButtonStyle.DESTRUCTIVE_SECONDARY -> buttonColors(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        BlackInkButtonStyle.SECONDARY -> buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        BlackInkButtonStyle.TEXT -> buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(15.dp)
                    .alpha(
                        alpha = if(isLoading) 1f else 0f
                    ),
                strokeWidth = 1.5.dp,
                color = Color.Black
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    8.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.alpha(
                    if(isLoading) 0f else 1f
                )
            ) {
                leadingIcon?.invoke()
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

@Preview
@Composable
fun BlackInkPrimaryButtonPreview() {

    BlackInkTheme {
        BlackInkButton(
            onClick = { },
            text = "Button_prim",

            )
    }
}


@Preview
@Composable
fun BlackInkSecondaryButtonPreview() {

    BlackInkTheme {
        BlackInkButton(
            onClick = { },
            text = "Button_sec",
            style = BlackInkButtonStyle.SECONDARY

        )
    }
}

@Preview
@Composable
fun BlackInkDestructivePrimaryButtonPreview() {

    BlackInkTheme {
        BlackInkButton(
            onClick = { },
            text = "Button_primary",
            style = BlackInkButtonStyle.DESTRUCTIVE_PRIMARY

        )
    }
}

@Preview
@Composable
fun BlackInkSecondaryDestructiveButtonPreview() {

    BlackInkTheme {
        BlackInkButton(
            onClick = { },
            text = "Button_secondary",
            style = BlackInkButtonStyle.DESTRUCTIVE_SECONDARY

        )
    }
}

@Preview
@Composable
fun BlackInkTextButtonPreview() {

    BlackInkTheme {
        BlackInkButton(
            onClick = { },
            text = "Button_text",
            style = BlackInkButtonStyle.TEXT

        )
    }
}