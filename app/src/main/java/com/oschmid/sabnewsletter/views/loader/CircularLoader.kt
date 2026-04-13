package com.oschmid.sabnewsletter.views.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.oschmid.sabnewsletter.ui.theme.SabencosBlue
import com.oschmid.sabnewsletter.ui.theme.SabencosYellow


@Composable
fun CircularProgressComposable(
    ) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .background(SabencosYellow),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = SabencosYellow,
            trackColor = SabencosBlue,
        )

    }

    }
