package br.com.nadod.designpatterns.memento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.nadod.designpatterns.R;

public class MementoActivity extends AppCompatActivity {

    boolean hasUndoOrOperator = false;
    ArrayList<Character> numbers =
            new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));

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
                if (!hasUndoOrOperator && editable.length() > 0) {
                    String lastDigit = String.valueOf(editable.charAt(editable.length() - 1));
                    digits.writeDigit(lastDigit);
                }
                hasUndoOrOperator = false;
            }
        });


        setOnClickListenerOperator(R.id.sumBut, digits, editText);
        setOnClickListenerOperator(R.id.subBut, digits, editText);
        setOnClickListenerOperator(R.id.multBut, digits, editText);
        setOnClickListenerOperator(R.id.divBut, digits, editText);

        Button button = (Button) findViewById(R.id.undoBut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int digitsLength = digits.getDigits().length();
                if (digitsLength > 0) {
                    hasUndoOrOperator = true;
                    if (!numbers.contains(digits.getDigits().charAt(digitsLength - 1))) {
                        digits.undo();
                    } else {
                        for (int i = (digitsLength - 1); i >= 0; i--) {
                            if (numbers.contains(digits.getDigits().charAt(i))) {
                                digits.undo();
                            } else {
                                break;
                            }
                        }
                    }
                    editText.setText(digits.getDigits());
                    editText.setSelection(editText.getText().length());
                }
            }
        });

        Button resultBut = (Button) findViewById(R.id.resultBut);
        resultBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasUndoOrOperator = true;
                char lastChar = digits.getDigits().charAt(digits.getDigits().length() - 1);
                Log.d("TAG", String.valueOf(digits.getDigits()));
                if (!numbers.contains(lastChar)) {
                    Log.d("TAG", String.valueOf(lastChar));
                    Toast.makeText(getApplicationContext(),
                            "Operação não calculável. Reveja os valores digitados",
                            Toast.LENGTH_LONG).show();
                    hasUndoOrOperator = false;
                }
            }
        });
    }

    private void setOnClickListenerOperator(final int resourceId, final Digits digits,
                                            final EditText editText) {
        Button buttonDiv = (Button) findViewById(resourceId);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (digits.getDigits().length() > 0) {
                    char lastChar = digits.getDigits().charAt(digits.getDigits().length() - 1);
                    if (numbers.contains(lastChar)) {
                        hasUndoOrOperator = true;
                        digits.writeDigit(getOperatorFromRes(resourceId));
                        editText.setText(digits.getDigits());
                        editText.setSelection(editText.getText().length());
                    }
                }
            }
        });
    }

    private String getOperatorFromRes(int resourceId) {
        switch (resourceId) {
            case R.id.sumBut: {
                return "+";
            }
            case R.id.subBut: {
                return "-";
            }
            case R.id.multBut: {
                return "*";
            }
            case R.id.divBut: {
                return "/";
            }
        }
        return "";
    }
}
