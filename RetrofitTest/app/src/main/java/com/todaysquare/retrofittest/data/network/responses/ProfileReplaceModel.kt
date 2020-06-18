package com.todaysquare.retrofittest.data.network.responses

import android.graphics.Bitmap

import com.google.gson.annotations.Expose
import com.todaysquare.retrofittest.utils.ToConstant

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

import java.io.*


class ProfileReplaceModel {
    // Request Model
    class RQ(path: String?, bitmap: Bitmap, model: ProfileInfoModel) {
        lateinit var mpFile: MultipartBody.Part
        val rqMap: HashMap<String?, RequestBody?> get() = rqMap

        init {
            if (path != null) {
                val file = File(path)

                try {
                    val os: OutputStream = BufferedOutputStream(FileOutputStream(file))

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                    os.close()

                    val rqFile: RequestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)

                    mpFile = MultipartBody.Part.createFormData(
                        ToConstant.Param.PROFILE_IMAGE, file.name, rqFile)

                } catch (e: FileNotFoundException) {
                    e.printStackTrace()

                } catch (e: IOException) {
                    e.printStackTrace()

                }
            }

            val rqGender =
                RequestBody.create(MediaType.parse("text/plain"), model.gender)
            val rqNickName =
                RequestBody.create(MediaType.parse("text/plain"), model.nickName)
            val rqWeight =
                RequestBody.create(MediaType.parse("text/plain"), model.weight.toString())
            val rqHeight =
                RequestBody.create(MediaType.parse("text/plain"), model.height.toString())

            rqMap[ToConstant.Param.GENDER] = rqGender
            rqMap[ToConstant.Param.NICK_NAME] = rqNickName
            rqMap[ToConstant.Param.WEIGHT] = rqWeight
            rqMap[ToConstant.Param.HEIGHT] = rqHeight

        }
    }

    class RS : BaseResponseModel() {
        @Expose
        var profile: CommonProfileModel? = null

        class CommonProfileModel {
            @Expose
            private val name: String? = null

            @Expose
            private val point = 0

        }
    }

    val responseType: Any get() = ProfileReplaceModel::class.java

}