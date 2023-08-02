package com.example.areader.screens.home

import android.annotation.SuppressLint
import android.telecom.Call.Details
import android.widget.HorizontalScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.areader.R
import com.example.areader.components.BookRating
import com.example.areader.components.FABContent
import com.example.areader.components.ListCard
import com.example.areader.components.ReaderAppBar
import com.example.areader.components.TitleSection
import com.example.areader.model.MBook
import com.example.areader.navigation.ReaderScreens
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = {
 ReaderAppBar(title = "A.Reader", navController = navController )
    },
        floatingActionButton = {
            FABContent {}
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeContent(navController)
        }
    }

}
@Composable
fun HomeContent(navController: NavController){
    val listOfBooks = listOf(
        MBook(id = "dadfa", title = "Hello again", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = "Hello ", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = "again", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = "Hello again", authors = "All of us", notes = null),
        MBook(id = "dadfa", title = "Hello again", authors = "All of us", notes = null)




        )
   //me @gmail.com
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if(!email.isNullOrEmpty())
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
    else "N/A"
    Column(Modifier.padding(2.dp),
    verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your Reading \n" + " activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))

            Column {
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                     modifier = Modifier
                         .clickable {
                             navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                         }
                         .size(45.dp),
                tint = MaterialTheme.colors.secondaryVariant)
                Text(text = currentUserName!!,
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                overflow = TextOverflow.Clip)
                Divider()

            }}


        ReadingRightNowArea(books = listOf(), navController = navController )

        TitleSection(label = "Reading List")

        BookListArea(listOfBooks = listOfBooks, navController = navController)

}}

@Composable
fun BookListArea(listOfBooks: List<MBook>,
                 navController: NavController) {
        HorizontalScrollableComponent(listOfBooks){
            //todo on card click go to detail
        }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>, onCardPress: (String)-> Unit) {
val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
        for(book in listOfBooks){
            ListCard(book){
                onCardPress(it)
            }
        }
    }
}


@Composable
fun ReadingRightNowArea(books: List<MBook>, navController: NavController){

}
