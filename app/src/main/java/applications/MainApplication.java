package applications;

import android.app.Application;


public class MainApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Dagger<YourComponentName> class is created with the first build automatically.
        mAppComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                dataModule(new DataModule()).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}


