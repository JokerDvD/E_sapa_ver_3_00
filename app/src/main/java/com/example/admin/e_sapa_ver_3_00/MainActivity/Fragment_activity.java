package com.example.admin.e_sapa_ver_3_00.MainActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.BarcodeActivity.scanner_actv;
import com.example.admin.e_sapa_ver_3_00.Fragments.alcohol;
import com.example.admin.e_sapa_ver_3_00.Fragments.history;
import com.example.admin.e_sapa_ver_3_00.Fragments.home;
import com.example.admin.e_sapa_ver_3_00.Fragments.settings;
import com.example.admin.e_sapa_ver_3_00.Fragments.tabacco;
import com.example.admin.e_sapa_ver_3_00.FragmentsAdapter.MyAdapter;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Preferences.preferenceSave_Load;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.example.admin.e_sapa_ver_3_00.viewPagerAnimation.ZoomOutPageTransformer;
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
    private ViewPager viewPager;
    private SubActionButton btn1, btn2, btn3, btn4, btn5;
    private SubActionButton btn6, btn7;
    private FloatingActionMenu actionMenu;
    private FloatingActionButton actionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_activity);
        pref = new preferenceSave_Load(this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        setToolbar();
        viewPager.setAdapter(new MyAdapter(this, getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setCurrentPageTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setCurrentItem(0);
        navigationDrawer(viewPager);
        CircularFloatingActionMenu();
        setBackground();
        setLanguage();

    }

    private void setLanguage() {
        pref.loadLangTag(resourceFile.lang_tag, "ru");
        int page = pref.loadPageN(resourceFile.pageN_tag, 0);

        if (page == 6) {
            Locale locale = new Locale(pref.loadLangTag(resourceFile.lang_tag, "ru"));
            Locale.setDefault(locale);
            Configuration configuration = new Configuration();
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration, null);

            pref.savePageNum(resourceFile.pageN_tag, 0);
            viewPager.setCurrentItem(page);
        }
    }

    private void setBackground() {
        int theme_color = pref.loadThemeTag(resourceFile.theme_tag, R.color.theme_color_3);
            getWindow().getDecorView().setBackgroundResource(theme_color);
            actionButton.setBackgroundColor(theme_color);
    }

    private void CircularFloatingActionMenu() {
        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_barcode));

        actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();

        actionButton.setBottom(50);
        actionButton.setRight(50);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

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
        imageView4.setImageDrawable(getResources().getDrawable(R.drawable.ic_barcode));
        btn4 = new SubActionButton.Builder(this).setContentView(imageView4).build();

       /* ImageView imageView5 = new ImageView(this);
        imageView5.setImageDrawable(getResources().getDrawable(R.drawable.ic_settings));
        btn5 = new SubActionButton.Builder(this).setContentView(imageView5).build();*/


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
                .addSubActionView(btn4)/*
                .addSubActionView(btn5)*/
//                .setAnimationHandler(new DefaultAnimationHandler())
                .setRadius(150)
                .setRadius(150)
//                .setStartAngle(0)
//                .setEndAngle(90)
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

    private void setCurrentPageTitle(int position) {
        String toolbarString;
        switch (position) {
            case 0:
                toolbarString = home.getTitle(getBaseContext(), position);
                break;
            /*case 1:
                toolbarString = qrcode.getTitle(getBaseContext(), position);
                break;
            case 2:
                toolbarString = (barcode.getTitle(getBaseContext(), position));
                break;*/
            case 1:
                toolbarString = (tabacco.getTitle(getBaseContext(), position));
                break;
            case 2:
                toolbarString = alcohol.getTitle(getBaseContext(), position);
                break;
            case 3:
                toolbarString = history.getTitle(getBaseContext(), position);
                break;
            case 4:
                toolbarString = settings.getTitle(getBaseContext(), position);
                break;
            default:
                toolbarString = settings.getTitle(getBaseContext(), position);
                break;
        }
        toolbar.setTitle(toolbarString);
    }

    private void navigationDrawer(final ViewPager pager) {
        drawer = new DrawerBuilder().withActivity(this).withToolbar(toolbar).addDrawerItems(
                new SecondaryDrawerItem().withIcon(R.drawable.ic_home).withName(R.string.nav_home).setEnabled(true),
                new SectionDrawerItem().withName(R.string.nav_SectionDrawerItemValidate),
                /*new SecondaryDrawerItem().withIcon(R.drawable.ic_qrcode).withName(R.string.nav_qrcode).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_barcode).withName(R.string.nav_brandcode).setEnabled(true),*/
                new SecondaryDrawerItem().withIcon(R.drawable.ic_smoking).withName(R.string.nav_smoking).setEnabled(true),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_martini).withName(R.string.nav_alcohol).setEnabled(true),
                new SectionDrawerItem().withName(R.string.nav_SectionDrawerItemHistory),
                new SecondaryDrawerItem().withIcon(R.drawable.ic_history).withName(R.string.nav_history).setEnabled(true).withIdentifier(1),
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
                                toolbar.setTitle(((Nameable) drawerItem).getNameRes());
                                if (position == 8) {
                                    finish();
                                } else {
                                    setCurrentFragment(position, pager);
                                }
                                int content = drawer.getDrawerItems().get(position).getIdentifier();
                               /* if (content == 1) {
                                    Toast.makeText(Fragment_activity.this, "Content " + content, Toast.LENGTH_LONG).show();
                                }*/
                            }

//                            Toast.makeText(Fragment_activity.this, " Фрагмент " + position, Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }
                }).build();
        drawer.setSelection(0);
    }

    private void setCurrentFragment(int position, ViewPager pager) {
        int page;
        switch (position) {
            case 0:
                page = 0;
                break;
           /* case 2:
                page = 1;
                break;
            case 3:
                page = 2;
                break;*/
            case 2:
                page = 1;
                break;
            case 3:
                page = 2;
                break;
            case 5:
                page = 3;
                break;
            case 7:
                page = 4;
                break;
            default:
                Toast.makeText(Fragment_activity.this, "Position " + position, Toast.LENGTH_LONG).show();
                page = 0;
                break;
        }
        pager.setCurrentItem(page);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(R.string.nav_home);
    }

    /*@Override
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
    }*/

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else if (actionMenu.isOpen()) {
            actionMenu.close(true);
        } else {
//            super.onBackPressed();
            Toast.makeText(this, "Зачем ты жмешь это?!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alco_series_label:
                viewPager.setCurrentItem(1);
                break;
            case R.id.alco_list_view:
                viewPager.setCurrentItem(2);
                break;
            case R.id.alco_part1a:
                viewPager.setCurrentItem(4);
                break;
            case R.id.alco_header:
                Intent intent_barcode = new Intent(this, scanner_actv.class);
                startActivity(intent_barcode);
                break;
        }
    }
}
