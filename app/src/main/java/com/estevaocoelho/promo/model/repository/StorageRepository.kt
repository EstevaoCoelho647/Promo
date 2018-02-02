package com.estevaocoelho.promo.model.repository

import android.net.Uri
import android.util.Log
import com.estevaocoelho.promo.util.CompressorUtil
import com.estevaocoelho.promo.util.DATABASE_ITEM_PATH
import com.google.firebase.storage.FirebaseStorage
import java.io.File

/**
 * Created by estevaocoelho on 07/12/17.
 */
class StorageRepository {
    val instance: FirebaseStorage

    constructor() {
        instance = FirebaseStorage.getInstance()
    }

    fun saveImageOnStorage(url: String, key: String, callback: OnImageSaveListener) {
        CompressorUtil.compress(url, { it: File? ->
            instance.reference.child(DATABASE_ITEM_PATH).child(key).putFile(Uri.fromFile(it)).addOnCompleteListener({ task ->
                val downloadUrl: Uri = task.result.downloadUrl!!
                Log.d("saveImageOnStorage", "Complete")
                callback.onSuccess(downloadUrl)
            }).addOnFailureListener({ e ->
                Log.d("Failure", e.message)
                callback.onError()
            }).addOnProgressListener({ progress ->
                Log.d("Progress ", progress.bytesTransferred.toString() + "/" + progress.totalByteCount.toString())
            })
        })
    }

    interface OnImageSaveListener {
        fun onSuccess(url: Uri)
        fun onError()
    }
}