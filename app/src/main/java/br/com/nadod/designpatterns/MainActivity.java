package br.com.nadod.designpatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;

import br.com.nadod.designpatterns.abstractfactory.AbstractFactoryActivity;
import br.com.nadod.designpatterns.abstractfactory.AbstractPizzaiolo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button abstractFactory = (Button) findViewById(R.id.abstractFactoryBut);
        abstractFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AbstractFactoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
