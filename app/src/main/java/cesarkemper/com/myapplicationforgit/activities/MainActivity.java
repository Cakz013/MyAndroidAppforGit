package cesarkemper.com.myapplicationforgit.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cesarkemper.com.myapplicationforgit.R;

public class MainActivity extends AppCompatActivity {
    public EditText userId, password;
    Button btnSignUp;
    TextView tvsignIn;
    FirebaseAuth mFireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        userId = findViewById(R.id.UserText);
        password = findViewById(R.id.PasswordText);
        btnSignUp = findViewById(R.id.buttonSignUp);
        tvsignIn = findViewById(R.id.SignInHere);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = userId.getText().toString();
                String pwd = password.getText().toString();
                if (user.isEmpty()) {
                    userId.setError("Por Favor ingrese su Usuario");
                    userId.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Porfavor Ingrese su contraseña");
                    password.requestFocus();
                }else if (user.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Los campos estan vacios!", Toast.LENGTH_LONG).show();
                }else if (!(user.isEmpty() && pwd.isEmpty())){
                    mFireBaseAuth.createUserWithEmailAndPassword(user,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Registro fallido, Por favor intentelo de nuevo", Toast.LENGTH_LONG).show();
                            }else
                                {
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });

                }else{
                    Toast.makeText(MainActivity.this,"Ocurrio un error!", Toast.LENGTH_LONG).show();
                }
            }
        });

        tvsignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
