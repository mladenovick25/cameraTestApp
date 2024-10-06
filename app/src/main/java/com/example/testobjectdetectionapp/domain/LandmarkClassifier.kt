package com.example.testobjectdetectionapp.domain

import android.graphics.Bitmap

interface LandmarkClassifier {

    fun classify(bitmap: Bitmap, rotation: Int): List<Classification>
}