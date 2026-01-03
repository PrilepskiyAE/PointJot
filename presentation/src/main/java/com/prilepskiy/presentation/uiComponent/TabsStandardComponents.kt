package com.prilepskiy.presentation.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.unit.dp
import com.prilepskiy.common.Black
import com.prilepskiy.common.Gray400
import com.prilepskiy.common.Gray90
import com.prilepskiy.common.Transparent
import com.prilepskiy.common.White
import com.prilepskiy.common.Yellow500

@Composable
fun TabsStandardComponents(tabs: List<String>, onClickTab: ((selectedTabIndex: Int) -> Unit)) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    TabRow(
        containerColor=Color.Transparent,
        contentColor = Color.Transparent,
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        indicator = {},
        divider = {
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier.background(Color.Transparent),
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = title,
                            color = Gray90
                        )
                        Divider(
                            thickness = 2.dp,
                            color = if (selectedTabIndex == index) Gray90 else Transparent
                        )
                    }

                },
                unselectedContentColor = Transparent,
                selectedContentColor = Transparent,
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onClickTab.invoke(index)
                }
            )
        }
    }
}