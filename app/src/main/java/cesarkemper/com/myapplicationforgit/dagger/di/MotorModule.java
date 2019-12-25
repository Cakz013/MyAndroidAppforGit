package cesarkemper.com.myapplicationforgit.dagger.di;

import cesarkemper.com.myapplicationforgit.dagger.Coche;
import cesarkemper.com.myapplicationforgit.dagger.Motor;
import dagger.Module;
import dagger.Provides;

@Module
public class MotorModule {
    @Provides
    public Motor providesMotorDiesel(){
        return new Motor("Diesel");
    }

    @Provides
    public Coche providesCoche(Motor motor){
        return new Coche(motor);
    }
}
