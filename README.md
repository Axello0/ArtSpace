# ArtSpace

Step 1 : I Studied using the Android Studio Beginner developer 

Step 2 : Used ChatGpt to ask what imports should i use for this task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme
import kotlinx.coroutines.launch


Step 3: Used ChatGpt on an example for this Art Space but I didnt understand with my knowledge so i tried it on my own and asked chatGPT for the normal fixes

Step 4: I didnt know how to do the motions using scaffold given by the link from the teacher , so i asked ChatGPT to find another method to help me scroll and ChatGPT gave me the pager which i have edited already since this is from my code

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
            

Step 5: Using the Knowledge i had i made it so that after it is the last picture it goes back to the original page by using count/Current page 

Step 6: whilst doing this i had an arrow for the image vector which i asked ChatGPT for and it was to fix the Icons.AutoMirrored.Filled.ArrowForward and the ArrowBackward

This is my github: https://github.com/Axello0