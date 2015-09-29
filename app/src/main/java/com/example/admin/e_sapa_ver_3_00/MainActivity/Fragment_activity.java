package com.example.admin.e_sapa_ver_3_00.MainActivity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.admin.e_sapa_ver_3_00.FragmentsAdapter.MyAdapter;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.viewPagerAnimation.DepthPageTransformer;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class Fragment_activity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerBuilder builder;
    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_activity);
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyAdapter(this, getSupportFragmentManager()));
//        pager.setPageTransformer(true, new ZoomOutPageTransformer());
        pager.setPageTransformer(true, new DepthPageTransformer());
        setToolbar();
        navigationDrawer(pager);

    }

    private void navigationDrawer(final ViewPager pager) {
        builder = new DrawerBuilder().withActivity(this).withToolbar(toolbar).addDrawerItems(
                new SecondaryDrawerItem().withIcon(R.drawable.ic_home).withName(R.string.nav_home).setEnabled(true),
                new SectionDrawerItem().withName(R.string.nav_SectionDrawerItemValidate),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_qrcode).withName(R.string.nav_qrcode).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_barcode).withName(R.string.nav_brandcode).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_smoking).withName(R.string.nav_smoking).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_martini).withName(R.string.nav_alcohol).setEnabled(true),
                new SectionDrawerItem().withName(R.string.nav_SectionDrawerItemHistory),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_history).withName(R.string.nav_alcohol).setEnabled(true),
                new SectionDrawerItem().withName(R.string.action_settings),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_settings).withName(R.string.action_settings).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_exit_to_app).withName(R.string.nav_exit).setEnabled(true)
        )
                .withHeader(R.layout.header)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int position, long l, IDrawerItem drawerItem) {

                        if (drawerItem != null) {
                            if (drawerItem instanceof Nameable) {
                                toolbar.setTitle(((Nameable)
                                        drawerItem).getNameRes());
                                pager.setCurrentItem(position);
                            }
                        }
                        return false;
                    }
                })

        ;

        drawer = builder.build();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
