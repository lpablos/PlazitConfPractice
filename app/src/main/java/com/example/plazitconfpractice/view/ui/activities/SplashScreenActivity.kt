package com.example.plazitconfpractice.view.ui.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.plazitconfpractice.R
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //
        val intent = Intent(this, MainActivity::class.java)
        // 1.-Cargamos la animacion que diseñamos
        val animation = AnimationUtils.loadAnimation(this, R.anim.animacion)
        // 2.-Asignacion en vista del elemento a animar
        ivLogoPlazitConf.startAnimation(animation)
        // 3.- Asignar lo que pasara despues de la animación

        animation.setAnimationListener(object : Animation.AnimationListener{
            // Funciones de lo que pase en la animacion

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                // 4.- Finalizando la actividad enviarnos a Main que deseamos
                startActivity(intent)
                // 5.- Terminar el ciclo de vida de este activitie
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })

    }
}


