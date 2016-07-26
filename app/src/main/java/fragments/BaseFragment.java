package fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imcodebased.quotesmate.R;

import java.util.List;

import adapters.CustomRecyclerAdapter;
import customviews.CustomRecyclerView;
import model.services.IQuotesDataLoader;
import model.services.json.JsonQuotesDataLoader;
import model.services.json.provider.RawJsonProvider;

public abstract class BaseFragment<T> extends Fragment {


    protected CustomRecyclerView listView;

    protected List<T> quotes;

    protected CustomRecyclerAdapter<T> adapter;

    protected IQuotesDataLoader quotesDataLoader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), null);
    }

    public abstract int getLayout();

    public abstract void initializeData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        quotesDataLoader = new JsonQuotesDataLoader(view.getContext(), new RawJsonProvider());
        listView = (CustomRecyclerView) view.findViewById(R.id.listView);
        listView.setEmtpyStateView(view.findViewById(R.id.empty));
    }

    @Override
    public void onResume() {
        super.onResume();
        initializeData();
    }


}
