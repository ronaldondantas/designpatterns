package br.com.nadod.designpatterns.memento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.nadod.designpatterns.R;

public class MementoActivity extends AppCompatActivity {

    boolean hasUndo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memento);

        final Digits digits = new Digits();

        final EditText editText = (EditText) findViewById(R.id.etDigit);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!hasUndo && editable.length() > 0) {
                    String lastDigit = String.valueOf(editable.charAt(editable.length() - 1));
                    digits.writeDigit(lastDigit);
                    Log.d("MEMENTO afterTextChange", lastDigit);
                }
                hasUndo = false;
            }
        });

        Button button = (Button) findViewById(R.id.undoBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasUndo = true;
                digits.undo();
                Log.d("MEMENTO undo", digits.getDigits());
                editText.setText(digits.getDigits());
            }
        });
    }
}
