
package com.accounting.easyaccountant;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accounting.easyaccountant.fragments.BillFragment;
import com.accounting.easyaccountant.fragments.DashboardFragment;
import com.accounting.easyaccountant.fragments.ReportFragment;
import com.accounting.easyaccountant.fragments.SalesFragment;
import com.accounting.easyaccountant.objects.Contact;
import com.accounting.easyaccountant.objects.Item;
import com.accounting.easyaccountant.objects.Payment;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static FloatingActionMenu menuRed;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
//    private FloatingActionButton fab3;
    private List<FloatingActionMenu> menus = new ArrayList<>();
    static  Context context ;
    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        DatabaseHandler  databaseHandler = new DatabaseHandler(getApplicationContext());

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);
        menuRed = (FloatingActionMenu) findViewById(R.id.menu_red);
        menuRed.setVisibility(View.VISIBLE);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
//        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        menuRed.setClosedOnTouchOutside(true);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = DashboardFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = SalesFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = SalesFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = ReportFragment.newInstance();
                                break;
                            case R.id.action_item5:
                                selectedFragment = ReportFragment.newInstance();
                                break;

                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, DashboardFragment.newInstance());
        transaction.commit();


        menus.add(menuRed);


        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
//        fab3.setOnClickListener(clickListener);

        int delay = 400;
        for (final FloatingActionMenu menu : menus) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, delay + 150);

        menuRed.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuRed.isOpened()) {
                    Toast.makeText(getApplicationContext(), menuRed.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }

                menuRed.toggle(true);
            }
        });

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab1:
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, BillFragment.newInstance());
                    transaction.commit();
                    menuRed.close(true);
                    menuRed.setVisibility(View.GONE);
                    break;
                case R.id.fab2:

//                    fab2.setVisibility(View.VISIBLE);
                    FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                    transaction1.replace(R.id.frame_layout, BillFragment.newInstance());
                    transaction1.commit();
                    menuRed.setVisibility(View.GONE);
                    menuRed.close(true);

                    break;
//                case R.id.fab3:
////                    fab2.setVisibility(View.VISIBLE);
//                    Toast.makeText(getApplicationContext(),"gggggggggg",Toast.LENGTH_SHORT).show();
//
//                    break;
            }
        }
    };










}
