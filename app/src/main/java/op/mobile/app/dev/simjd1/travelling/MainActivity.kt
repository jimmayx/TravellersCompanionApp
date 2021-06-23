package op.mobile.app.dev.simjd1.travelling


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var isDark: Boolean = false
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        sharedPref = getPreferences(Context.MODE_PRIVATE)!!
        isDark = sharedPref.getBoolean("dark_mode", true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_style, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.title == "Change Style" && isDark) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
            isDark = false
            sharedPref.edit().putBoolean("dark_mode", isDark).apply()
            return true
        } else if (item.title == "Change Style" && isDark) {
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
            isDark = true
            sharedPref.edit().putBoolean("dark_mode", isDark).apply()
            return true
        }
        return true
    }
}