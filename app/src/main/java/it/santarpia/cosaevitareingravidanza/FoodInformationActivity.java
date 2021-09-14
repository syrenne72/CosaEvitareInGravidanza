package it.santarpia.cosaevitareingravidanza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodInformationActivity extends AppCompatActivity {
    private TextView tvFoodName, tvFoodDesc, tvListeriosiPresence, tvToxoPresence, tvSalmPresence, tvSafe;
    private ImageView ivListeriosi,ivToxo, ivSalm, ivSafe;

    private DBmanager dBmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_information);

        tvFoodName = findViewById(R.id.tvFoodName);
        tvFoodDesc = findViewById(R.id.tvFoodDesc);
        tvListeriosiPresence = findViewById(R.id.tvListeriosiPresence);
        tvToxoPresence = findViewById(R.id.tvToxoPresence);
        tvSalmPresence = findViewById(R.id.tvSalmPresence);
        tvSafe = findViewById(R.id.tvSafe);

        ivListeriosi = findViewById(R.id.ivListeriosi);
        ivToxo = findViewById(R.id.ivToxo);
        ivSalm = findViewById(R.id.ivSalm);
        ivSafe = findViewById(R.id.ivSafe);

        dBmanager = new DBmanager(getApplicationContext());

        setView();
    }

    private void setView() {
        String id = getIntent().getStringExtra("id");
        int toxo = getIntent().getIntExtra("toxo", -1);

        //If an error occours, intent will be deleted
        if(id.equalsIgnoreCase("") || toxo == -1)
            onBackPressed();

        Food food = dBmanager.findFoodById(id);

        Log.d("kiwi", getClass().getSimpleName() + ": food: " + food.toString());

        tvFoodName.setText(food.getName());
        tvFoodDesc.setText(food.getDescription());

        if(food.getListeriosi() == Food.NEG) {
            ivListeriosi.setVisibility(View.GONE);
            tvListeriosiPresence.setVisibility(View.GONE);
        }

        if(food.getToxoplasmosi() == Food.NEG) {
            ivToxo.setVisibility(View.GONE);
            tvToxoPresence.setVisibility(View.GONE);
        }

        if(food.getSalmonellosi() == Food.NEG) {
            ivSalm.setVisibility(View.GONE);
            tvSalmPresence.setVisibility(View.GONE);
        }

        int f_safe = food.getSafe();

        if(f_safe == Food.ALMOSTSAFE) {
            ivSafe.setBackground(getDrawable(R.drawable.almostsafe));
            tvSafe.setText("questo cibo può essere consumato a determinate condizioni");
        } else if((f_safe == Food.SAFE) || (food.getToxoplasmosi() == Food.POS && toxo == 1)) {
            ivSafe.setBackground(getDrawable(R.drawable.safe));
            tvSafe.setText("questo cibo può essere tranquillamente consumato");
        } else if(f_safe == Food.NOSAFE) {
            ivSafe.setBackgroundResource(R.drawable.nosafe);
            tvSafe.setText("questo cibo non può essere consumato");
        }
    }
}