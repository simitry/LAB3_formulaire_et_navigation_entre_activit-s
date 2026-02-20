package com.example.lab3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Screen2Activity : AppCompatActivity() {

    private lateinit var recap: TextView
    private lateinit var btnRetour: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen2) // relie au XML du récap

        recap = findViewById(R.id.recap)
        btnRetour = findViewById(R.id.btnRetour)

        // 1) Récupérer l'Intent qui a lancé cet écran
        val intent = intent

        // 2) Extraire les données envoyées depuis MainActivity
        val nom     = intent.getStringExtra("nom")
        val email   = intent.getStringExtra("email")
        val phone   = intent.getStringExtra("phone")
        val adresse = intent.getStringExtra("adresse")
        val ville   = intent.getStringExtra("ville")

        // 3) Construire un texte formaté (affichage multi-lignes)
        val resume = "Nom : ${safe(nom)}\n" +
                     "Email : ${safe(email)}\n" +
                     "Phone : ${safe(phone)}\n" +
                     "Adresse : ${safe(adresse)}\n" +
                     "Ville : ${safe(ville)}"

        // 4) Afficher le récapitulatif
        recap.text = resume

        // 5) Bouton Retour : fermer cet écran et revenir au précédent
        btnRetour.setOnClickListener { finish() }
    }

    // Petite aide : si une valeur est null/vides, retourner "—"
    private fun safe(s: String?): String {
        return if (s.isNullOrEmpty()) "—" else s.trim()
    }
}
