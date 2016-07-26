package activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.imcodebased.quotesmate.R;

import java.util.ArrayList;
import java.util.List;

import adapters.TabFragmentPagerAdapter;
import fragments.AuthorListFragment;
import fragments.GenreListFragment;
import fragments.QuoteListFragment;


/**
 * Created by codebased on 22/07/16.
 */
public class TabMainActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewPager;

    private List<Fragment> fragmentList;
    private List<String> titles;
    private TabFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        this.initUI();
        this.prepareData();

        adapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, titles);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void prepareData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new QuoteListFragment());
        fragmentList.add(new AuthorListFragment());
        fragmentList.add(new GenreListFragment());

        titles = new ArrayList<>();
        titles.add("Random Quotes");
        titles.add("Authors");
        titles.add("Genre");
    }

    private void initUI() {

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

    }
}
