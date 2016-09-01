package br.com.nadod.designpatterns.abstractfactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.util.Calendar;

import br.com.nadod.designpatterns.MainActivity;
import br.com.nadod.designpatterns.R;

public class AbstractFactoryActivity extends AppCompatActivity implements
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    TextView tvResult;
    TextView dateChoosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_factory);

        tvResult = (TextView) findViewById(R.id.tvResultPizza);
        dateChoosed = (TextView) findViewById(R.id.dateChoosed);

        Button butDate = (Button) findViewById(R.id.butPedirPizza);
        butDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        AbstractFactoryActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }
    @Override
    public void onDateSet(DatePickerDialog datePicker, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        dateChoosed.setText(date);

        try {
            AbstractPizzaiolo abstractPizzaiolo = AbstractPizzaiolo.createPizzaiolo(date);
            if (abstractPizzaiolo != null) {
                dateChoosed.setText(abstractPizzaiolo.cook().get());
            } else {
                dateChoosed.setText("Pizzaria fechada");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout timePicker, int hourOfDay, int minute, int second) {
        String time = "You picked the following time: "+hourOfDay+"h"+minute;
        dateChoosed.setText(time);
    }
}
