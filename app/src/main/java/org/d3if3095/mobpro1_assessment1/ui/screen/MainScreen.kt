package org.d3if3095.mobpro1_assessment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3095.mobpro1_assessment1.R
import org.d3if3095.mobpro1_assessment1.navigation.Screen
import org.d3if3095.mobpro1_assessment1.ui.theme.Mobpro1_assessment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}


@Composable
fun ScreenContent(modifier: Modifier) {
    var merek by remember { mutableStateOf("") }
    var tipe by remember { mutableStateOf("") }
    var Rekomendasi1 by remember { mutableStateOf(false) }
    var Rekomendasi2 by remember { mutableStateOf(false) }
    var Rekomendasi3 by remember { mutableStateOf(false) }
    var Rekomendasi4 by remember { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(id = R.string.mobil_manual),
        stringResource(id = R.string.mobil_matic)
    )

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.app_intro),
            modifier = Modifier.fillMaxWidth()
        )

        // OutlinedTextField for the merek
        OutlinedTextField(
            value = merek,
            onValueChange = { value ->
                merek = value
            },
            label = { Text(text = stringResource(R.string.merek_mobil))},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(text = stringResource(id = R.string.pilih_tipe), style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp))

        // Radio buttons for tipe mobil
        Row(
            modifier = Modifier.padding(top = 6.dp).border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
            verticalAlignment =  Alignment.CenterVertically
        ) {
            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = tipe == text,
                            onClick = {
                                tipe = text
                            },
                            role = Role.RadioButton
                        ).weight(1f)
                        .padding(16.dp)
                ) {
                    RadioButton(selected = tipe == text, onClick = null)
                    Text(text = text)
                }
            }
        }

        // Button to show recommendations
        Button(
            onClick = {

                Rekomendasi1 = false
                Rekomendasi2 = false
                Rekomendasi3 = false
                Rekomendasi4 = false

                if (merek.lowercase() == "toyota" && tipe.lowercase() == "matic") {
                    Rekomendasi1 = true
                }
                if (merek.lowercase() == "toyota" && tipe.lowercase() == "manual") {
                    Rekomendasi2 = true
                }
                if (merek.lowercase() == "daihatsu" && tipe.lowercase() == "matic") {
                    Rekomendasi3 = true
                }
                if (merek.lowercase() == "daihatsu" && tipe.lowercase() == "manual") {
                    Rekomendasi4 = true
                }
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = stringResource(R.string.lihat_rekomendasi))
        }

        // Recommendations section
        if (Rekomendasi1) {
            ToyotaMatic()
        }
        if (Rekomendasi2){
            ToyotaManual()
        }
        if (Rekomendasi3) {
            DaihatsuMatic()
        }
        if (Rekomendasi4){
            DaihatsuManual()
        }

    }
}

@Composable
fun ToyotaMatic() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.toyota_matic),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.toyota),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_toyota_matic),
            modifier = Modifier.padding(top = 40.dp))
    }
}

@Composable
fun ToyotaManual() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.toyota_manual),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.toyota),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_toyota_manual),
            modifier = Modifier.padding(top = 40.dp))
    }
}

@Composable
fun DaihatsuMatic() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.daihatsu_matic),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.daihatsu),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_daihatsu_matic),
            modifier = Modifier.padding(top = 40.dp))
    }
}

@Composable
fun DaihatsuManual() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp)
    ) {
        Text(text = stringResource(id = R.string.daihatsu_manual),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.daihatsu),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(20.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = stringResource(id = R.string.isi_daihatsu_manual),
            modifier = Modifier.padding(top = 40.dp))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Mobpro1_assessment1Theme {
        MainScreen(rememberNavController())
    }
}

