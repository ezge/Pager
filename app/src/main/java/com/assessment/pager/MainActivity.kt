package com.assessment.pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.assessment.pager.ui.theme.PagerTheme
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

val drawableIds = listOf(R.drawable.cover_1, R.drawable.cover_2,
                         R.drawable.cover_3, R.drawable.cover_4, R.drawable.cover_5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoverPager(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoverPager(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState { drawableIds.size }
    val coroutineScope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth()) { page ->
            Image(
                painter = painterResource(drawableIds[page]),
                contentDescription = "cover",
                modifier = Modifier.padding(10.dp)
                                   .fillMaxWidth()
                                   .clip(shape = RoundedCornerShape(10.dp))
            )
        }
        Row {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Next Page",
                 modifier = Modifier.size(75.dp).clickable { coroutineScope.launch {
                     pagerState.animateScrollToPage(pagerState.currentPage - 1)}
                 }
            )
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next Page",
                 modifier = Modifier.size(75.dp).clickable { coroutineScope.launch {
                     pagerState.animateScrollToPage(pagerState.currentPage + 1)}
                 }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PagerTheme {
        CoverPager()
    }
}