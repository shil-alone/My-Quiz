package com.codershil.myquiz;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Categories extends AppCompatActivity {

    public static final String key = "CATEGORY";

    public static String getKey(){
        return key;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void sendUrl(View view){
        Intent intent = new Intent(Categories.this,MainActivity.class);

        switch (view.getId()){
            case R.id.btnGk:

                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=9&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnBooks:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=10&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnFilm:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=11&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnMusic:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=12&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnMusical:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=13&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnTelevision:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=14&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnVideoGame:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=15&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnBoradGame:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=16&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnNature:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=17&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnComputers:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=18&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnMath:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=19&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnMytho:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=20&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnSports:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=21&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnGeography:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=22&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnHistory:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=23&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnPolitical:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=24&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnArt:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=25&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnCelebrities:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=26&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnAnimals:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=27&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnVehicles:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=28&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnComics:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=29&type=multiple&encode=base64");
                startActivity(intent);
                break;

            case R.id.btnGadget:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=30&type=multiple&encode=base64");
                startActivity(intent);
                break;


            case R.id.btnCartoon:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&category=32&type=multiple&encode=base64");
                startActivity(intent);
                break;
            default:
                intent.putExtra(key,"https://opentdb.com/api.php?amount=10&type=multiple&encode=base64");
                startActivity(intent);
                break;

        }

    }

}