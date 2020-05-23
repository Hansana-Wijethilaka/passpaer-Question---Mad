package mad.example.it18220834;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import mad.example.it18220834.Database.DBHandler;

import static java.security.AccessController.getContext;

public class ProfileManagement extends AppCompatActivity {

    EditText username,dob,password;
    Button update,add;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        username = findViewById(R.id.user_nam);
        dob = findViewById(R.id.DOb);
        password = findViewById(R.id.passw);
        update = findViewById(R.id.btn3);
        male = findViewById(R.id.ma);
        female = findViewById(R.id.fe);
        add = findViewById(R.id.add);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){

                    gender = "Male";

                }
                else{

                    gender = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
                Toast.makeText(ProfileManagement.this, "user added.user ID:"+newID, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(true);
                female.setChecked(false);
            }
        });

    }
}
