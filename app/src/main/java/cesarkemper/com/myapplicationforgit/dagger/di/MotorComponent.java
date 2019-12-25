package cesarkemper.com.myapplicationforgit.dagger.di;

import cesarkemper.com.myapplicationforgit.activities.DaggerActivity;
import cesarkemper.com.myapplicationforgit.activities.MainActivity;
import dagger.Component;

@Component(modules = MotorModule.class)
public interface MotorComponent {
    void inject(DaggerActivity daggerActivity);
}
