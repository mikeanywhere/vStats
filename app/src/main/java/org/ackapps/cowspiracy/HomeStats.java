package org.ackapps.cowspiracy;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class HomeStats extends ActionBarActivity {

    protected ProgressBar animalsProgressBar;

    protected ProgressBar co2ProgressBar;
    protected ProgressBar co2ProgressBar2;

    protected ProgressBar waterProgressBar;
    protected ProgressBar waterProgressBar2;
    protected ProgressBar waterProgressBar3;
    protected ProgressBar waterProgressBar4;
    protected ProgressBar waterProgressBar5;

    protected ProgressBar grainProgressBar;
    protected ProgressBar grainProgressBar2;
    protected ProgressBar grainProgressBar3;
    protected ProgressBar grainProgressBar4;

    protected ProgressBar rainforestProgressBar;
    protected ProgressBar rainforestProgressBar2;
    protected ProgressBar rainforestProgressBar3;

    protected TextView animalNumber;
    protected TextView waterNumber;
    protected TextView grainNumber;
    protected TextView co2Number;
    protected TextView rainforestNumber;
    protected TextView userLevelTextView;

    protected int totalWaterToDate = 1;
    protected int totalCo2ToDate = 1;
    protected int totalGrainToDate = 1;
    protected int totalRainforetSavedToDate = 1;
    protected int totalAnimalsSavedToDate = 1;

    protected int userAnimalDailyConsumption = 1;
    protected int userLevel = 0;
    protected String[] userLevelNameArray;
    protected String userLevelNameString;
    protected int daysSinceStart = 6;

    protected int[] statsArray;

    protected static final int WATER_PER_DAY = 1100;
    protected static final int cO2_PER_DAY = 20;
    protected static final int GRAIN_PER_DAY = 45;
    protected static final int RAINFOREST_PER_DAY = 30;
    protected static int ANIMALS_PER_DAY = 1;

    protected final String PREFS_FILE_NAME = "myPrefsCowspiracy";
    protected final String DELIMITER = ";;DELIMITER;;";

    protected SharedPreferences sharedPreferences;
    protected Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_stats);

        totalWaterToDate = WATER_PER_DAY*daysSinceStart;
        totalCo2ToDate = cO2_PER_DAY*daysSinceStart;
        totalGrainToDate = GRAIN_PER_DAY*daysSinceStart;
        totalRainforetSavedToDate = RAINFOREST_PER_DAY*daysSinceStart;
        totalAnimalsSavedToDate = userAnimalDailyConsumption*daysSinceStart;

        setUpProgressBars();


        sharedPreferences = getSharedPreferences(PREFS_FILE_NAME,0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        resources = getResources();


        statsArray = new int[]{WATER_PER_DAY,
                cO2_PER_DAY,
                GRAIN_PER_DAY,
                RAINFOREST_PER_DAY,
                userAnimalDailyConsumption};


        userLevel = Math.round(daysSinceStart/10);
        userLevelNameArray = resources.getStringArray(R.array.userLevelNames);
        userLevelNameString = userLevelNameArray[userLevel];
        userLevelTextView = (TextView) findViewById(R.id.user_level);
        userLevelTextView.setText(userLevelNameString);

    }

    protected void setUpProgressBars() {
        animalsProgressBar = (ProgressBar) findViewById(R.id.animal_progress_bar);
        animalsProgressBar.getProgressDrawable().setColorFilter(Color.parseColor("#EC1717"), PorterDuff.Mode.SRC_IN);
        animalsProgressBar.setMax(ANIMALS_PER_DAY*100);
        animalsProgressBar.setProgress(totalAnimalsSavedToDate*10);
        animalNumber = (TextView) findViewById(R.id.animals_number);
        animalNumber.setText(totalAnimalsSavedToDate +"");

        co2ProgressBar = (ProgressBar) findViewById(R.id.co2_progress_bar);
        co2ProgressBar.getProgressDrawable().setColorFilter(Color.parseColor("#52504B"), PorterDuff.Mode.SRC_IN);
        co2ProgressBar.setMax(cO2_PER_DAY*50);
        co2ProgressBar.setProgress(totalCo2ToDate*10);
        if((totalCo2ToDate*10) >= (cO2_PER_DAY*50)){
            co2ProgressBar2 = (ProgressBar) findViewById(R.id.co2_progress_bar_2);
            co2ProgressBar2.getProgressDrawable().setColorFilter(Color.parseColor("#52504B"), PorterDuff.Mode.SRC_IN);
            co2ProgressBar2.setMax(cO2_PER_DAY*50);
            co2ProgressBar2.setProgress((totalCo2ToDate*10)-(cO2_PER_DAY*50));
        }
        co2Number = (TextView) findViewById(R.id.co2_number);
        co2Number.setText(totalCo2ToDate+"");


            rainforestProgressBar = (ProgressBar) findViewById(R.id.rainforest_progress_bar);
            rainforestProgressBar.getProgressDrawable().setColorFilter(Color.parseColor("#388B01"), PorterDuff.Mode.SRC_IN);
            rainforestProgressBar.setMax(RAINFOREST_PER_DAY * 100);
            rainforestProgressBar.setProgress(totalRainforetSavedToDate*33);
        if((totalRainforetSavedToDate*33) >= (RAINFOREST_PER_DAY*100)) {
            rainforestProgressBar2 = (ProgressBar) findViewById(R.id.rainforest_progress_bar_2);
            rainforestProgressBar2.getProgressDrawable().setColorFilter(Color.parseColor("#388B01"), PorterDuff.Mode.SRC_IN);
            rainforestProgressBar2.setMax(RAINFOREST_PER_DAY * 100);
            rainforestProgressBar2.setProgress((totalRainforetSavedToDate*33)-(RAINFOREST_PER_DAY*100));

            if((totalRainforetSavedToDate*33)+(RAINFOREST_PER_DAY*100)>=(RAINFOREST_PER_DAY*66) ){
                rainforestProgressBar3 = (ProgressBar) findViewById(R.id.rainforest_progress_bar_3);
                rainforestProgressBar3.getProgressDrawable().setColorFilter(Color.parseColor("#388B01"), PorterDuff.Mode.SRC_IN);
                rainforestProgressBar3.setMax(RAINFOREST_PER_DAY * 100);
                rainforestProgressBar3.setProgress((totalRainforetSavedToDate*33)-(RAINFOREST_PER_DAY*100));
            }
        }


        rainforestNumber = (TextView) findViewById(R.id.rainforest_number);
        rainforestNumber.setText(totalRainforetSavedToDate+"");

        grainProgressBar = (ProgressBar) findViewById(R.id.grain_progress_bar);
        grainProgressBar.getProgressDrawable().setColorFilter(Color.parseColor("#ddae36"), PorterDuff.Mode.SRC_IN);
        grainProgressBar.setMax(GRAIN_PER_DAY*5);
        grainProgressBar.setProgress(totalGrainToDate);
            grainProgressBar2 = (ProgressBar) findViewById(R.id.grain_progress_bar_2);
            grainProgressBar2.getProgressDrawable().setColorFilter(Color.parseColor("#daa520"), PorterDuff.Mode.SRC_IN);
            grainProgressBar2.setMax(GRAIN_PER_DAY*10);
            grainProgressBar2.setProgress(totalGrainToDate);
                grainProgressBar3 = (ProgressBar) findViewById(R.id.grain_progress_bar_3);
                grainProgressBar3.getProgressDrawable().setColorFilter(Color.parseColor("#daa520"), PorterDuff.Mode.SRC_IN);
                grainProgressBar3.setMax(GRAIN_PER_DAY*15);
                grainProgressBar3.setProgress(totalGrainToDate);
                    grainProgressBar4 = (ProgressBar) findViewById(R.id.grain_progress_bar_4);
                    grainProgressBar4.getProgressDrawable().setColorFilter(Color.parseColor("#c4941c"), PorterDuff.Mode.SRC_IN);
                    grainProgressBar4.setMax(GRAIN_PER_DAY*20);
                    grainProgressBar4.setProgress(totalGrainToDate);
        grainNumber = (TextView) findViewById(R.id.grain_number);
        grainNumber.setText(totalGrainToDate+"");

        waterProgressBar = (ProgressBar) findViewById(R.id.water_progress_bar);
        waterProgressBar.getProgressDrawable().setColorFilter(Color.parseColor("#2098ae"), PorterDuff.Mode.SRC_IN);
        waterProgressBar.setMax(WATER_PER_DAY*10);
        waterProgressBar.setProgress(totalWaterToDate);
            waterProgressBar2 = (ProgressBar) findViewById(R.id.water_progress_bar_2);
            waterProgressBar2.getProgressDrawable().setColorFilter(Color.parseColor("#088da5"), PorterDuff.Mode.SRC_IN);
            waterProgressBar2.setMax(WATER_PER_DAY*10);
            waterProgressBar2.setProgress(totalWaterToDate);
                waterProgressBar3 = (ProgressBar) findViewById(R.id.water_progress_bar_3);
                waterProgressBar3.getProgressDrawable().setColorFilter(Color.parseColor("#077e94"), PorterDuff.Mode.SRC_IN);
                waterProgressBar3.setMax(WATER_PER_DAY*10);
                waterProgressBar3.setProgress(totalWaterToDate);
                    waterProgressBar4 = (ProgressBar) findViewById(R.id.water_progress_bar_4);
                    waterProgressBar4.getProgressDrawable().setColorFilter(Color.parseColor("#067084"), PorterDuff.Mode.SRC_IN);
                    waterProgressBar4.setMax(WATER_PER_DAY*10);
                    waterProgressBar4.setProgress(totalWaterToDate);
                        waterProgressBar5 = (ProgressBar) findViewById(R.id.water_progress_bar_5);
                        waterProgressBar5.getProgressDrawable().setColorFilter(Color.parseColor("#056273"), PorterDuff.Mode.SRC_IN);
                        waterProgressBar5.setMax(WATER_PER_DAY*10);
                        waterProgressBar5.setProgress(totalWaterToDate);

        waterNumber = (TextView) findViewById(R.id.water_number);
        waterNumber.setText(totalWaterToDate+"");




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
