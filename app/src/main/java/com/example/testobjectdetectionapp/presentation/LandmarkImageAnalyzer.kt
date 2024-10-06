package com.example.testobjectdetectionapp.presentation

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.testobjectdetectionapp.domain.Classification
import com.example.testobjectdetectionapp.domain.LandmarkClassifier

class LandmarkImageAnalyzer (
    private val classifier: LandmarkClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    // FOR THIS AI THE BEST PERFORMANCE IS 321 x 321, that is why we crop it
    // we EXTENDED the Bitmap class to have centerCrop option

    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0){
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }

}