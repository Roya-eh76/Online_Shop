package com.example.onlineshop.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.network.ProductRepository;
import com.example.onlineshop.viewmodel.ViewModelCategoryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriViewPagerFragment extends Fragment {
    public static final String ARGS_ID_CATEGORY = "ARGS_ID_CATEGORY";
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private ViewModelCategoryFragment mViewModelCategoryFragment;
    private List<CategoriesItem> mCategoriesItems = new ArrayList<>();
    private FragmentStateAdapter mAdapter;
    private int mId;
    private ProductRepository mProductRepository=ProductRepository.getInstance();

    public static CategoriViewPagerFragment newInstance(int mId) {

        Bundle args = new Bundle();
        args.putInt(ARGS_ID_CATEGORY, mId);
        CategoriViewPagerFragment fragment = new CategoriViewPagerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public CategoriViewPagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = getArguments().getInt(ARGS_ID_CATEGORY);
        mViewModelCategoryFragment = ViewModelProviders.of(this).get(ViewModelCategoryFragment.class);
        mViewModelCategoryFragment.getListMutableLiveData().observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                mCategoriesItems = categoriesItems;
                for (int i = 0; i < mCategoriesItems.size(); i++) {
                   // if(!mCategoriesItems.get(i).getName().equals("فروش ویژه"))
                    mTabLayout.addTab(mTabLayout.newTab().setText(mCategoriesItems.get(i).getName()));
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_categori_view_pager, container, false);
        view.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager2 = view.findViewById(R.id.view_pager);


        setUpTabLayout();


        return view;
    }

    public void setUpTabLayout() {

        mAdapter = new FragmentStateAdapter(this) {

            @Override
            public int getItemCount() {
                return mCategoriesItems.size();
            }

            @NonNull
            @Override
            public Fragment createFragment(int position) {
               // mProductRepository.getSubCategories(mCategoriesItems.get(position).getId()+"");
                return SubCategoryFragment.newInstance(mCategoriesItems.get(position).getId());
            }
        };

        mViewPager2.setAdapter(mAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager2, (tab, position) -> tab.setText(mCategoriesItems.get(position)
                .getName())).attach();

    }

}
