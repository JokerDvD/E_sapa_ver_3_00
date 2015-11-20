package com.example.admin.e_sapa_ver_3_00.MainActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.Fragments.alcohol;
import com.example.admin.e_sapa_ver_3_00.Fragments.history;
import com.example.admin.e_sapa_ver_3_00.Fragments.home;
import com.example.admin.e_sapa_ver_3_00.Fragments.settings;
import com.example.admin.e_sapa_ver_3_00.Fragments.tabacco;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Preferences.preferenceSave_Load;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.other.GPS;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.example.admin.e_sapa_ver_3_00.WTF;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.Locale;

public class Fragment_activity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Drawer drawer;
    private preferenceSave_Load pref;
    private SubActionButton btn1, btn2, btn3, btn4;
    private FloatingActionMenu actionMenu;
    private FloatingActionButton actionButton;
    private Fragment navigationFragment;
    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_activity);
        pref = new preferenceSave_Load(this);
        setToolbar();
        getLocationPoints();
        navigationDrawer();
        CircularFloatingActionMenu();
        setBackground();
        setLanguage();

    }

    private void setLanguage() {
        int page = pref.loadPageN(resourceFile.pageN_tag, 0);
        Locale locale = new Locale(pref.loadLangTag(resourceFile.lang_tag, "ru"));
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, null);
        if (page == 6) {

            drawer.setSelection(7);
            toolbar.setTitle(getResources().getString(R.string.set_info_2));
            pref.savePageNum(resourceFile.pageN_tag, 0);
        }
    }

    private void setBackground() {
        int theme_color = pref.loadThemeTag(resourceFile.theme_tag, R.color.theme_color_3);
        getWindow().getDecorView().setBackgroundResource(theme_color);
    }

    private void CircularFloatingActionMenu() {
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_action));

        actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        actionButton.setBottom(50);
        actionButton.setRight(50);
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageDrawable(getResources().getDrawable(R.drawable.ic_smoking));

        btn1 = new SubActionButton.Builder(this).setContentView(imageView1).build();

        ImageView imageView2 = new ImageView(this);
        imageView2.setImageDrawable(getResources().getDrawable(R.drawable.ic_martini));
        btn2 = new SubActionButton.Builder(this).setContentView(imageView2).build();

        ImageView imageView3 = new ImageView(this);
        imageView3.setImageDrawable(getResources().getDrawable(R.drawable.ic_settings));
        btn3 = new SubActionButton.Builder(this).setContentView(imageView3).build();


        ImageView imageView4 = new ImageView(this);
        imageView4.setImageDrawable(getResources().getDrawable(R.drawable.ic_history));
        btn4 = new SubActionButton.Builder(this).setContentView(imageView4).build();

        btn1.setOnClickListener(this);
        btn1.setId(R.id.alco_series_label);
        btn2.setOnClickListener(this);
        btn2.setId(R.id.alco_list_view);
        btn3.setOnClickListener(this);
        btn3.setId(R.id.alco_part1a);
        btn4.setOnClickListener(this);
        btn4.setId(R.id.alco_header);

        actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(btn1)
                .addSubActionView(btn2)
                .addSubActionView(btn3)
                .addSubActionView(btn4)
                .setRadius(150)
                .setRadius(150)
                .attachTo(actionButton)
                .build();

        actionMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {

            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {

            }
        });
    }

    private void navigationDrawer() {
        drawer = new DrawerBuilder().withActivity(this).withToolbar(toolbar).addDrawerItems(
                new SecondaryDrawerItem().withIcon(R.drawable.ic_home).withName(R.string.nav_home).setEnabled(true),
                new SectionDrawerItem().withName(R.string.nav_SectionDrawerItemValidate),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_smoking).withName(R.string.nav_smoking).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_martini).withName(R.string.nav_alcohol).setEnabled(true),
                new SectionDrawerItem().withName(R.string.nav_history),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_history).withName(R.string.nav_SectionDrawerItemHistory).setEnabled(true).withIdentifier(1),
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
                                toolbar.setTitle(((Nameable) drawerItem).getName());

                                if (position == 8) {
                                    finish();
                                }
                            }
                        }
                        if (position == position) {
                            Toast.makeText(getApplicationContext(), "Position " + position, Toast.LENGTH_LONG).show();
                            OpenPositionFragment(position);
                        }
                        return false;
                    }
                }).build();


        drawer.setSelection(0);
    }

    private void OpenPositionFragment(int position) {
        switch (position) {
            case 0:
                navigationFragment = new home();
                toolbar.setTitle(getResources().getString(R.string.home_title));
                toolbar.setSubtitle("");
                break;
            case 2:
                navigationFragment = new tabacco();
                toolbar.setTitle(getResources().getString(R.string.tabac_title));
                toolbar.setSubtitle(getResources().getString(R.string.tabac_textInfo_1));
                break;
            case 3:
                navigationFragment = new alcohol();
                toolbar.setTitle(getResources().getString(R.string.alco_title));
                toolbar.setSubtitle(getResources().getString(R.string.alco_text_info_1));
                break;
            case 5:
                navigationFragment = new history();
                toolbar.setTitle(getResources().getString(R.string.history_info_title));
                toolbar.setSubtitle("");
                break;
            case 7:
                navigationFragment = new settings();
                toolbar.setTitle(getResources().getString(R.string.set_info_3));
                toolbar.setSubtitle("");
                break;
        }

        if (navigationFragment != null) {
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.content_frame, navigationFragment).commit();
        }
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSelected(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.nav_home);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else if (actionMenu.isOpen()) {
            actionMenu.close(true);
        } else {
            if (count == 5) {
                count = 0;
                navigationFragment = new WTF();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.content_frame, navigationFragment).commit();
                Toast.makeText(this, "ну и ачем ты нажал это ?!", Toast.LENGTH_LONG).show();

            }
            count++;
        }
    }

    @Override
    public void onClick(View v) {
        int pos;
        switch (v.getId()) {
            case R.id.alco_series_label:
                pos = 2;
                break;
            case R.id.alco_list_view:
                pos = 3;
                break;
            case R.id.alco_part1a:
                pos = 7;
                break;
            case R.id.alco_header:
                pos = 5;
                break;
            default:
                pos = 0;
        }

        OpenPositionFragment(pos);
    }

    public void getLocationPoints() {
        GPS gps = new GPS(getApplicationContext());
        if (gps.canGetLocation()) {
            resourceFile.latitude = gps.getLatitude();
            resourceFile.longitude = gps.getLongitude();
        } else {
            gps.showSettingsAlert();
        }
    }
}
