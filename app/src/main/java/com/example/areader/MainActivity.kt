package com.example.areader

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.areader.ui.theme.AReaderTheme
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AReaderTheme {
                val db = FirebaseFirestore.getInstance()
                val user: MutableMap<String, Any> = HashMap()
                user ["FirstName"] = "Joe"
                user ["lastName"] = "James"

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d("FB", "onCreate: ${it.id}")
                        }.addOnFailureListener {
                            Log.d("FB", "onCreate: $it")
                        }
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AReaderTheme {
        Greeting("Android")
    }
}