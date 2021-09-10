package it.santarpia.cosaevitareingravidanza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBmanager dbmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbmanager = new DBmanager(getApplicationContext());

        //Updating food's list
        if (dbmanager.getVersion() < 1) {
            Log.d("kiwi", "Updating foods' database from version: " + dbmanager.getVersion());

            dbmanager.updateVersion();

            ArrayList<Food> foods = new ArrayList<>();

            /*Pesce*/
            foods.add(new Food("Pesce spada", "Il pesce spada è un pesce osseo marino, unica specie della famiglia Xiphiidae. Si tratta di una specie di grande importanza per la pesca commerciale.",
                    Food.NEG, Food.NEG, Food.NOSAFE, "Pesce di mare"));
            foods.add(new Food("Sgombro", "Lo sgombro, chiamato anche maccarello, scombro o lacerto a seconda delle zone d'Italia, è un pesce di mare appartenente alla famiglia Scombridae. È un tipico pesce azzurro.",
                    Food.NEG, Food.NEG, Food.NOSAFE, "Pesce di mare"));
            foods.add(new Food("Tonno", "Thunnus è un genere della famiglia Scombridae che raggruppa otto specie di grandi pesci pelagici predatori, conosciuti comunemente come tonni.",
                    Food.NEG, Food.NEG, Food.NOSAFE, "Pesce di mare"));

            /*Bevande*/
            foods.add(new Food("Alcol", "",
                    Food.NEG, Food.NEG, Food.NOSAFE, "Bevande"));
            foods.add(new Food("Caffè", "",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Bevande"));

            /*Carne*/
            foods.add(new Food("Carne cruda", "",
                    Food.POS, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Carne affumicata", "",
                    Food.POS, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Carne marinata", "",
                    Food.POS, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Carne in scatola", "",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Prosciutto cotto", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Carne"));
            foods.add(new Food("Mortadella", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Carne"));
            foods.add(new Food("Wustel", "Richiedono un ulteriore cottura per essere mangiati senza rischi",
                    Food.NEG, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Prosciutto crudo", "Da consumare con moderazione perché ricca di sodio",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Bresaola", "Da consumare con moderazione perché ricca di sodio",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Speck", "Da consumare con moderazione perché ricca di sodio",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Fegato", "Livelli elevati di vitamina A che possono aumentare i rischi di malformazioni del feto",
                    Food.NEG, Food.NEG, Food.NOSAFE, "Carne"));
            foods.add(new Food("Interiora animali", "Livelli elevati di vitamina A che possono aumentare i rischi di malformazioni del feto",
                    Food.NEG, Food.NEG, Food.NOSAFE, "Carne"));

            /*Formaggi*/
            foods.add(new Food("Feta", "La feta è un formaggio tradizionale greco, a pasta semidura ma friabile, bianchissimo e piuttosto salato",
                    Food.NEG, Food.POS, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Brie", "",
                    Food.NEG, Food.POS, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Gorgonzola", "",
                    Food.NEG, Food.POS, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Roquefort", "",
                    Food.NEG, Food.POS, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Stilton", "",
                    Food.NEG, Food.POS, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Camembert", "",
                    Food.NEG, Food.POS, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Mozzarella", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.POS, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Ricotta", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.POS, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Mascarpone", "E' un latticino piuttosto grasso che dev'essere consumato con parsimonia\nIn caso di mascarpone artigianale, il consiglio è di evitarlo",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Formaggio spalmabile", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Parmigiano", "Per assicurarsi la giusta quantità di calcio giornaliera basta inserire nella propria alimentazione un pezzetto di parmigiano ogni giorno",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Fontina", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Cheddar", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Provolone dolce", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Pecorino", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Asiago", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Formaggio svizzero", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Robiola", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Fiocchi di latte", "",
                    Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Provola", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Provola affumicata", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Scamorza", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Scamorza affumicata", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));

            /*Frutta*/
            foods.add(new Food("Frutta fresca", "Dev'essere consumata sbucciata e ben lavata",
                    Food.POS, Food.NEG, Food.ALMOSTSAFE, "Frutta"));

            for(Food f : foods)
                dbmanager.insertFood(f);
        }

        //Starting search activity
        new CountDownTimer(1000, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                Log.d("kiwi", getClass().getSimpleName() + ": launched main menu");

                finish();

                Log.d("kiwi", getClass().getSimpleName() + ": ended activity");
            }
        }.start();
    }
}