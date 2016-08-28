package applications;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import services.json.provider.IJsonProvider;
import services.json.provider.QuotesmateApiServiceProvider;
import storage.IStore;
import storage.QuoteReaderDbHelper;
import storage.SqliteStore;

@Module
public class DataModule {

    @Provides
    @Singleton
    public QuoteReaderDbHelper provideSQLiteOpenHelper(Application application){
        return new QuoteReaderDbHelper(application);
    }

    @Provides
    @Singleton
    public IStore provideStorage(QuoteReaderDbHelper sqLiteOpenHelper) {
        return new SqliteStore(sqLiteOpenHelper);
    }

    @Provides
    @Singleton
    public IJsonProvider provideJsonProvider(IStore storage) {
        return new QuotesmateApiServiceProvider(storage);
    }
}
