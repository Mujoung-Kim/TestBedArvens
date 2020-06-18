package com.todaysquare.retrofittest.data.network.responses

import com.google.gson.annotations.Expose

class ActivityRecordInputModel {
    companion object {
        class RQ(@field:Expose var exerciseId: Int, @field:Expose var exerciseName: String)

        class RS : BaseResponseModel() {
            @Expose
            var calory = 0

        }
    }
}