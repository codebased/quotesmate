package activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.imcodebased.quotesmate.R;

import java.util.ArrayList;
import java.util.List;

import adapters.TabFragmentPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.AuthorListFragment;
import fragments.FavouriteListFragment;
import fragments.GenreListFragment;
import fragments.QuoteListFragment;

public class TabMainActivity extends BaseActivity {

    @BindView(R.id.tabsView)
    protected TabLayout tabView;

    @BindView(R.id.viewPagerView)
    protected ViewPager viewPagerView;

    @BindView(R.id.toolbarView)
    protected Toolbar toolbarView;

    protected List<Fragment> mFragmentList;
    protected List<String> mTitleList;
    protected TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        ButterKnife.bind(this);

        toolbarView.setTitle(R.string.title_activity_tab_main);
        setSupportActionBar(toolbarView);

        prepareData();

        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);

        viewPagerView.setAdapter(mTabFragmentPagerAdapter);
        tabView.setupWithViewPager(viewPagerView);

        setTabIcons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void prepareData() {

        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        setFragmentData(new FavouriteListFragment(), R.string.title_tab_favourite);
        setFragmentData(new QuoteListFragment(), R.string.title_tab_random_quote);
        setFragmentData(new AuthorListFragment(), R.string.title_tab_authors);
        setFragmentData(new GenreListFragment(), R.string.title_tab_genres);
    }

    private void setTabIcons() {
        tabView.getTabAt(0).setIcon(R.drawable.ic_favourite);
        tabView.getTabAt(1).setIcon(R.drawable.ic_random);
        tabView.getTabAt(2).setIcon(R.drawable.ic_author);
        tabView.getTabAt(3).setIcon(R.drawable.ic_genre);
    }

    private void setFragmentData(Fragment fragment, int res) {
        mFragmentList.add(fragment);
        mTitleList.add(getString(res));
    }
}
