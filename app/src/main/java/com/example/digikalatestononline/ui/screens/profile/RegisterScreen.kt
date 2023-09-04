package com.example.digikalatestononline.ui.screens.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikalatestononline.R
import com.example.digikalatestononline.data.model.home.AmazingItem
import com.example.digikalatestononline.data.remote.NetworkResult
import com.example.digikalatestononline.ui.theme.LocalSpacing
import com.example.digikalatestononline.ui.theme.Typography
import com.example.digikalatestononline.ui.theme.darkText
import com.example.digikalatestononline.ui.theme.h6
import com.example.digikalatestononline.ui.theme.selectedBottomBar
import com.example.digikalatestononline.util.Constants.USER_PHONE
import com.example.digikalatestononline.util.Constants.USER_TOKEN
import com.example.digikalatestononline.util.InputValidation
import com.example.digikalatestononline.util.InputValidation.isValidPassword
import com.example.digikalatestononline.viewmodel.DataStoreViewModel
import com.example.digikalatestononline.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(viewModel: ProfileViewModel = hiltViewModel(),dataStore : DataStoreViewModel = hiltViewModel()) {
    val context = LocalContext.current

    LaunchedEffect(Dispatchers.Main){
        viewModel.loginResponse.collectLatest {loginResponse->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let {user ->
                        if (user.token.isNotEmpty()){
                            dataStore.saveUserTOKEN(user.token)
                            dataStore.saveUserID(user.id)
                            dataStore.saveUserPHONE(user.phone)
                            USER_PHONE = user.phone
                            USER_TOKEN = user.token
                            dataStore.saveUserPASSWORD(viewModel.inputPasswordState)

                            viewModel.screenState = ProfileScreenState.PROFILE_STATE
                        }
                    }
                    Toast.makeText(
                        context,
                        loginResponse.message,
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.loading = false
                }

                is NetworkResult.Error -> {
                    Log.e("6191", "loginResponse Api Error is :${loginResponse.message}")
                    viewModel.loading = false
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }


    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.digi_settings
                    ), contentDescription = "",
                    modifier = Modifier
                        .padding(
                            LocalSpacing.current.small
                        )
                        .size(LocalSpacing.current.semiLarge),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .padding(LocalSpacing.current.small),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }
        }

        Spacer(modifier = Modifier.height(LocalSpacing.current.extraLarge))

        Text(
            text = stringResource(id = R.string.set_password_text),
            modifier = Modifier.padding(
                horizontal = LocalSpacing.current.semiLarge
            ),
            style = Typography.h6,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold
        )

        MyEditText(
            value = viewModel.textFieldInput,
            placeholder = stringResource(id = R.string.phone_and_email),
            onValueChange = {

            }
        )

        MyEditText(
            value = viewModel.inputPasswordState,
            placeholder = stringResource(id = R.string.set_password_text),
            onValueChange = {
                viewModel.inputPasswordState = it
            }
        )

        Spacer(modifier = Modifier.height(LocalSpacing.current.medium))
        if (viewModel.loading){
            LoadingButton()
        }else{
            MyButton(text = stringResource(id = R.string.digikala_entry)) {
                if (isValidPassword(viewModel.inputPasswordState)) {
                    viewModel.login()
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


}