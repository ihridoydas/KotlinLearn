package com.example.jetpackcomposepractice.albumFeature

import android.annotation.SuppressLint
import android.drm.DrmStore.Action.PREVIEW
import android.media.Image
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils.isEmpty
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposepractice.albumFeature.util.COLOR_BLUE
import com.example.jetpackcomposepractice.albumFeature.util.ColorHex
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.toColorInt
import com.example.jetpackcomposepractice.MyViewModel
import com.example.jetpackcomposepractice.R
import com.example.jetpackcomposepractice.albumFeature.data.entities.Images
import com.example.jetpackcomposepractice.albumFeature.util.COLOR_LIGHT_GRAY
import com.example.jetpackcomposepractice.albumFeature.util.UiEvent
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlin.math.abs

enum class AlbumType {
    NUMBER_PLATE,
    EXTERIOR_5_SIDE,
    VEHICLE_INFO,
    OTHERS
}


enum class InputSelectorAlbumDetail {
    CAMERA_ADD_APPEARANCE,
    CAMERA_ADD_VEHICLE_INFO,
    CAMERA_ADD_OTHERS
}

@SuppressLint("RestrictedApi", "UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    navController: NavController? = null,
    onSelectorChange: (InputSelectorAlbumDetail) -> Unit,
    viewModel: AlbumDetailViewModel = AlbumDetailViewModel()
) {

    var selectedMode by rememberSaveable { mutableStateOf(false) }
    var allImageSelectMode by rememberSaveable { mutableStateOf(false) }
    var allImageSelected by rememberSaveable { mutableStateOf(false) }
    var allImageDeSelected by rememberSaveable { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ScreenMode -> {
                    selectedMode = uiEvent.isSelect
                }
                is UiEvent.AllImageSelectMode -> {
                    allImageSelectMode = uiEvent.isSelectAll
                    if (allImageSelectMode) {
                        allImageSelected = allImageSelectMode
                    } else {
                        allImageDeSelected = true
                    }
                }
                is UiEvent.SelectAllImages -> {
                    allImageSelected = uiEvent.selectAllImages
                }
                is UiEvent.Navigate -> { navController?.navigate(PREVIEW) }
                else -> {}
            }
        }
    }



    //Start UI
    Scaffold(
        //TopBar
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.testTag("Album Details Screen"),
                title = {
                    Text(
                        text = "Number Plate is very long ,set Text overflow Ellipsis",
                        fontSize = 16.sp,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp)
                    )
                },
                navigationIcon = {
                    OutlinedButton(
                        onClick = {

                        },
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = ColorHex.hex2Rgb(COLOR_BLUE),
                            disabledContentColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .defaultMinSize(
                                minHeight = 1.dp,
                                minWidth = 1.dp
                            ),
                        contentPadding = PaddingValues(0.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = null
                        )
                        Text(text = "一覧")

                    }
                },
                actions = {
                    OutlinedButton(
                        onClick = {
                            if (selectedMode) {
                                allImageSelected = false
                                allImageDeSelected = true
                                allImageSelectMode = false
                            }
                            selectedMode = !selectedMode

                        },
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = ColorHex.hex2Rgb(COLOR_BLUE),
                            disabledContentColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .defaultMinSize(minHeight = 1.dp, minWidth = 1.dp),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        if (!selectedMode) {
                            Text(text = "選択")
                        } else {
                            Text(
                                text = "キャンセル",
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                }
            )

        },
        bottomBar = {
            //Bottom Bar
            if (selectedMode) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color(COLOR_LIGHT_GRAY.toColorInt())),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    OutlinedButton(
                        onClick = {
                            allImageSelectMode = !allImageSelectMode

                            if (allImageSelectMode) {
                                allImageSelected = true
                            } else {
                                allImageSelected = true
                            }
                        },
                        modifier = Modifier
                            .padding(start = 10.dp),
                        border = BorderStroke(0.dp, Color.Transparent)

                    ) {
                        if (allImageSelectMode) {
                            Text(text = "すべて解除", color = Color(COLOR_BLUE.toColorInt()))
                        } else {
                            Text(text = "すべて選択", color = Color(COLOR_BLUE.toColorInt()))
                        }

                    }
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(start = 10.dp),
                        border = BorderStroke(0.dp, Color.Transparent)
                    ) {
                        Text(
                            text = "ワークスペースに送信",
                            color = Color(COLOR_BLUE.toColorInt())
                        )

                    }

                }

            }


        }
    ) {

        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        //Image URI listener (show dialog when value change)
        imageUri?.let {
            viewModel.onImportCompleteDialogClicked()
            //Add viewmodel onImport complete dialog
        }
        // Launcher (Photo Picker)
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            imageUri = uri
        }
        // 追加撮影アラートダイアログ
        AddPictureOtherAlertDialog(
            launcher = launcher,
            viewModel = viewModel,
            onSelectorChange = onSelectorChange
        )
        // 取り込み完了ダイアログ
        ImportCompleteDialog(viewModel = viewModel)

        //Start Implementation

        LazyColumn(
            modifier = Modifier
                .testTag("ALBUM_DETAIL_LAZY_COLUMN")
                .fillMaxSize()
        ) {
            // 未送信通知欄
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .background(Color(COLOR_LIGHT_GRAY.toColorInt()))
                        .padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.badge2),
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "未送信の画像があります",
                        fontSize = 13.sp,
                        color = Color(COLOR_BLUE.toColorInt())
                    )
                    /*
                    Button(onClick = {
                        print("import")
                        navController?.navigate(TEST_IMPORT)
                    }) {
                        Text(text = "TEST IMPORT")
                    }
                    */
                }
            }

            // ナンバープレート画像セクション
            item {
                ImageLibrary(
                    navController = navController,
                    selectedMode = selectedMode,
                    allImageSelectMode = allImageSelectMode,
                    selectAllImage = allImageSelected,
                    deSelectAllImage = allImageDeSelected,
                    albumType = AlbumType.NUMBER_PLATE,
                    images = viewModel.numberPlateImageDatas,
                    startPadding = 0.dp,
                    endPadding = 0.dp,
                    onClickSelect = {
                        allImageSelected = it
                        allImageDeSelected = it
                    },
                    viewModel = viewModel,
                    onSelectorChange = onSelectorChange
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 外観5方向欄
            item {
                ImageLibrary(
                    albumName = "外観5方向",
                    navController = navController,
                    selectedMode = selectedMode,
                    allImageSelectMode = allImageSelectMode,
                    selectAllImage = allImageSelected,
                    albumType = AlbumType.EXTERIOR_5_SIDE,
                    images = viewModel.appearanceImagesDatas,
                    deSelectAllImage = allImageDeSelected,
                    onClickSelect = {
                        allImageSelected = it
                        allImageDeSelected = it
                    },
                    viewModel = viewModel,
                    onSelectorChange = onSelectorChange
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 車両情報欄
            item {
                ImageLibrary(
                    albumName = "車両情報",
                    navController = navController,
                    selectedMode = selectedMode,
                    allImageSelectMode = allImageSelectMode,
                    selectAllImage = allImageSelected,
                    albumType = AlbumType.VEHICLE_INFO,
                    images = listOf(
                        viewModel.cautionPlateImageDatas,
                        viewModel.odometerImageDatas,
                        viewModel.vehicleCertificateImageDatas
                    ),
                    deSelectAllImage = allImageDeSelected,
                    onClickSelect = {
                        allImageSelected = it
                        allImageDeSelected = it
                    },
                    viewModel = viewModel,
                    onSelectorChange = onSelectorChange
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 20.dp),
                ) {
                    Text(text = "コーションプレート", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "オドメーター", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(60.dp))
                    Text(text = "車検証", fontSize = 12.sp)
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            // その他画像セクション
            item {
                ImageLibrary(
                    albumName = "その他",
                    navController = navController,
                    selectedMode = selectedMode,
                    allImageSelectMode = allImageSelectMode,
                    selectAllImage = allImageSelected,
                    albumType = AlbumType.OTHERS,
                    images = viewModel.otherAlbum,
                    deSelectAllImage = allImageDeSelected,
                    onClickSelect = {
                        allImageSelected = it
                        allImageDeSelected = it
                    },
                    viewModel = viewModel,
                    onSelectorChange = onSelectorChange
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "残り41枚撮影可能")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }


    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageLibrary(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    albumName: String = "",
    images: List<Images?>,
    selectedMode: Boolean = false,
    allImageSelectMode: Boolean,
    selectAllImage: Boolean,
    deSelectAllImage: Boolean,
    albumType: AlbumType,
    startPadding: Dp = 10.dp,
    endPadding: Dp = 10.dp,
    onClickSelect: (Boolean) -> Unit,
    viewModel: AlbumDetailViewModel,
    onSelectorChange: (InputSelectorAlbumDetail) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val imageWidth = (screenWidth - 40) / 3


    // アルバム全体構築
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = startPadding, end = endPadding)
    ) {
        // アルバムのタイトル（ヘッダ）
        if (albumName != "") {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    text = albumName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // 次の行目にオーバーフロー用なローに画像を配置
        FlowRow(
            modifier = modifier
                .fillMaxSize(),
            mainAxisSpacing = 10.dp,
            crossAxisSpacing = 10.dp,
            mainAxisSize = SizeMode.Wrap,
            mainAxisAlignment = FlowMainAxisAlignment.Start
        ) {

            // 画像のセルをどのくらい作りたいかを判断する
            val repeat: Int = when (albumType) {

                AlbumType.NUMBER_PLATE -> { 1 }

                AlbumType.EXTERIOR_5_SIDE -> {
                    if (images.isEmpty()) 1 else if (images.size < 5) (images.size + 1) else 5
                }

                AlbumType.VEHICLE_INFO -> {
                    images.size
                }

                AlbumType.OTHERS -> {
                    if (images.size == 90) images.size else images.size + 1
                }
            }

            // 再生可能セールを作成して画像を配置する
            repeat(repeat) { cell ->

                var image: Images? = null
                var noImageCell: Int? = null
                var isChecked by rememberSaveable { mutableStateOf(false) }

                // アルバムのタイプを判断して画像を修得する
                when (albumType) {

                    // ナンバープレート画像の場合
                    AlbumType.NUMBER_PLATE -> {
                        image = images[cell]
                    }

                    // 　外観５方向の場合
                    AlbumType.EXTERIOR_5_SIDE -> {
                        if (images.isNotEmpty()) {
                            if (images.size >= 5) {
                                image = images[cell]
                            } else {
                                if (cell == images.size) {
                                    image = null
                                    noImageCell = cell
                                } else {
                                    image = images[cell]
                                }
                            }
                        } else {
                            image = null
                            noImageCell = cell
                        }
                    }

                    // 　車両情報の場合
                    AlbumType.VEHICLE_INFO -> {
                        image = images[cell]
                    }

                    // 　その他の場合
                    AlbumType.OTHERS -> {
                        if (images.isNotEmpty()) {
                            if (images.size >= 50) {
                                image = images[cell]
                            } else {
                                if (cell == images.size) {
                                    image = null
                                    noImageCell = cell
                                } else {
                                    image = images[cell]
                                }
                            }
                        } else {
                            image = null
                            noImageCell = cell
                        }
                    }
                }

                // 　修得した画像のURLから表示する用な画像を作成する
                val paintImage = if (image != null) {
                    R.drawable.car_img
                } else {
                    when (albumType) {
                        AlbumType.OTHERS -> {
                            if (selectedMode) R.drawable.ic_camera_and_galery_inactive
                            else R.drawable.ic_camera_and_galery
                        }
                        else -> {
                            if (selectedMode) R.drawable.ic_camera_capture_inactive
                            else R.drawable.ic_camera_capture
                        }
                    }
                }

                // 　画像の全体的なセル
                Box(
                    modifier = modifier
                        .size(
                            width = if (albumType == AlbumType.NUMBER_PLATE) screenWidth.dp else imageWidth.dp,
                            height = if (albumType == AlbumType.NUMBER_PLATE) 281.dp else 82.dp
                        )

                ) {

                    Card(
                        shape = if (albumType == AlbumType.NUMBER_PLATE) RoundedCornerShape(0.dp) else RoundedCornerShape(5.dp),
                        modifier = modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = paintImage),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds,
                            alpha = 1f
                        )
                    }

                    // 未送信や画像を選択するチェックボックスのiconを表示する部分
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .padding(start = 5.dp, top = 5.dp, end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        // まだ送信してない画像の時表示するicon
                        if (image?.isUploaded == false) {
                            Image(
                                painter = painterResource(id = R.drawable.badge2),
                                contentDescription = null,
                                modifier = modifier.size(20.dp)
                            )
                        }

                        // 選択モードの時チェックボックスを表示
                        if (selectedMode) {

                            // allSelectフラグが陽性の場合全てのチェックマークを陽性する。
                            isChecked = if (allImageSelectMode && selectAllImage) {
                                true
                            } else if (!allImageSelectMode && deSelectAllImage) {
                                false
                            } else {
                                isChecked
                            }


                            // 選択様子を判断するチェエックマーク

                            val painter = if (isChecked) {
                                painterResource(id = R.drawable.ic_checked)
                            } else {
                                painterResource(id = R.drawable.ic_check_blank)
                            }

                            when(albumType) {
                                AlbumType.VEHICLE_INFO -> {
                                    if (image != null) {
                                        androidx.compose.material3.Icon(
                                            painter = painter,
                                            contentDescription = null,
                                            tint = Color.Unspecified,
                                            modifier = modifier.size(20.dp)
                                        )
                                    }
                                }
                                else -> {
                                    if (cell != noImageCell) {
                                        androidx.compose.material3.Icon(
                                            painter = painter,
                                            contentDescription = null,
                                            tint = Color.Unspecified,
                                            modifier = modifier.size(20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                    val context = LocalContext.current
                    // 　画像セル自体の上に透明なボタンでタップアクションを行う
                    androidx.compose.material3.OutlinedButton(
                        onClick = {
                            if (selectedMode) {
                                onClickSelect(false)
                                isChecked = !isChecked
                            } else {
                                // viewModel?.onEvent(event = AlbumDetailEvent.OnTapImage(route = "image_edit_screen"))

                                if (image != null) {

                                    Toast.makeText(context,"Preview...", Toast.LENGTH_LONG).show()
                                   // navController?.navigate(PREVIEW)
                                } else {
                                    // Check cell type
                                    when (albumType) {
                                        // other: show alert
                                        AlbumType.OTHERS -> {
                                            viewModel.onAddPictureOtherDialogClicked()
                                        }
                                        // not other: show camera
                                        AlbumType.EXTERIOR_5_SIDE -> {
                                            onSelectorChange(InputSelectorAlbumDetail.CAMERA_ADD_APPEARANCE)
                                        }
                                        AlbumType.VEHICLE_INFO -> {
                                            // TODO: differentiate vehicle info (certificate, odometer, caution plate)
                                            onSelectorChange(InputSelectorAlbumDetail.CAMERA_ADD_VEHICLE_INFO)
                                        }
                                        else -> {
                                        }
                                    }
                                }
                            }
                        },
                        modifier = modifier
                            .fillMaxSize()
                            .testTag(
                                when (albumType) {
                                    // other: show alert
                                    AlbumType.OTHERS -> {
                                        if (image != null) {
                                            "ALBUM_DETAIL_CELL_BUTTON_OTHERS"
                                        } else {
                                            "ALBUM_DETAIL_CELL_BUTTON_OTHERS_EMPTY"
                                        }
                                    }
                                    // not other: show camera
                                    AlbumType.EXTERIOR_5_SIDE -> {
                                        if (image != null) {
                                            "ALBUM_DETAIL_CELL_BUTTON_APPEARANCE"
                                        } else {
                                            "ALBUM_DETAIL_CELL_BUTTON_APPEARANCE_EMPTY"
                                        }
                                    }
                                    AlbumType.VEHICLE_INFO -> {
                                        if (image != null) {
                                            "ALBUM_DETAIL_CELL_BUTTON_VEHICLE_INFO"
                                        } else {
                                            "ALBUM_DETAIL_CELL_BUTTON_VEHICLE_INFO_EMPTY"
                                        }
                                    }
                                    AlbumType.NUMBER_PLATE -> {
                                        "ALBUM_DETAIL_CELL_BUTTON_NUMBER_PLATE"
                                    }
                                }
                            ),
                        border = BorderStroke(width = 0.dp, color = Color.Transparent),
                        content = {},
                        enabled = if (selectedMode) {
                            when (albumType) {
                                AlbumType.VEHICLE_INFO -> image != null
                                else -> cell != noImageCell
                            }
                        } else true
                    )
                }
            }
        }
    }
}