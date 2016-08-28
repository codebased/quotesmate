package applications;

import javax.inject.Singleton;

import dagger.Component;
import fragments.AuthorListFragment;
import fragments.FavouriteListFragment;
import fragments.GenreListFragment;
import fragments.QuoteListFragment;

@Singleton

@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    void inject(GenreListFragment fragment);

    void inject(AuthorListFragment fragment);

    void inject(QuoteListFragment fragment);

    void inject(FavouriteListFragment fragment);
}