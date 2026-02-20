package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // 1) Références vers les champs de saisie
    private lateinit var nom: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var adresse: EditText
    private lateinit var ville: EditText

    // 2) Référence vers le bouton
    private lateinit var btnEnvoyer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // 3) Cycle de vie : onCreate est appelé à la création de l'écran
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // 4) Lie l'UI XML à cette Activity
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 5) Récupérer les vues par leurs IDs (définis dans XML)
        nom     = findViewById(R.id.nom)
        email   = findViewById(R.id.email)
        phone   = findViewById(R.id.phone)
        adresse = findViewById(R.id.adresse)
        ville   = findViewById(R.id.ville)
        btnEnvoyer = findViewById(R.id.btnEnvoyer)

        // 6) Écouter le clic sur "Envoyer"
        btnEnvoyer.setOnClickListener {
            // 6.1) Lire le texte des champs
            val sNom     = nom.text.toString().trim()
            val sEmail   = email.text.toString().trim()
            val sPhone   = phone.text.toString().trim()
            val sAdresse = adresse.text.toString().trim()
            val sVille   = ville.text.toString().trim()

            // 6.2) Validation ultra-simple (débutant) : champs obligatoires
            if (sNom.isEmpty() || sEmail.isEmpty()) {
                Toast.makeText(this, "Nom et Email sont obligatoires.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // stoppe l'envoi
            }

            // 6.3) Construire un Intent explicite vers l'écran 2
            val i = Intent(this@MainActivity, Screen2Activity::class.java)

            // 6.4) Passer les données avec des « extras » (clé/valeur)
            i.putExtra("nom", sNom)
            i.putExtra("email", sEmail)
            i.putExtra("phone", sPhone)
            i.putExtra("adresse", sAdresse)
            i.putExtra("ville", sVille)

            // 6.5) Démarrer l'activité de récapitulatif
            startActivity(i)
        }
    }
}
