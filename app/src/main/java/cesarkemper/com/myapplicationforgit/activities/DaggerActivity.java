package cesarkemper.com.myapplicationforgit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import cesarkemper.com.myapplicationforgit.R;
import cesarkemper.com.myapplicationforgit.dagger.Coche;
import cesarkemper.com.myapplicationforgit.dagger.Motor;
import cesarkemper.com.myapplicationforgit.dagger.di.MotorApplication;

public class DaggerActivity extends AppCompatActivity {

    @Inject
    Motor motor;

    @Inject
    Coche coche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
//        motor = new Motor("Gasolina");
        ((MotorApplication)getApplication()).getMotorComponent().inject(this);
        Toast.makeText(DaggerActivity.this,motor.getTipoMotor(),Toast.LENGTH_SHORT).show();

        Toast.makeText(DaggerActivity.this, coche.getMotor(), Toast.LENGTH_SHORT).show();
    }
}
