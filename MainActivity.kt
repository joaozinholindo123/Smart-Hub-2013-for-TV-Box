package com.smarthub.launcher

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PLUTO_PACKAGE_NAME = "tv.pluto.android"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOpenPluto = findViewById<Button>(R.id.btnOpenPluto)
        val btnAppPluto = findViewById<LinearLayout>(R.id.btnAppPluto)
        val btnAppAll = findViewById<LinearLayout>(R.id.btnAppAll)

        // Ação para abrir a Pluto TV no destaque principal
        btnOpenPluto.setOnClickListener {
            openPlutoTV()
        }

        // Ação ao clicar no ícone do app Pluto TV no rodapé
        btnAppPluto.setOnClickListener {
            openPlutoTV()
        }

        // Ação para abrir a lista completa de apps do sistema
        btnAppAll.setOnClickListener {
            openAllApps()
        }
    }

    private fun openPlutoTV() {
        val launchIntent = packageManager.getLaunchIntentForPackage(PLUTO_PACKAGE_NAME)
        if (launchIntent != null) {
            // App instalado: Abre o aplicativo da Pluto TV
            startActivity(launchIntent)
        } else {
            // App não instalado: Tenta abrir via navegação Web ou Play Store
            try {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://pluto.tv/pt/live-tv"))
                startActivity(webIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "Pluto TV não instalada", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openAllApps() {
        // Abre a lista padrão de apps do Android
        val intent = Intent(Intent.ACTION_SETTING_PREFERENCES)
        startActivity(intent)
    }

    // Bloqueia a tecla "Voltar" para o launcher não fechar sozinho na TV Box
    override fun onBackPressed() {
        // Não faz nada (mantém o usuário na Launcher)
    }
}
