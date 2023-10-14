package com.example.getmoview.ui.screens.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.getmoview.R
import com.example.getmoview.ui.composables.ExpandButton
import io.ktor.http.content.Version

@Composable
fun SettingScreen() {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "About",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.Bold
        )

        var sourceExpanded by remember {
            mutableStateOf(false)
        }

        var policyExpanded by remember {
            mutableStateOf(false)
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.github), contentDescription = null,
                    modifier = Modifier.size(25.dp, 25.dp)
                )

                Text(text = "Source code on github", modifier = Modifier.weight(1.0f))

                ExpandButton(expanded = sourceExpanded, onClick = {
                    sourceExpanded = !sourceExpanded
                })
            }

            if (sourceExpanded) {
                ExpandedListItem()
            }
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Policy, contentDescription = null
                )

                Text(text = "Privacy Policy", modifier = Modifier.weight(1.0f))

                ExpandButton(expanded = policyExpanded, onClick = {
                    policyExpanded = !policyExpanded
                })
            }

            if (policyExpanded) {
                ExpandedListItem()
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(imageVector = Icons.Default.WatchLater, contentDescription = null)
            Text(text = "Version",
                modifier = Modifier.weight(1f)
                )
            Text(text = "1.0.0")
        }


    }

}

@Composable
fun ExpandedListItem() {
    Text(text = "process 20277 on device 'samsung-sm_a326u-adb-RFCR821JERF-gMzqll._adb-tls-connect._tcp'.")
}