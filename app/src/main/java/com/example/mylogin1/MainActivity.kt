package com.example.mylogin1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylogin1.ui.theme.MyLogin1Theme
import kotlinx.coroutines.selects.select

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLogin1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    
                ) {
                    LoginScreen1()
                }
            }
        }
    }
}

@Composable
fun LoginScreen1(){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    var selectedItem= remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .paint(painterResource(R.drawable.back), contentScale = ContentScale.Crop)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(R.drawable.vikash_sinha_1),
                contentDescription = "login page",
                modifier = Modifier
                    .size(150.dp)
                    .padding(16.dp)
            )
            Text(
                text = "Login",
                fontWeight = FontWeight.Bold, fontSize = 25.sp,
                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF476810))
            )

            @OptIn(ExperimentalMaterial3Api::class)
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            @OptIn(ExperimentalMaterial3Api::class)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedItem.value == "Google",
                        onClick = {
                            selectedItem.value = "Google"

                        })
                    Text(text = "Google", fontWeight = FontWeight.Bold, fontSize = 25.sp)

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedItem.value == "Amazon",
                        onClick = {
                            selectedItem.value = "Amazon"
                        })
                    Text(text = "Amazon", fontWeight = FontWeight.Bold, fontSize = 25.sp)

                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = selectedItem.value == "Flipkart",
                        onClick = {
                            selectedItem.value = "Flipkart"
                        })
                    Text(text = "Flipkart", fontWeight = FontWeight.Bold, fontSize = 25.sp)

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    var url = "https://www.google.com/"
                    if (selectedItem.value == "Google") {
                        url = "https://www.google.com/"
                    } else if (selectedItem.value == "Amazon") {
                        url = "https://www.amazon.in/"
                    } else {
                        url = "https://www.flipkart.com/"
                    }

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                    if (intent.resolveActivity(context.packageManager) != null) {
                        context.startActivity(intent)

                    } else {
                        Toast.makeText(context, "No web browser available", Toast.LENGTH_LONG)
                            .show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "open website")

            }

        }
    }

}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyLogin1Theme {
        LoginScreen1()
    }
}