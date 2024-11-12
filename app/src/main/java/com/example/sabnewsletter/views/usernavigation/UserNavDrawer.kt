package com.example.sabnewsletter.views.usernavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sabnewsletter.R
import com.example.sabnewsletter.repository.AuthenticationRepository
import com.example.sabnewsletter.views.navdrawer.NavigationDrawerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserNavigationBottomSheet(viewmodel:NavigationDrawerViewModel,authenticationRepository:AuthenticationRepository){
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val showBottomSheet by viewmodel.showBottomSheet.observeAsState(false)
if (showBottomSheet) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = {
            viewmodel.hideBottomSheet()
        }
    ) {
        Text(
            "Swipe up to open sheet. Swipe down to dismiss.",
            modifier = Modifier.padding(16.dp)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = {
                viewmodel.hideBottomSheet()
                authenticationRepository.logOutUser() }) {
                Text("Logout")

            }

            Text(text = "app version ${stringResource(id = R.string.app_version)}")
        }

    }
}
}
