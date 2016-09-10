package br.com.nadod.designpatterns;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;

import br.com.nadod.designpatterns.abstractfactory.AbstractFactoryActivity;
import br.com.nadod.designpatterns.abstractfactory.AbstractPizzaiolo;
import br.com.nadod.designpatterns.mediator.MediatorActivity;
import br.com.nadod.designpatterns.memento.MementoActivity;

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

        Button memento = (Button) findViewById(R.id.mementoBut);
        memento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MementoActivity.class);
                startActivity(intent);
            }
        });

        Button mediator = (Button) findViewById(R.id.mediatorBut);
        mediator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MediatorActivity.class);
                startActivity(intent);
            }
        });
    }
}
