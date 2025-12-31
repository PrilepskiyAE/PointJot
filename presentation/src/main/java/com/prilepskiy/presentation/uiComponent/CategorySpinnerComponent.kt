package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.prilepskiy.common.Gray80
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.Spaces
import com.prilepskiy.domain.model.CategoryModel


@Composable
fun CategorySpinnerComponent(
    categoryList: List<CategoryModel>,
    selectedCategory: CategoryModel?,
    onCategorySelected: (CategoryModel) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedName = selectedCategory?.categoryName ?: "Выберите категорию"

    Box(modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spaces.space60)
                .padding(vertical = Spaces.space2, horizontal = Spaces.space16)
                .clickable { expanded = true }
                .border(Spaces.space1, Gray80, RoundedCornerShape(Spaces.space8))
                .padding(horizontal = Spaces.space16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedName,
                color = if (selectedCategory == null) Gray80 else Gray90,
                modifier = Modifier.weight(1f) // Занимает всё пространство
            )
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp
                else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = Gray80,
                modifier = Modifier.size(Spaces.space24)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categoryList.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    },
                    text = { Text(category.categoryName) }
                )
            }
        }
    }
}
