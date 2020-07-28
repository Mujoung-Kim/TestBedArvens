package com.todaysquare.asynctaskdownload.data.models

import android.net.Uri
import android.provider.MediaStore

class Music (id: String, title: String?, artist: String?, albumId: String?, duration: Long?) {
    var id: String = ""
    var title: String?
    var artist: String?
    var albumId: String?
    var duration: Long?

    init {
        this.id = id
        this.title = title
        this.artist = artist
        this.albumId = albumId
        this.duration = duration

    }

    fun getMusicUri(): Uri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    fun getAlbumUri(): Uri = Uri.parse("context://media/external/audio/albumart/$albumId")

}