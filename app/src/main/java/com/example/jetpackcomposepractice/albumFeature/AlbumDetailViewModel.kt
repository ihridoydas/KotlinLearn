package com.example.jetpackcomposepractice.albumFeature

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposepractice.albumFeature.data.entities.Images
import com.example.jetpackcomposepractice.albumFeature.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AlbumDetailViewModel  : ViewModel() {


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent: Flow<UiEvent> = _uiEvent.receiveAsFlow()

    // 追加撮影アラートダイアログ表示フラグ（その他）
    private val _showAddPictureOtherAlertDialog = mutableStateOf(false)
    val showAddPictureOtherAlertDialog: MutableState<Boolean> = _showAddPictureOtherAlertDialog

    // 取り込み完了アラートダイアログ表示フラグ
    private val _showImportCompleteAlertDialog = mutableStateOf(false)
    val showImportCompleteAlertDialog: MutableState<Boolean> = _showImportCompleteAlertDialog

    var otherAlbum: List<Images?>

    fun onEvent(event: AlbumDetailEvent) {

        when (event) {
            is AlbumDetailEvent.OnSelectMode -> {
                sendUiEvent(UiEvent.ScreenMode(event.selectMode))
            }
            is AlbumDetailEvent.OnSelectAllImages -> {
                sendUiEvent(UiEvent.SelectAllImages(event.selectAllImages))
            }
            is AlbumDetailEvent.OnAllImageSelectMode -> {
                sendUiEvent(UiEvent.AllImageSelectMode(event.selectMode))
            }
            is AlbumDetailEvent.OnSelectSingleImage -> {}
            is AlbumDetailEvent.OnTapImage -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }



    // ダミーデータ作成
    val numberPlateImageDatas: List<Images> = listOf(Images(1, 1, "外観５方向",1, null,"image", null, false, false, null, null),)
    val appearanceImagesDatas: List<Images> = listOf(
        Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
        Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
        Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
        // Images(1, 1, "外観５方向",1, null,"image", null, false, false, null, null),
        // Images(1, 1, "外観５方向",1, null,"image", null, false, false, null, null),
        // Images(1, 1, "外観５方向",1, null,"image", null, false, false, null, null),
    )
    val cautionPlateImageDatas: Images? = Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null)
    val odometerImageDatas: Images? = null
    val vehicleCertificateImageDatas: Images? = null

    init {
        otherAlbum = listOf(
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
            Images(1, 1, "外観５方向", 1, null, "image", null, false, false, null, null),
        )
    }

    /**
     * 追加撮影アラートダイアログを表示する
     */
    fun onAddPictureOtherDialogClicked() {
        _showAddPictureOtherAlertDialog.value = true
    }

    /**
     * 追加撮影アラートダイアログの撮影ボタン行動
     */
    fun onAddPictureOtherDialogConfirmShoot() {
        _showAddPictureOtherAlertDialog.value = false
        // Continue with executing the confirmed action
    }

    /**
     * 追加撮影アラートダイアログの取り込みボタン行動
     */
    fun onAddPictureOtherDialogConfirmImport() {
        _showAddPictureOtherAlertDialog.value = false
        // Continue with executing the confirmed action
    }

    /**
     * 追加撮影アラートダイアログのキャンセルボタン行動
     */
    fun onAddPictureOtherDialogDismiss() {
        _showAddPictureOtherAlertDialog.value = false
    }

    /**
     * 取り込み完了アラートダイアログを表示する
     */
    fun onImportCompleteDialogClicked() {
        _showImportCompleteAlertDialog.value = true
    }

    /**
     * 取り込み完了アラートダイアログを表示する
     */
    fun onImportCompleteDialogDismiss() {
        _showImportCompleteAlertDialog.value = false
    }
}
