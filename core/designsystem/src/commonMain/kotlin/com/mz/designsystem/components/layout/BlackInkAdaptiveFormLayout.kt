package com.mz.designsystem.components.layout


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mz.designsystem.theme.extended
import com.mz.presentation.util.DeviceConfiguration
import com.mz.presentation.util.currentDeviceConfiguration
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BlackInkAdaptiveFormLayout(
    headerText: String,
    errorText: String,
    logo: @Composable () -> Unit,
    formContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = currentDeviceConfiguration()
    val headerColor = if (configuration == DeviceConfiguration.MOBILE_LANDSCAPE) {
        MaterialTheme.colorScheme.onBackground
    } else {
        MaterialTheme.colorScheme.extended.textPrimary
    }

    when (configuration) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            BlackInkSurface(
                modifier = Modifier
                    .consumeWindowInsets(WindowInsets.navigationBars)
                    .consumeWindowInsets(WindowInsets.displayCutout),
                header = {
                    Spacer(modifier = Modifier.height(32.dp))
                    logo()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                AuthHeaderSection(
                    headerText = headerText,
                    errorText = errorText,
                    headerColor = headerColor
                )
                Spacer(modifier = Modifier.height(32.dp))
                formContent()
            }
            Spacer(modifier = Modifier.height(32.dp))
            AuthHeaderSection(
                headerText = headerText,
                errorText = errorText,
                headerColor = headerColor
            )
        }

        else -> {}
    }
    DeviceConfiguration.MOBILE_LANDSCAPE
    DeviceConfiguration.MOBILE_PORTRAIT
    DeviceConfiguration.TABLET_PORTRAIT
    DeviceConfiguration.TABLET_LANDSCAPE
    DeviceConfiguration.DESKTOP
}

@Composable
fun AuthHeaderSection(
    headerText: String,
    errorText: String,
    headerColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = headerText,
        style = MaterialTheme.typography.titleLarge,
        color = headerColor,
        modifier = Modifier.fillMaxWidth()
    )
    AnimatedVisibility(
        visible = true
    ) {
        if (errorText != null) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}

@Composable
fun ColumnScope.AuthHeaderSection(
    headerText: String,
    errorText: String? = null,
    headerColor: Color,
    modifier: Modifier = Modifier
) {

}


@Composable
@Preview
fun BlackInkAdaptiveFormPreview() {
    BlackInkAdaptiveFormLayout(
        headerText = "Welcome to BlackInk!",
        errorText = "Something went wrong",
        logo = {},
        formContent = {}
    )
}
