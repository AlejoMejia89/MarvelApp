package com.alejandromejia.marvelapp.ui.comics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.alejandromejia.marvelapp.R
import com.alejandromejia.marvelapp.domain.models.comics.DResult
import com.alejandromejia.marvelapp.ui.components.models.ViewModels
import com.alejandromejia.marvelapp.utils.DESCRIPTION
import com.alejandromejia.marvelapp.utils.FORMAT
import com.alejandromejia.marvelapp.utils.FontFamilyProvider
import com.alejandromejia.marvelapp.utils.HTTP
import com.alejandromejia.marvelapp.utils.HTTPS
import com.alejandromejia.marvelapp.utils.MODIFIED_DATE
import com.alejandromejia.marvelapp.utils.NO_DATA_FOUND
import com.alejandromejia.marvelapp.utils.PAGES
import com.alejandromejia.marvelapp.utils.formatDate

@Composable
fun ComicScreenView(
    viewModels: ViewModels,
    comic: DResult,
    attributionText: String
) {

    var favoriteClicked by rememberSaveable { mutableStateOf(comic.isFavorite) }

    Card(
        modifier = Modifier
            .background(Color.Black),
        contentColor = Color.White
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.Black)
        ) {
            val (boxImage, boxComponents, rowContent, descriptionContent) = createRefs()
            val likeIcon: Painter = painterResource(id = R.drawable.ic_favorite)
            val description = comic.description
            val title = comic.title

            Column(
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, bottom = 10.dp)
                    .constrainAs(boxComponents) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 3.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }

            Box(modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .constrainAs(boxImage) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(boxComponents.bottom)
                }) {
                ImageComicAppView(
                    path = "${
                        comic.thumbnail.path.replace(
                            HTTP,
                            HTTPS
                        )
                    }.${comic.thumbnail.extension}",
                    startY = 0F,
                    endY = 300F,
                    startColor = colorResource(id = R.color.transparent_black),
                    endColor = Color.Transparent,
                    onItemSelected = {}
                )

                IconButton(
                    onClick = {
                        favoriteClicked = !favoriteClicked
                        if (favoriteClicked) {
                            viewModels.localComicsViewModel.saveFavoriteComics(comic)
                        } else {
                            viewModels.localComicsViewModel.removeFavorite(comic)
                        }

                    },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        painter = likeIcon,
                        contentDescription = null,
                        tint = if (favoriteClicked) Color.Red else Color.White
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp, start = 30.dp, end = 30.dp)
                    .constrainAs(rowContent) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(boxImage.bottom)
                    }, horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .background(Color.DarkGray)
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = FORMAT,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 3.dp),
                            fontSize = 12.sp
                        )
                        Text(
                            text = comic.format,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 3.dp),
                            fontSize = 10.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .background(Color.DarkGray)
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = PAGES,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 3.dp),
                            fontSize = 12.sp
                        )
                        Text(
                            text = "${comic.pageCount}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 3.dp),
                            fontSize = 10.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .background(Color.DarkGray)
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            text = MODIFIED_DATE,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 3.dp),
                            fontSize = 12.sp
                        )
                        Text(
                            text = comic.modified.formatDate(),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 3.dp),
                            fontSize = 10.sp
                        )
                    }
                }

            }

            Column(modifier = Modifier.constrainAs(descriptionContent) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(rowContent.bottom)
            }) {
                Text(
                    text = DESCRIPTION,
                    color = Color.White,
                    modifier = Modifier.padding(top = 20.dp, bottom = 10.dp, start = 20.dp),
                    fontFamily = FontFamilyProvider.openSans,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description.ifEmpty { NO_DATA_FOUND },
                    color = Color.White,
                    fontFamily = FontFamilyProvider.openSans,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth(),
                    textAlign = if (description.isNotEmpty()) TextAlign.Justify else TextAlign.Center,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    maxLines = 5
                )
                Text(
                    text = attributionText,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .fillMaxWidth(),
                    fontFamily = FontFamilyProvider.openSans,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                )
            }


        }
    }
}
