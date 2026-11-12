package com.oschmid.sabnewsletter.views.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


//@Composable
//fun FunkyCardDraw(){
//    val cardSize = 150.dp
//    val cardBackground = Color.Black
//    val bubbleColor = Color(0xFFf3f3f3)
//    val backgroundColor = Color.White
////#TODO: add below once function looks like working
////    cardBackground: Color = Color.Black,
////    bubbleColor: Color = Color(0xFFf3f3f3),
////    backgroundColor: Color = Color.White,
////    cardSize: Dp = 150.dp,
//    val radius = cardSize.value / 2f
//    Box(){
//
//        Card(
//            modifier = Modifier.size(cardSize).clip(RoundedCornerShape(15.dp)),
//            colors = CardDefaults.cardColors(containerColor = Color.Black)
//        ) { Canvas(modifier = Modifier.size(cardSize), onDraw = {
//            drawRect(
//                color = backgroundColor,
//                topLeft = Offset(x = size.width - radius + (radius * 0.2f), y = 12f),
//                size = size / 2f,
//            )
//
//            drawRect(
//                color = backgroundColor,
//                topLeft = Offset(x = cardSize.value * 1.3f, y = cardSize.value * -1f),
//                size = size / 2f,
//            )
//
//            drawCircle(
//                color = backgroundColor,
//                radius = cardSize.value / 1.5f,
//                center = Offset(
//                    x = size.width - radius + (radius * 0.2f),
//                    y = radius - (radius * 0.2f)
//                )
//            )
//
//            drawCircle(
//                color = cardBackground,
//                radius = radius * 0.8f,
//                center = Offset(
//                    x = size.width / 2.14f,
//                    y = radius - (radius * 0.2f)
//                )
//            )
//
//            drawCircle(
//                color = cardBackground,
//                radius = radius * 0.8f,
//                center = Offset(
//                    x = size.width - radius + (radius * 0.2f),
//                    y = radius + (radius * 1.93f)
//                )
//            )
//
//            drawCircle(
//                color = bubbleColor,
//                radius = radius * 0.8f,
//                center = Offset(
//                    x = size.width - radius + (radius * 0.2f),
//                    y = radius - (radius * 0.2f)
//                )
//            )
//
//        }) }
//    }
//
//}
//
//@Composable
//fun TopRightShapes() {
//    // 1. Define the Hexagon Shape
//    val hexagonShape = GenericShape { size, _ ->
//        val width = size.width
//        val height = size.height
//        moveTo(width * 0.5f, 0f)
//        lineTo(width, height * 0.25f)
//        lineTo(width, height * 0.75f)
//        lineTo(width * 0.5f, height)
//        lineTo(0f, height * 0.75f)
//        lineTo(0f, height * 0.25f)
//        close()
//    }
//
//    // 2. Main Box matching screen/container size
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.TopEnd
//    ) {
//        // 3. Hexagon holding the image (placed in the top right)
//        Image(
//            painter = painterResource(id = R.drawable.your_image), // Replace with your image resource
//            contentDescription = "Hexagon Image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier
//                .offset(x = 20.dp, y = (-20).dp) // Adjust based on overlap requirements
//                .size(120.dp)
//                .clip(hexagonShape)
//        )
//
//        // 4. Triangle (partially overlaps the hexagon on the top right)
//        Canvas(
//            modifier = Modifier
//                .offset(x = 60.dp, y = (-40).dp) // Tweak positioning to achieve overlap
//                .size(100.dp)
//        ) {
//            val path = Path().apply {
//                moveTo(size.width, 0f) // Top Right Corner
//                lineTo(0f, size.height) // Bottom Left Corner
//                lineTo(size.width, size.height) // Bottom Right Corner
//                close()
//            }
//            drawPath(path = path, color = Color.Blue)
//        }
//    }
//}

