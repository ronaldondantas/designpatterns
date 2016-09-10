package br.com.nadod.designpatterns.mediator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.nadod.designpatterns.R;

public class MediatorActivity extends AppCompatActivity {

    public enum UserType{
        Student,
        Teacher
    }

    ChatMediator mediator = new ChatMediatorImpl();
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediator);

        final TextView tvResult = (TextView) findViewById(R.id.tvResultSend);

        Button butAddStudent = (Button) findViewById(R.id.butAddStudent);
        butAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddUser(UserType.Student);
            }
        });

        Button butAddTeacher = (Button) findViewById(R.id.butAddTeacher);
        butAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogAddUser(UserType.Teacher);
            }
        });


        final RadioButton rbAmITeacher = (RadioButton) findViewById(R.id.rbAmITeacher);
        final RadioButton rbAmIStudent = (RadioButton) findViewById(R.id.rbAmIStudent);

        final RadioButton rbSendToStudent = (RadioButton) findViewById(R.id.rbSendToStudent);
        final RadioButton rbSendToTeacher = (RadioButton) findViewById(R.id.rbSendToTeacher);

        rbAmITeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbAmIStudent.setChecked(!b);
                rbSendToTeacher.setChecked(!b);
                if (b) {
                    currentUser = new UserImpl(mediator, "usuário", UserType.Teacher);
                } else {
                    currentUser = null;
                }
            }
        });

        rbAmIStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbAmITeacher.setChecked(!b);
                rbSendToTeacher.setEnabled(b);
                if (b) {
                    rbSendToTeacher.setChecked(false);
                    currentUser = new UserImpl(mediator, "usuário", UserType.Student);
                } else {
                    currentUser = null;
                }
            }
        });

        rbSendToTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rbSendToStudent.setChecked(!b);
            }
        });

        rbSendToStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rbSendToTeacher.isEnabled()) rbSendToTeacher.setChecked(!b);
            }
        });

        Button butSendMsg = (Button) findViewById(R.id.butSendMsg);
        butSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvResult.setText("");

                UserType to = UserType.Teacher;
                if (rbSendToTeacher.isChecked()) to = UserType.Teacher;
                else if (rbSendToStudent.isChecked()) to = UserType.Student;

                if (currentUser != null) {
                    if (currentUser.getType() == UserType.Teacher) {
                        tvResult.setText(tvResult.getText() + "\n" + currentUser.send("Olá meus alunos!", to));
                    } else if (currentUser.getType() == UserType.Student) {
                        if (rbSendToTeacher.isChecked()) tvResult.setText(tvResult.getText() + "\n" + currentUser.send("Olá professor!", to));
                        if (rbSendToStudent.isChecked()) tvResult.setText(tvResult.getText() + "\n" + currentUser.send("Olá galera!", to));
                    }
                }
            }
        });
    }

    private void openDialogAddUser(final UserType userType) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog, null);

        builder.setView(view).setCancelable(false)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etName = (EditText) view.findViewById(R.id.etName);
                        String etText = etName.getText().toString();
                        if (!etText.isEmpty()) {
                            mediator.addUser(new UserImpl(mediator, etText, userType));
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).show();
    }
}
