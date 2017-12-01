package yjs.gd.com.gdandroidsample.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yjs.gd.com.gdandroidsample.R;

/**
 * Created by jesson_yi on 2017/12/1.
 */

public class TabPagerFragment extends Fragment {
    public static final String AUDIT = "audit";
    public static final String ALL = "all";
    private TabHost mTabHost;
    private ViewPager mPager;
    private MyPagerAdapter mPagerAdapter;
    private List<Fragment> mLists = new ArrayList<Fragment>();
    View mAuditTab,mAllTab;
    View[] mTabs = new View[]{mAuditTab,mAllTab};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLists.add(getFragment(AUDIT));
        mLists.add(getFragment(ALL));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_common_pager_tabhost,
                container, false);
        mPager = (ViewPager) rootView.findViewById(R.id.common_tab_pager);
        mPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(mPagerAdapter);
        mTabHost = (TabHost) rootView.findViewById(R.id.tabhost);
        mTabHost.setup();
        mTabHost.clearAllTabs();
        mTabHost.setOnTabChangedListener(mPagerAdapter);
        int pageCount = mPagerAdapter.getCount();
        for (int i = 0; i < pageCount; i++) {
            mTabs[i] =  LayoutInflater.from(getActivity()).inflate(R.layout.tab_top_indicator, null);
            TextView textView = (TextView) mTabs[i].findViewById(R.id.tab_label);
            String pageTitle = (String) mPagerAdapter.getPageTitle(i);
            textView.setText(pageTitle);

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(pageTitle);
            tabSpec.setIndicator(mTabs[i]);
            tabSpec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    View dummyView = new View(getActivity());
                    dummyView.setMinimumHeight(0);
                    dummyView.setMinimumWidth(0);
                    return dummyView;
                }
            });
            mTabHost.addTab(tabSpec);
        }

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();

    }
    private Fragment getFragment(String  type) {
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putString("user_type",type);
        fragment.setArguments(args);
        return fragment;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter
            implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public void onTabChanged(String tabId) {
            int position = mTabHost.getCurrentTab();

            for (int i =0; i< mTabHost.getTabWidget().getTabCount(); i ++){
                boolean bl = (position==i)? true: false;
                mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.detail_point).setVisibility(bl? View.VISIBLE:View.GONE);
            }
            mPager.setCurrentItem(position);
        }
        @Override
        public Fragment getItem(int position) {
            return mLists.get(position);
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public String  getPageTitle(int position) {
            if(position==0){
                return "用户申请";
            }else{
                return "全部用户";
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            TabWidget tabWidget = mTabHost.getTabWidget();
            int oldFocusability = tabWidget.getDescendantFocusability();
            tabWidget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            mTabHost.setCurrentTab(position);
            tabWidget.setDescendantFocusability(oldFocusability);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
            }
        }
    }
}
