package com.bawie.chenzhiqiang.shopjdcart.view.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import com.bawie.chenzhiqiang.shopjdcart.R;
import com.bawie.chenzhiqiang.shopjdcart.view.fragment.FindFragment;
import com.bawie.chenzhiqiang.shopjdcart.view.fragment.GroupFragment;
import com.bawie.chenzhiqiang.shopjdcart.view.fragment.HomeFragment;
import com.bawie.chenzhiqiang.shopjdcart.view.fragment.MyFragment;
import com.bawie.chenzhiqiang.shopjdcart.view.fragment.ShopFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*https://blog.csdn.net/zhang___yong/article/details/62050580
 * https://www.jianshu.com/p/c94584b72635
 * http://www.jb51.net/article/84855.htm
 *
 * 阿里矢量图库
 * */

public class MainActivity extends FragmentActivity {
    private List<Fragment> fragmentList;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.myCoordinator)
    CoordinatorLayout myCoordinator;
    private BottomBar mbottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        ButterKnife.bind (this);

        initViewPage ();

        creatBottomBar (savedInstanceState);
    }

    private void creatBottomBar(Bundle savedInstanceState) {
        mbottomBar = BottomBar.attachShy (myCoordinator,viewPager,savedInstanceState);

        mbottomBar.setItemsFromMenu (R.menu.bottombar_tabs, new OnMenuTabClickListener () {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                switch (menuItemId){
                    case R.id.tab_home:
                        viewPager.setCurrentItem (0);
                        break;
                    case R.id.tab_find:
                        viewPager.setCurrentItem (1);
                        break;
                    case R.id.tab_group:
                        viewPager.setCurrentItem (2);
                        break;
                    case R.id.tab_shop:
                        viewPager.setCurrentItem (3);
                        break;
                    case R.id.tab_my:
                        viewPager.setCurrentItem (4);
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(int menuItemId) {

            }
        });

        mbottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        mbottomBar.mapColorForTab(1, 0xFF5D4037);
        mbottomBar.mapColorForTab(2, "#7B1FA2");
        mbottomBar.mapColorForTab(3, "#FF5252");
        mbottomBar.mapColorForTab(4, "#FF9800");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
// Necessary to restore the BottomBar's state, otherwise we would
// lose the current tab on orientation change.
        mbottomBar.onSaveInstanceState(outState);
    }

    private void initViewPage() {
        fragmentList = new ArrayList<> ();
        fragmentList.add(new HomeFragment ());
        fragmentList.add(new FindFragment ());
        fragmentList.add(new GroupFragment ());
        fragmentList.add(new ShopFragment ());
        fragmentList.add(new MyFragment ());
        viewPager.setAdapter(new FragmentStatePagerAdapter (getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                mbottomBar.selectTabAtPosition(position, true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
