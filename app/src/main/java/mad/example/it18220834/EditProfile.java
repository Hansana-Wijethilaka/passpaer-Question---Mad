package mad.example.it18220834;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

import mad.example.it18220834.Database.DBHandler;

public class EditProfile extends AppCompatActivity {

    EditText username,dob,password;
    Button edit,delete,search;
    RadioButton male,female;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        username = findViewById(R.id.edit_user);
        dob = findViewById(R.id.edit_dob);
        password = findViewById(R.id.edit_pass);
        delete = findViewById(R.id.edit_Del);
        edit = findViewById(R.id.edit_Ed);
        female = findViewById(R.id.edit_female);
        male = findViewById(R.id.edit_male);
        search = findViewById(R.id.edit_btn);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                List user = dbHandler.readAllInfo(username.getText().toString());

                if(user.isEmpty()){

                    Toast.makeText(EditProfile.this, "user found.User :"+user.get(0).toString(), Toast.LENGTH_SHORT).show();
                    username.setText(null);
                }
                else{

                    username.setText(user.get(0).toString());
                    dob.setText(user.get(1).toString());
                    password.setText(user.get(2).toString());

                    if(user.get(3).toString().equals("Male")){

                        male.setChecked(true);
                    }
                    else{

                        female.setChecked(true);

                    }

                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(male.isChecked()){

                    gender = "Male";

                }
                else{

                    gender = "Female";
                }

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                Boolean status = dbHandler.updateInfo(username.getText().toString(),dob.getText().toString(),password.getText().toString(),gender);
                if(status){

                    Toast.makeText(EditProfile.this, "user updated", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(EditProfile.this, "update failed", Toast.LENGTH_SHORT).show();

                }


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());
                dbHandler.deleteInfo(username.getText().toString());

                Toast.makeText(EditProfile.this, "user deleted", Toast.LENGTH_SHORT).show();

                username.setText(null);
                dob.setText(null);
                password.setText(null);
                male.setChecked(false);
                female.setChecked(false);
            }
        });

    }
}
