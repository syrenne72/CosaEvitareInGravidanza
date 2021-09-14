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

            /*Bevande*/
            foods.add(new Food("Alcol", "Non bere superalcolici durante la gravidanza e limitare il vino o la birra a un bicchiere a pasto",
                    Food.NEG, Food.NEG, Food.NEG, Food.NOSAFE, "Bevande"));
            foods.add(new Food("Caffè", "Non eccedere (un massimo di tre tazzine al massimo al giorno) o passare al decaffeinato",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Bevande"));

            /*Carne*/
            foods.add(new Food("Arrosto di tacchino", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));
            foods.add(new Food("Arrosto di pollo", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));

            foods.add(new Food("Bacon cotto", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));
            foods.add(new Food("Bresaola", "Da consumare con moderazione perché ricca di sodio",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));

            foods.add(new Food("Carne cruda", "",
                    Food.POS, Food.POS, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Carne affumicata", "",
                    Food.POS, Food.POS, Food.NEG, Food.NOSAFE, "Carne"));
            foods.add(new Food("Carne marinata", "",
                    Food.POS, Food.POS, Food.NEG, Food.NOSAFE, "Carne"));
            foods.add(new Food("Carne in scatola", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Coppa cruda", "",
                    Food.POS, Food.NEG, Food.NEG, Food.NOSAFE, "Carne"));
            foods.add(new Food("Cotechino", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));
            foods.add(new Food("Culatello", "",
                    Food.POS, Food.NEG, Food.NEG, Food.NOSAFE, "Carne"));

            foods.add(new Food("Fegato", "Livelli elevati di vitamina A che possono aumentare i rischi di malformazioni del feto",
                    Food.NEG, Food.NEG, Food.POS, Food.NOSAFE, "Carne"));

            foods.add(new Food("Interiora animali", "Livelli elevati di vitamina A che possono aumentare i rischi di malformazioni del feto",
                    Food.NEG, Food.NEG, Food.POS, Food.NOSAFE, "Carne"));

            foods.add(new Food("Mortadella", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));

            foods.add(new Food("Pancetta cruda", "Va evitata a causa del contenuto alto di sale",
                    Food.POS, Food.NEG, Food.NEG, Food.NOSAFE, "Carne"));
            foods.add(new Food("Pancetta cotta", "Non bisogna esagerare nel consumo di pancetta cotta",
                    Food.POS, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Prosciutto cotto", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));
            foods.add(new Food("Prosciutto crudo", "Da consumare con moderazione perché ricca di sodio",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Porchetta", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));

            foods.add(new Food("Salame", "Da evitare assolutamente poiché può provocare anche salmonellosi",
                    Food.POS, Food.POS, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Salsiccia", "Da evitare assolutamente poiché può provocare anche salmonellosi",
                    Food.POS, Food.POS, Food.POS, Food.NOSAFE, "Carne"));
            foods.add(new Food("Speck", "Da consumare con moderazione perché ricca di sodio",
                    Food.NEG, Food.NEG, Food.POS, Food.ALMOSTSAFE, "Carne"));
            foods.add(new Food("Selvaggina", "Va consumata solo ben cotta e con moderazione perché poco digeribile",
                    Food.POS, Food.POS, Food.POS, Food.NOSAFE, "Carne"));

            foods.add(new Food("Testa in cassetta", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));

            foods.add(new Food("Wustel", "Richiedono un ulteriore cottura per essere mangiati senza rischi",
                    Food.NEG, Food.POS, Food.POS, Food.ALMOSTSAFE, "Carne"));

            foods.add(new Food("Zampone", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Carne"));

            /*Cereali*/
            foods.add(new Food("Amaranto", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Cous cous", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Farro", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Germogli di cereali", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Cereali"));
            foods.add(new Food("Grano saraceno", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Miglio", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Orzo", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Panna vegetale", "Se acquistata al supermercato non presenta rischi",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));
            foods.add(new Food("Pane", "Da consumare preferibilmente integrale",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));
            foods.add(new Food("Pasta", "Da consumare preferibilmente integrale",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Quinoa", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Riso", "Da consumare preferibilmente integrale",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Cereali"));

            foods.add(new Food("Soia", "Al massimo due volte a settimana poiché contiene fitoestrogeni",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Cereali"));

            foods.add(new Food("Tofu", "Al massimo due volte a settimana poiché contiene fitoestrogeni",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Cereali"));

            /*Erbe aromatiche*/
            foods.add(new Food("Basilico", "Va consumato dopo essere stato accuratamente lavato",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Erbe aromatiche"));

            foods.add(new Food("Finocchietto", "Va consumato dopo essere stato accuratamente lavato",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Erbe aromatiche"));

            foods.add(new Food("Prezzemolo", "Va consumato dopo essere stato accuratamente lavato",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Erbe aromatiche"));

            foods.add(new Food("Rucola", "Va consumatoa dopo essere stata accuratamente lavata",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Erbe aromatiche"));

            foods.add(new Food("Salvia", "Va consumata dopo essere statoa accuratamente lavata",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Erbe aromatiche"));

            /*Formaggi*/
            foods.add(new Food("Asiago", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));

            foods.add(new Food("Brie", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Formaggio"));

            foods.add(new Food("Camembert", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Cheddar", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));

            foods.add(new Food("Gorgonzola", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Formaggio"));

            foods.add(new Food("Feta", "La feta è un formaggio tradizionale greco, a pasta semidura ma friabile, bianchissimo e piuttosto salato",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Formaggio"));
            foods.add(new Food("Formaggio svizzero", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Fontina", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Fiocchi di latte", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Formaggio spalmabile", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));

            foods.add(new Food("Latte crudo", "Da consumare previa bollitura\nPreferire il latte UHT",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Formaggio"));

            foods.add(new Food("Mascarpone", "E' un latticino piuttosto grasso che dev'essere consumato con parsimonia\nIn caso di mascarpone artigianale, il consiglio è di evitarlo",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Mozzarella", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.POS, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));

            foods.add(new Food("Panna da cucina", "Bisogna assicurarsi che sia prodotta con latte pastorizzato",
                    Food.NEG, Food.POS, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Panna da montare", "Se acquistata al supermercato non presenta rischi",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Panna spray", "Se acquistata al supermercato non presenta rischi",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Parmigiano", "Per assicurarsi la giusta quantità di calcio giornaliera basta inserire nella propria alimentazione un pezzetto di parmigiano ogni giorno",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Provolone dolce", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Pecorino", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Provola", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Provola affumicata", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));

            foods.add(new Food("Ricotta", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.POS, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Robiola", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Formaggio"));
            foods.add(new Food("Roquefort", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Formaggio"));

            foods.add(new Food("Scamorza", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Scamorza affumicata", "Se fabbricata con latte pastorizzato può essere consumata in sicurezza, poiché priva di listeriosi",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));
            foods.add(new Food("Stilton", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Formaggio"));

            foods.add(new Food("Yogurt", "Se pastorizzato, non presenta rischi",
                    Food.NEG, Food.POS, Food.NEG, Food.ALMOSTSAFE, "Formaggio"));

            /*Frutta*/
            foods.add(new Food("Frutta fresca", "Dev'essere consumata sbucciata e ben lavata",
                    Food.POS, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Frutta"));

            foods.add(new Food("Olive verdi", "Devono essere ben lavate",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Frutta"));

            /*Frutta secca*/
            foods.add(new Food("Albicocche secche", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));
            foods.add(new Food("Arachidi", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Castagne", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Datteri", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Fichi secchi", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Mandorle", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Mandorle", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Noci", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));
            foods.add(new Food("Nocciole", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Pistacchi", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));
            foods.add(new Food("Pinoli", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));
            foods.add(new Food("Prugne secche", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            foods.add(new Food("Uva passa", "Non abusarne in caso di diabete",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutta secca"));

            /*Frutti di mare*/
            foods.add(new Food("Aragosta cotta", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));

            foods.add(new Food("Cozze cotte", "Vanno consumate con moderazione e dopo essersi assicurati della provenienza e di una corretta cottura (di almeno 15 minuti in acqua bollente)",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Frutti di mare"));
            foods.add(new Food("Cozze crude", "",
                    Food.NEG, Food.NEG, Food.POS, Food.NOSAFE, "Frutti di mare"));
            foods.add(new Food("Calamari cotti", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));
            foods.add(new Food("Calamari crudi", "",
                    Food.NEG, Food.NEG, Food.POS, Food.NOSAFE, "Frutti di mare"));
            foods.add(new Food("Crostacei", "Devono essere freschissimi e perfettamente cotti per evitare la listeriosi, l'Epatite A ed eventuali intossicazioni",
                    Food.NEG, Food.POS, Food.POS, Food.ALMOSTSAFE, "Frutti di mare"));
            foods.add(new Food("Crostacei fritti", "Da mangiare con molta moderazione a causa delle elevate calorie ed un possibile rischio cancerogeno",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Frutti di mare"));

            foods.add(new Food("Gamberi cotti", "Vanno consumate solo in modo occasionale, perché sono animali filtratori e rischiano di trasmettere eventuali sostanze tossiche presenti nell'ambiente",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));
            foods.add(new Food("Gamberi crudi", "",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Frutti di mare"));
            foods.add(new Food("Granchi cotti", "Vanno consumate solo in modo occasionale, perché sono animali filtratori e rischiano di trasmettere eventuali sostanze tossiche presenti nell'ambiente",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));
            foods.add(new Food("Granchi crudi", "",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Frutti di mare"));

            foods.add(new Food("Molluschi", "Devono essere freschissimi e perfettamente cotti per evitare la listeriosi, l'Epatite A ed eventuali intossicazioni",
                    Food.NEG, Food.POS, Food.POS, Food.ALMOSTSAFE, "Frutti di mare"));
            foods.add(new Food("Molluschi fritti", "Da mangiare con molta moderazione a causa delle elevate calorie ed un possibile rischio cancerogeno",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Frutti di mare"));

            foods.add(new Food("Ostriche cotte", "Vanno consumate solo in modo occasionale, perché sono animali filtratori e rischiano di trasmettere eventuali sostanze tossiche presenti nell'ambiente",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Frutti di mare"));
            foods.add(new Food("Ostriche crude", "",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Frutti di mare"));

            foods.add(new Food("Polipi cotti", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));

            foods.add(new Food("Riccio di mare", "Non presenta rischi, siccome cresce in acque non inquinate\nTuttavia, è sempre consigliabile moderazione",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));

            foods.add(new Food("Seppie cotte", "",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Frutti di mare"));

            foods.add(new Food("Vongole cotte", "Vanno consumate con moderazione e dopo essersi assicurati della provenienza e di una corretta cottura",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Frutti di mare"));
            foods.add(new Food("Vongole crude", "",
                    Food.NEG, Food.NEG, Food.POS, Food.NOSAFE, "Frutti di mare"));

            /*Funghi*/
            foods.add(new Food("Funghi crudi", "Anche se di provenienza sicura, è meglio evitare funghi crudi",
                    Food.NEG, Food.NEG, Food.NEG, Food.NOSAFE, "Funghi"));
            foods.add(new Food("Funghi raccolti amatorialmente", "I funghi raccolti da persone non esperte sono sconsigliati in gravidanza a causa di una possibile tossicità e velenosità",
                    Food.NEG, Food.NEG, Food.NEG, Food.NOSAFE, "Funghi"));
            foods.add(new Food("Funghi surgelati", "Se acquistati in supermercati o negozi qualificati sono sicuri\nNon abusarne per evitare problemi di digestione",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Funghi"));
            foods.add(new Food("Funghi secchi", "Se acquistati in supermercati o negozi qualificati sono sicuri\nNon abusarne per evitare problemi di digestione\nConsumare sempre previa cottura",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Funghi"));


            /*Legumi*/
            foods.add(new Food("Ceci", "Da mangiare dopo essere stati accuratamente cotti",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Legumi"));

            foods.add(new Food("Germogli di legumi", "",
                    Food.NEG, Food.POS, Food.NEG, Food.NOSAFE, "Legumi"));

            foods.add(new Food("Lenticchie", "Da mangiare dopo essere state accuratamente cotte",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Legumi"));

            foods.add(new Food("Fagioli", "Da mangiare dopo essere stati accuratamente cotti",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Legumi"));

            /*Olio*/
            foods.add(new Food("Olio di oliva crudo", "Dev'essere consumata sbucciata e ben lavata",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Olio"));

            /*Pesce*/
            foods.add(new Food("Alici marinate", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Aringhe marinate", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Acciughe marinate", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Carpaccio", "Può provocare pericolose intossicazioni alimentari",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));

            foods.add(new Food("Pesce affumicato", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Pesce crudo", "Il consumo di pesce crudo è sempre sconsigliato per il rischio di contaminazione con epatite A, batteri e altri parassiti\nQuesto a meno di non essere assolutamente certi che il pesce sia stato abbattuto termicamente e che dopo il trattamento è stato mantenuto in condizioni igieniche perfette",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Pesce marinato", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Pesce spada", "I pesci di grandi dimensioni contengono alte concentrazioni di mercurio che possono essere dannose per il bambino\nNon superare una porzione a settimana",
                    Food.NEG, Food.NEG, Food.POS, Food.ALMOSTSAFE, "Pesce"));
            foods.add(new Food("Pesce spada affumicato", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));

            foods.add(new Food("Salmone affumicato", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Sgombro", "I pesci di grandi dimensioni contengono alte concentrazioni di mercurio che possono essere dannose per il bambino\nNon superare una porzione a settimana",
                    Food.NEG, Food.NEG, Food.POS, Food.ALMOSTSAFE, "Pesce"));
            foods.add(new Food("Sushi", "Può provocare pericolose intossicazioni alimentari",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Sashimi", "Può provocare pericolose intossicazioni alimentari",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));

            foods.add(new Food("Tonno", "I pesci di grandi dimensioni contengono alte concentrazioni di mercurio che possono essere dannose per il bambino\nNon superare una porzione a settimana",
                    Food.NEG, Food.NEG, Food.POS, Food.ALMOSTSAFE, "Pesce"));
            foods.add(new Food("Tonno affumicato", "Trattamenti come marinatura e affumicatura possono ridurre il rischio di contaminazione da parte di parassiti ma non lo azzerano completamente",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Pesce"));
            foods.add(new Food("Tonno in scatola", "I pesci di grandi dimensioni contengono alte concentrazioni di mercurio che possono essere dannose per il bambino\nNon superare una porzione a settiman",
                    Food.NEG, Food.POS, Food.POS, Food.ALMOSTSAFE, "Pesce"));
            foods.add(new Food("Trota affumicata", "",
                    Food.NEG, Food.POS, Food.NOSAFE, Food.NEG, "Pesce"));

            foods.add(new Food("Verdesca", "I pesci di grandi dimensioni contengono alte concentrazioni di mercurio che possono essere dannose per il bambino\nNon superare una porzione a settimana",
                    Food.NEG, Food.POS, Food.POS, Food.ALMOSTSAFE, "Pesce"));

            /*Salamoia*/
            foods.add(new Food("Olive in salamoia", "Hanno una maggiore percentuale di sodio rispetto alle olive cotte al forno\nInoltre, l’alto contenuto di sale può comportare un aumento della pressione arteriosa",
                    Food.NEG, Food.NEG, Food.NEG, Food.NOSAFE, "Salamoia"));

            /*Sott'aceto*/
            foods.add(new Food("Sott'aceto", "Gli alimenti sott'aceto possono essere nocivi per il feto se presentano muffa, sono stati aperti da più di due giorni o sono stati fatti in casa",
                    Food.NEG, Food.NEG, Food.POS, Food.ALMOSTSAFE, "Sott'aceto"));

            /*Sott'olio*/
            foods.add(new Food("Sott'olio", "Gli alimenti sott'olio possono essere nocivi per il feto se presentano muffa, sono stati aperti da più di due giorni o sono stati fatti in casa",
                    Food.NEG, Food.NEG, Food.POS, Food.ALMOSTSAFE, "Sott'olio"));

            /*Uova*/
            foods.add(new Food("Uova crude", "Da consumare solo cotte o pastorizzate per evitare listeriosi e salmonellosi",
                    Food.NEG, Food.POS, Food.POS, Food.NOSAFE, "Uova"));
            foods.add(new Food("Uova fritte", "Da mangiare con molta moderazione a causa delle elevate calorie ed un possibile rischio cancerogeno",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Uova"));

            /*Verdura*/
            foods.add(new Food("Verdura cruda", "Consumare solo dopo un accurato lavaggio",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Verdura"));
            foods.add(new Food("Verdura grigliata", "Assicurarsi che la griglia sia ben pulita",
                    Food.NEG, Food.NEG, Food.NEG, Food.SAFE, "Verdura"));
            foods.add(new Food("Verdura fritta", "Da mangiare con molta moderazione a causa delle elevate calorie ed un possibile rischio cancerogeno",
                    Food.NEG, Food.NEG, Food.NEG, Food.ALMOSTSAFE, "Verdura"));

            foods.add(new Food("Zucca", "Va sempre ben lavata o cotta",
                    Food.POS, Food.NEG, Food.NEG, Food.SAFE, "Verdura"));

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