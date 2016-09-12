package br.com.nadod.designpatterns.iterator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import br.com.nadod.designpatterns.R;

public class IteratorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterator);

        //initGame

        Random r = new Random();

        //arrayList

        List<Integer> arrayListNumbers = new ArrayList<>();
        for (int i=0; i<20; i++) {
            arrayListNumbers.add(1 + r.nextInt(20));
        }
        final CardsIterator playerArrayList = new PlayerArrayList(arrayListNumbers);

        //stack

        Stack<Integer> stackNumbers = new Stack<>();
        for (int i=0; i<20; i++) {
            stackNumbers.add(1 + r.nextInt(20));
        }
        final CardsIterator playerStack = new PlayerStack(stackNumbers);

        //game

        refreshValues(arrayListNumbers, stackNumbers);

        final TextView tvResultGame = (TextView) findViewById(R.id.tvResultGame);

        //nextRound
        Button resultButton = (Button) findViewById(R.id.butRound);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IteratorInterface arrayListInterator = playerArrayList.createIterator();
                IteratorInterface stackInterator = playerStack.createIterator();

                arrayListInterator.first();
                stackInterator.first();

                if (arrayListInterator.lower() < stackInterator.lower()) {
                    arrayListInterator.add(stackInterator.lower());
                    stackInterator.remove();

                    if (stackInterator.isEmpty()) {
                        tvResultGame.setText("O vencedor foi o JOGADOR B");
                    }
                } else if (arrayListInterator.lower() > stackInterator.lower()) {
                    stackInterator.add(arrayListInterator.lower());
                    arrayListInterator.remove();

                    if (arrayListInterator.isEmpty()) {
                        tvResultGame.setText("O vencedor foi o JOGADOR A");
                    }
                } else {
                    arrayListInterator.add(arrayListInterator.currentItem());
                    arrayListInterator.remove();

                    stackInterator.add(stackInterator.currentItem());
                    stackInterator.remove();
                }
                refreshValues(arrayListInterator.getValues(), stackInterator.getValues());
            }
        });
    }

    private void refreshValues(List<Integer> arrayListNumbers, List<Integer> stackNumbers) {
        List<String> arrayListNumbersString = new ArrayList<>(arrayListNumbers.size());
        for (Integer myInt : arrayListNumbers) {
            arrayListNumbersString.add(String.valueOf(myInt));
        }

        TextView tvPlayerA = (TextView) findViewById(R.id.tvCardsA);
        tvPlayerA.setText(TextUtils.join(" ", arrayListNumbersString));

        List<String> stackNumbersString = new ArrayList<>(stackNumbers.size());
        for (Integer myInt : stackNumbers) {
            stackNumbersString.add(String.valueOf(myInt));
        }

        TextView tvPlayerB = (TextView) findViewById(R.id.tvCardsB);
        tvPlayerB.setText(TextUtils.join(" ", stackNumbersString));
    }
}
