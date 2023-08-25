package com.example.benchmark

import android.util.Log
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup(): Unit = benchmarkRule.measureRepeated(
        packageName = "com.example.getmoview",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun scrollCompilation() = scrollAndNavigate(CompilationMode.None())
    private fun scrollAndNavigate(compilationMode: CompilationMode) {
        benchmarkRule.measureRepeated(
            packageName = "com.example.getmoview",
            metrics = listOf(FrameTimingMetric()),
            compilationMode = compilationMode,
            iterations = 5,
            startupMode = null,
            setupBlock = {
                Log.d("BenchMark", "set up block iteration=$iteration ")
            }
        ) {

            pressHome()
            startActivityAndWait()
            Log.d("BenchMark", "measureBlock iteration=$iteration ")
            device.wait(Until.hasObject(By.res("movies")), 5000) // Waits until the lazy column has something
            val movieList = device.findObject(By.res("movies")) // find the lazy column using the test tag
            movieList.wait(Until.hasObject(By.res("moviesList")), 5000) // Waits until the movie_item has something

            //Wait for scroll to finish
            device.waitForIdle()

            // Set gesture margin to avoid triggering system gesture navigation
            movieList.setGestureMargin(device.displayWidth / 5)

            //Scroll down the list
            movieList.fling(Direction.DOWN)


            device.findObject(By.text("Barbie")).click()

            device.wait(Until.hasObject(By.text("User Score")), 50000)

            killProcess()
        }
    }
}

// Find views in our ui and perform actions on them.
// It is an extension function
//fun MacrobenchmarkScope.addElementAndScrollDown() {
//    val list =
//        device.findObject(By.res("movies")) // To find this, we add the movies tag to our lazy column
//
//    device.waitForIdle()
//
//    // Performing scroll gesture.
//    list.setGestureMargin(device.displayWidth / 5)  //Shrinks the gesture mode. This is to ensure during your scroll action we don't trigger some elements such as navigation
//    list.fling(Direction.DOWN) // Scrolling down
//    device.findObject(By.text("Spider-Man")).click()
//
//
//}