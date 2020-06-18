package com.todaysquare.retrofittest.data.network.responses

import com.google.gson.annotations.Expose

class ActivityExerciseSearchModel {
    companion object {
        class RQ

        class RS : BaseResponseModel() {
            @Expose
            var exerciseType: String? = null

            @Expose
            var exerciseName: String? = null

        }
    }
}