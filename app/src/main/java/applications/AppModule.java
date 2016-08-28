package applications;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codebased on 28/08/16.
 */

@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    //  you can use @Named("...") though it is not required.
    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }
}
