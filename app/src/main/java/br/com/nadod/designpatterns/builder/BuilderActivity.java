package br.com.nadod.designpatterns.builder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.nadod.designpatterns.R;

public class BuilderActivity extends AppCompatActivity {

    final List<OrderItem> orderItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);


        //sandwich

        final RadioButton rbCheeseburger = (RadioButton) findViewById(R.id.rbCheeseburger);
        final RadioButton rbHamburger = (RadioButton) findViewById(R.id.rbHamburguer);
        rbHamburger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbCheeseburger.setChecked(!b);
            }
        });
        rbCheeseburger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbHamburger.setChecked(!b);
            }
        });

        //potato

        final RadioButton rbSmallPotato = (RadioButton) findViewById(R.id.rbSmall);
        final RadioButton rbMediumPotato = (RadioButton) findViewById(R.id.rbMedium);
        final RadioButton rbBigPotato = (RadioButton) findViewById(R.id.rbBig);
        rbSmallPotato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rbMediumPotato.setChecked(!b);
                    rbBigPotato.setChecked(!b);
                }
            }
        });
        rbMediumPotato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rbSmallPotato.setChecked(!b);
                    rbBigPotato.setChecked(!b);
                }
            }
        });
        rbBigPotato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rbSmallPotato.setChecked(!b);
                    rbMediumPotato.setChecked(!b);
                }
            }
        });

        //toy

        final RadioButton rbCar = (RadioButton) findViewById(R.id.rbCar);
        final RadioButton rbBarbie = (RadioButton) findViewById(R.id.rbBarbie);
        rbCar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbBarbie.setChecked(!b);
            }
        });
        rbBarbie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbCar.setChecked(!b);
            }
        });

        //soda

        final RadioButton rbGuarana = (RadioButton) findViewById(R.id.rbGuarana);
        final RadioButton rbCoca = (RadioButton) findViewById(R.id.rbCoca);
        rbGuarana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbCoca.setChecked(!b);
            }
        });
        rbCoca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbGuarana.setChecked(!b);
            }
        });

        //button

        Button butResult = (Button) findViewById(R.id.butDoOrder);
        butResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChosenItems();

                TextView tvResult = (TextView) findViewById(R.id.tvResultOrder);
                tvResult.setText(doOrder());
                orderItemList.clear();
            }
        });
    }

    private void getChosenItems() {
        getChosenOptions((LinearLayout) findViewById(R.id.llSandwich), "sandwich");
        getChosenOptions((LinearLayout) findViewById(R.id.llPotato), "potato");
        getChosenOptions((LinearLayout) findViewById(R.id.llToy), "toy");
        getChosenOptions((LinearLayout) findViewById(R.id.llSoda), "soda");
    }

    private void getChosenOptions(LinearLayout linearLayout, String type) {
        int count = linearLayout.getChildCount();
        OrderItem orderItem;
        for (int i=0; i<count; i++) {
            View rb = linearLayout.getChildAt(i);
            if (rb instanceof RadioButton) {
                RadioButton rbu = (RadioButton) rb;
                if (rbu.isChecked()) {
                    orderItem = new OrderItem(rbu.getText().toString(), type);
                    orderItemList.add(orderItem);
                }
            }
        }
    }

    private String doOrder() {
        OrderBuilder employee = new AtendenteOrderBuilder();
        Order attendedOrder = employee.mount(orderItemList);
        
        employee = new MontadorOrderBuilder();
        return employee.mount(attendedOrder.getAllItems()).toString();
    }
}
