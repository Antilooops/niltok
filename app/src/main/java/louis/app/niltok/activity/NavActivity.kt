package louis.app.niltok.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.runBlocking
import louis.app.niltok.R
import louis.app.niltok.adapter.PRODUCT_DATA
import louis.app.niltok.model.Product
import louis.app.niltok.model.Product.Utils.getProductById

private const val TAG = "Nav"

open class NavActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    lateinit var scanQrButton: Button

    private val barcodeLauncher = registerForActivityResult(ScanContract()) { result ->
        if (result.contents != null) {
            Log.i(TAG, "onCreate: $result")
            val product: Product
            runBlocking {
                product = getProductById(id = result.contents.toInt())!!
            }
            val intent = Intent(this, louis.app.niltok.activity.ProductDetailActivity::class.java).apply {
                putExtra(PRODUCT_DATA, product)
            }
            startActivity(intent)
        } else {
            Log.i(TAG, "NO PRODUCT")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        // Désactive temporairement le callback pour permettre le retour normal
                        isEnabled = false
                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
        )


    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean{
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            R.id.nav_shop -> {
                startActivity(Intent(this, ShopActivity::class.java))
            }
            R.id.nav_qr_code -> {
                val options = ScanOptions()
                options.setPrompt("Scanner un QR code")
                options.setBeepEnabled(true)
                options.setOrientationLocked(true)
                options.setCaptureActivity(CaptureActivity::class.java)
                barcodeLauncher.launch(options)
            }
            R.id.nav_cart -> {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }
}