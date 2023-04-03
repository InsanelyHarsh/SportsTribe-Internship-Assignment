package com.example.demokmm.android

import android.media.Image
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.demokmm.Greeting

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MyApplicationTheme {
                var userName = remember {
                    mutableStateOf("")
                }
                var userEmail = remember {
                    mutableStateOf("")
                }


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
//            verticalArrangement = Arrangement.spacedBy(32.dp)
                ) {

                    val mContext = LocalContext.current
                    Button(onClick = {

                    }
                    ) {
                        getImageView(androidx.core.R.drawable.notify_panel_notification_icon_bg)
                    }


                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(vertical = 36.dp)
                    ) {
                        fieldView(text = userName.value.toString(), label = "Name", hint = "Enter Your Name")

                        fieldView(text = userEmail.value.toString(), label = "Email", hint = "Enter Your Email")
                    }

                    Button(onClick = {
                        Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG).show()
                    }) {
                        Text(text = "Save")
                    }

                }
            }
        }
    }
}





@Composable
fun GreetingView(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Button(onClick = {

        },modifier = Modifier
            .background(Color.White)
        ) {
            getImageView(androidx.core.R.drawable.notify_panel_notification_icon_bg)
        }



        fieldView(text = "", label = "Name", hint = "Enter Your Name")

        fieldView(text = "", label = "Email", hint = "Enter Your Email")

        Button(onClick = {

        }) {
            Text(text = "Save")
        }

    }
}



@Composable
fun fieldView(text:String,label:String,hint:String){
    TextField(value = text, onValueChange = {

    },label = { Text(text = label)},
        placeholder = { Text(text = hint)}
    )
}


@Composable
fun getImageView(imageID:Int) {
    Image(painter = painterResource(id = imageID),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(128.dp)
            .clip(CircleShape) // clip to the circle shape
            .border(5.dp, Color.Gray, CircleShape)//optional
    )
}


@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        var userName = remember {
            mutableStateOf("")
        }
        var userEmail = remember {
            mutableStateOf("")
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
//            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            val mContext = LocalContext.current
            Button(onClick = {

            }
            ) {
                getImageView(androidx.core.R.drawable.notify_panel_notification_icon_bg)
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(vertical = 36.dp)
            ) {
                fieldView(text = userName.value.toString(), label = "Name", hint = "Enter Your Name")

                fieldView(text = userEmail.value.toString(), label = "Email", hint = "Enter Your Email")
            }

            Button(onClick = {
                Toast.makeText(mContext, "Saved", Toast.LENGTH_LONG).show()
            }) {
                Text(text = "Save")
            }

        }
    }
}
