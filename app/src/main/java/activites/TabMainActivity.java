package activites;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.imcodebased.quotesmate.R;

import java.util.ArrayList;
import java.util.List;

import adapters.TabFragmentPagerAdapter;
import butterknife.BindView;
import fragments.AuthorListFragment;
import fragments.FavouriteListFragment;
import fragments.GenreListFragment;
import fragments.QuoteListFragment;

public class TabMainActivity extends BaseToolbarActivity {

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
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.toolbarView.setTitle(R.string.title_activity_tab_main);
        this.populateFragment();
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);

        viewPagerView.setAdapter(mTabFragmentPagerAdapter);
        tabView.setupWithViewPager(viewPagerView);
        this.setTabIcons();
        this.setTabListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void populateFragment() {

        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        setFragmentData(new FavouriteListFragment(), R.string.title_tab_favourite);
        setFragmentData(new QuoteListFragment(), R.string.title_tab_random_quote);
        setFragmentData(new AuthorListFragment(), R.string.title_tab_authors);
        setFragmentData(new GenreListFragment(), R.string.title_tab_genres);
    }

    private void setFragmentData(Fragment fragment, int res) {
        mFragmentList.add(fragment);
        mTitleList.add(getString(res));
    }


    private void setTabIcons() {
        tabView.getTabAt(0).setIcon(R.drawable.ic_favourite);
        tabView.getTabAt(1).setIcon(R.drawable.ic_random);
        tabView.getTabAt(2).setIcon(R.drawable.ic_author);
        tabView.getTabAt(3).setIcon(R.drawable.ic_genre);
    }

    private void setTabListener() {

        int tabIconColor = ContextCompat.getColor(TabMainActivity.this, R.color.selectedTabColor);
        tabView.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabView.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPagerView) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(TabMainActivity.this, R.color.selectedTabColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(TabMainActivity.this, R.color.tabColor);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

}
