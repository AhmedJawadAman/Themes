package com.example.themes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the saved theme before inflating the layout
        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        val theme = sharedPref.getString("AppTheme", "Theme.Themes")
        setTheme(resources.getIdentifier(theme, "style", packageName))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()

        // Handle window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize buttons for theme selection
        setupThemeButtons()
    }

    private fun setupThemeButtons() {
        // Red Theme Button
        findViewById<Button>(R.id.btnRedTheme).setOnClickListener {
            applyTheme("RedTheme")
        }

        // Black Theme Button
        findViewById<Button>(R.id.btnBlackTheme).setOnClickListener {
            applyTheme("BlackTheme")
        }

        // Blue Theme Button
        findViewById<Button>(R.id.btnBlueTheme).setOnClickListener {
            applyTheme("BlueTheme")
        }
    }

    private fun applyTheme(themeName: String) {
        // Save the selected theme in SharedPreferences
        val sharedPref = getSharedPreferences("AppPreferences", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("AppTheme", themeName)
            apply()
        }

        // Restart the activity to apply the theme
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun enableEdgeToEdge() {
        // Enable edge-to-edge display (optional if using Material3)
      /*  window.decorView.systemUiVisibility = (
                ViewCompat.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        ViewCompat.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )*/
    }
}
