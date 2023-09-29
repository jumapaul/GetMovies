package com.example.getmoview.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.getmoview.domain.model.genre.Genre

@Composable
fun ListingItem(
    genre: Genre,
    selectedGenreIds: MutableState<List<Int>>
) {

    val isChecked = selectedGenreIds.value.contains(genre.id)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                selectedGenreIds.value = if (checked){
                    selectedGenreIds.value + genre.id
                }else{
                    selectedGenreIds.value - genre.id
                } },
            modifier = Modifier.size(25.dp)
        )

        Text(text = genre.name)
    }



}