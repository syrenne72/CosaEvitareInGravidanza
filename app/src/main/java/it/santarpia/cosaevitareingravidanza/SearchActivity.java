package it.santarpia.cosaevitareingravidanza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    final int sdk = android.os.Build.VERSION.SDK_INT;

    private DBmanager dbmanager;
    private Cursor crs;
    private int toxo;

    private ListView lvHistory;
    private EditText etFoodName;
    private TextView tvSearchTitle;
    private CursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Referencing views
        lvHistory = findViewById(R.id.lvHistory);
        etFoodName = findViewById(R.id.etFoodName);
        tvSearchTitle = findViewById(R.id.tvSearchTitle);

        dbmanager = new DBmanager(this);

        searchSettings();

        //Searching for foods
        crs = dbmanager.findLastFoodsSearch(10);
//        Log.d("kiwi", crs.getString(crs.getColumnIndex(DBstring.F_FOOD_NAME)) + "");
        refreshListView();

        //Adding listener for autocomplete
        etFoodName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.d("kiwi", getClass().getSimpleName() + ": etFoodName: before change");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.d("kiwi", getClass().getSimpleName() + ": etFoodName: on change");
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                Log.d("kiwi", getClass().getSimpleName() + ": etFoodName: after change");
                String food = editable.toString();
//                Log.d("kiwi", getClass().getSimpleName() + ": etFoodName: text present: " + food);

                if(food.length() > 2) {
                    crs = dbmanager.findFoodByName(food);
                    tvSearchTitle.setText("Cibi trovati:");
                    refreshListView();
                } else if(food.length() == 0) {
                    crs = dbmanager.findLastFoodsSearch(10);
                    tvSearchTitle.setText("Ricerche recenti:");
                    refreshListView();
                }
            }
        });
    }

    /**
     * Searching for settings in user's database
     */
    private void searchSettings() {
        if(dbmanager.findSettings() == null) {
            final String[] name = {""};

            /*Ask for the name*/
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SearchActivity.this);
            alertDialog.setTitle("Nome");
            alertDialog.setMessage("Inserisci il tuo nome");
            final EditText input = new EditText(SearchActivity.this);
            input.setId(R.id.et_name);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            input.setLayoutParams(lp);
            alertDialog.setView(input);

            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Dialog d = (Dialog) dialog;
                    EditText edtext_username=  (EditText) d.findViewById(R.id.et_name);
                    name[0] = edtext_username.getText().toString();

//                    Log.d("Kiwi", ""+ name[0]);
                }
            });

            AlertDialog dialog = alertDialog.create();
            dialog.show();
            /**/

            /*Ask for toxoplasmosis antibodies*/
            dialog = new AlertDialog.Builder(SearchActivity.this).create();
            dialog.setTitle("Toxoplasmosi");
            dialog.setMessage("Hai gli anticorpi per la toxoplasmosi?");
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Sì",
                    new DialogInterface.OnClickListener() {
                        //saving toxoplasmosi's info
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            toxo = 1;
                        }
                    });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
                    new DialogInterface.OnClickListener() {
                        //saving toxoplasmosi's info
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                            toxo = 0;
                        }
                    });
            dialog.show();

            Log.d("kiwi", getClass().getSimpleName() + ": dialog box shown");

            /*Save user*/
            dbmanager.setSettings(new User(name[0], toxo));
        }

        //Check if toxoplasmosis antibodies are present
        User user = dbmanager.findSettings();
        toxo = user.isToxoplasmosi();
    }

    /**
     * Refresh the listview inserting new data from crs
     */
    private void refreshListView() {
        adapter = new CursorAdapter(this, crs, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                View v = getLayoutInflater().inflate(R.layout.list_item, null);
                return v;
            }

            @Override
            public void bindView(View view, Context context, Cursor crs) {
                final TextView tvFoodName;
                Button btnInfo;
                ImageView imgSafe, imgToxoplasmosi, imgListeriosi, imgSalmonellosi;

                tvFoodName = view.findViewById(R.id.tvFoodName);
                btnInfo = view.findViewById(R.id.btnInfo);
                imgSafe = view.findViewById(R.id.imgSafe);
                imgToxoplasmosi = view.findViewById(R.id.imgToxoplasmosi);
                imgListeriosi = view.findViewById(R.id.imgListeriosi);
                imgSalmonellosi = view.findViewById(R.id.imgSalmonellosi);

                int f_toxo = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_TOXOPLASMOSI));
                int f_safe = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_SAFE));

                Log.d("Kiwi", getClass().getSimpleName() + ": inserting food: " + crs.getString(crs.getColumnIndex(DBstring.F_FOOD_NAME)));
                Log.d("Kiwi", getClass().getSimpleName() + ": inserting food safety: " + crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_SAFE)));
                Log.d("Kiwi", getClass().getSimpleName() + ": inserting food salmonellosis: " + crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_SALMONELLOSI)));

                tvFoodName.setText("" + crs.getString(crs.getColumnIndex(DBstring.F_FOOD_NAME)));

//                Log.d("Kiwi", crs.getString(crs.getColumnIndex(DBstring.F_FOOD_NAME)) + ": " + (f_safe == Food.SAFE) + " " + (f_toxo == Food.POS && toxo == 1) + " " + (f_toxo == Food.NEG));
                if(f_safe == Food.ALMOSTSAFE)
                    imgSafe.setBackground(getDrawable(R.drawable.almostsafe));
                else if((f_safe == Food.SAFE) || (f_toxo == Food.POS && toxo == 1))
                    imgSafe.setBackground(getDrawable(R.drawable.safe));
                else if(f_safe == Food.NOSAFE)
                    imgSafe.setBackgroundResource(R.drawable.nosafe);

                if(f_toxo == Food.POS) {
                    imgToxoplasmosi.setVisibility(View.VISIBLE);
                    imgToxoplasmosi.setBackground(getDrawable(R.drawable.cat));
                } else
                    imgToxoplasmosi.setVisibility(View.INVISIBLE);

                if(crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_LISTERIOSI)) == Food.POS) {
//                    Log.d("kiwi", getClass().getSimpleName() + ": listeria present");
                    imgListeriosi.setVisibility(View.VISIBLE);
                    imgListeriosi.setBackground(ContextCompat.getDrawable(context, R.drawable.bacteria));
                } else
                    imgListeriosi.setVisibility(View.INVISIBLE);

                if(crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_SALMONELLOSI)) == Food.POS) {
//                    Log.d("kiwi", getClass().getSimpleName() + ": salmonellosis present");
                    imgSalmonellosi.setVisibility(View.VISIBLE);
                    imgSalmonellosi.setBackground(ContextCompat.getDrawable(context, R.drawable.bacteria_s));
                } else
                    imgSalmonellosi.setVisibility(View.INVISIBLE);

                btnInfo.setTag(crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_ID)));

                //Open new intent with food information
                btnInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Kiwi", getClass().getSimpleName() + ": opening new intent for food: " + btnInfo.getTag());

                        //Updating food last time be searched
                        int id = crs.getInt(crs.getColumnIndex(DBstring.F_FOOD_ID));
                        long time = System.currentTimeMillis();
                        dbmanager.updateFoodTime(id, time);

                        Intent i = new Intent(getApplicationContext(), FoodInformationActivity.class);
                        i.putExtra("id", btnInfo.getTag().toString());
                        i.putExtra("toxo", toxo);
                        startActivity(i);
                    }
                });
            }
        };

        lvHistory.setAdapter(adapter);

        Log.d("kiwi", getClass().getSimpleName() + ": refreshing list view");
    }

    /**
     * Method called by clicking on search button. Search all foods with specified name
     * @param view search button
     */
//    public void onSearch(View view) {
//        String name = etFoodName.getText().toString();
//
//        if(name.length() > 0 && name != null) {
//            crs = dbmanager.findFoodByName(name);
//
////            Log.d("Kiwi", getClass().getSimpleName() + ": found food: " + crs.getString(crs.getColumnIndex(DBstring.F_FOOD_NAME)));
//
//            tvSearchTitle.setText("Cibi trovati:");
//            refreshListView();
//        }
//    }

    @Override
    public void onBackPressed() {
        if(tvSearchTitle.getText().toString().equalsIgnoreCase("cibi trovati:")) {
            tvSearchTitle.setText("Ricerche recenti: ");
            crs = dbmanager.findLastFoodsSearch(10);
            refreshListView();
        } else
            super.onBackPressed();
    }
}