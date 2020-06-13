package com.example.smokingcessationhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_input);

        final EditText etName = (EditText) findViewById(R.id.ContactInputActivity_etInputName);
        final EditText etPhoneNum = (EditText) findViewById(R.id.ContactInputActivity_etInputPhoneNum);
        Button btSubmit = (Button) findViewById(R.id.ContactInputActivity_tvSubmit);

        btSubmit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                if (etName.getText().toString().equals("") || etPhoneNum.getText().toString().equals(""))
                    return;
                data.putExtra("name", etName.getText().toString());
                System.out.println(etName.getText());
                data.putExtra("phoneNum", etPhoneNum.getText().toString());
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
