package com.example.jetpackcomposepractice.albumFeature


sealed class AlbumDetailEvent {

    // 選択 click event
    data class OnSelectMode(val selectMode: Boolean) : AlbumDetailEvent()

    // 全ての画像を選択 Click event
    data class OnAllImageSelectMode(val selectMode: Boolean) : AlbumDetailEvent()

    // all image select event
    data class OnSelectAllImages(val selectAllImages: Boolean) : AlbumDetailEvent()

    // single image select event
    data class OnSelectSingleImage(val selectSingleImage: Boolean) : AlbumDetailEvent()

    // 画像をタップしてプレビュー画面へ遷移するイベント
    data class OnTapImage(val route: String) : AlbumDetailEvent()
}