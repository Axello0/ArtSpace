package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
/*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
 */
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme
import kotlinx.coroutines.launch
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PhotographyGallery()
                }
            }
        }
    }
}

data class Photo(val resourceId: Int, val descriptionname: String, val photographername: String, val year: Int)

val photoList = listOf(
    Photo(R.drawable.pic1, "Uno Lantern", "Andrea De Santis", 2023),
    Photo(R.drawable.pic2, "The Red Pagoda", "Anna Ryebank", 2018),
    Photo(R.drawable.pic3, "Overlooking Buddha", "Daniel Gubo", 2016),
    Photo(R.drawable.pic4, "Summer Weather", "Matteo bell", 2012),
    Photo(R.drawable.pic5, "Well Below", "Marta Flores", 2017),
    Photo(R.drawable.pic6, "Hikimaru", "Ryutaro Tsukata", 2020),
    Photo(R.drawable.pic7, "The Observation Platform", "Eva Bronzini", 2021),
    Photo(R.drawable.pic8, "The Hidden World", "Yusuke Furuya", 2022),
    Photo(R.drawable.pic9, "White as Snow", "Clyde Thomas", 2023),
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PhotographyGallery() {
    val customShadeColor = Color(0xFFececec)
    val pagerState = rememberPagerState(pageCount = { photoList.size })
    val animationScope = rememberCoroutineScope()

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            val currentPhoto = photoList[pagerState.currentPage]

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().height(500.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = currentPhoto.resourceId),
                    contentDescription = currentPhoto.descriptionname,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                        .border(width = 30.dp, color = Color.White)
                        .shadow(3.dp, RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(customShadeColor)
                    .padding(vertical = 4.dp, horizontal = 5.dp)
                    .width(300.dp)
                    .height(75.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    Text(
                        text = currentPhoto.descriptionname,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.wrapContentWidth().padding(start = 15.dp, top = 4.dp)
                    )

                    val annotatedString = buildAnnotatedString {
                        append(currentPhoto.photographername)
                        addStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold),
                            start = 0,
                            end = currentPhoto.photographername.length
                        )
                        append(" (${currentPhoto.year})")
                    }

                    Text(
                        text = annotatedString,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 15.dp, top = 4.dp).wrapContentWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        Box(
            modifier = Modifier.align(Alignment.BottomCenter).padding(horizontal = 16.dp, vertical = 15.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        animationScope.launch {
                            val previousPage = if (pagerState.currentPage == 0) {
                                pagerState.pageCount - 1
                            } else {
                                pagerState.currentPage - 1
                            }
                            pagerState.animateScrollToPage(previousPage)
                        }
                    },
                    modifier = Modifier.width(140.dp)
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Previous")
                    Text(text = "Previous")
                }


                Spacer(modifier = Modifier.width(50.dp))

                Button(
                    onClick = {
                        animationScope.launch {
                            val nextPage = if (pagerState.currentPage == pagerState.pageCount - 1) {
                                0 // wrap to the first page
                            } else {
                                pagerState.currentPage + 1
                            }
                            pagerState.animateScrollToPage(nextPage)
                        }
                    },
                    modifier = Modifier.width(140.dp)
                ) {
                    Text(text = "Next")
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Next")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotographyGalleryPreview() {
    ArtSpaceTheme {
        PhotographyGallery()
    }
}
