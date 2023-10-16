package com.thierryboiago.walpaperapp.ui.fragment.downloader

interface DownloaderInterface {
    fun downloadFile(url: String, description: String): Long
}