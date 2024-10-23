package com.example.gridrisky

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridrisky.data.DataSource
import com.example.gridrisky.model.Topic
import com.example.gridrisky.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent { //digunakan untuk mendefinisikan UI utama menggunakan jetpack compose.
            CoursesTheme { //tema aplikasi yang membungkus ui.
                Surface(//komponen untuk layout dengan warna latar sesuai tema.
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),//memberikan padding agar ui tidak menabrak status bar.
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(//untuk menampilkan daftar topik dalam bentuk griid.
                        modifier = Modifier.padding(
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(// Komponen yang menampilkan item secara grid dan hanya memuat item yang terlihat di layar (lazy loading).
        columns = GridCells.Fixed(2),//membuat grid dengan 2 kolom
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(DataSource.topics) { topic ->
            TopicCard(topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {//menampilkan konten dengan tampilan kotak kotak.
        Row {
            Box {
                Image(//menampilkan gambar topik dengan ukuran tetap.
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(68.dp)//ukuran gambar 68dp.
                        .aspectRatio(1f),//menjaga rasio gambar agar tetap 1 banding 1.
                    contentScale = ContentScale.Crop //memontong gambar agar sesuai kotak.
                )
            }
            Column {
                Text(//menampilkan nama topik jumlah kursus
                    text = stringResource(id = topic.name), //Mengambil string resource sesuai dengan id.
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(//menampilkan ikon dari resource gambar.
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(//menampilkan nama topik dan jumlah kursus.
                        text = topic.availableCourses.toString(), //menampilkan jumlah kursus.
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    CoursesTheme {
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}
