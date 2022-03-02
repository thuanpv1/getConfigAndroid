package thuan.testing.asimgetconfig;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.lvrenyang.io.base.COMIO;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import asim.sdk.locker.DeviceInfo;
import asim.sdk.locker.SDKLocker;
import asim.sdk.printer.SDKPrints;
import asim.sdk.sdksimdispenser.SimdispenserMain;
import thuan.testing.asimgetconfig.ui.configs.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public String getCommandData(String cmd) {
        if (cmd == null) cmd = "ps";

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String test = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            test = bufferedReader.lines().collect(Collectors.joining("\n"));
        }
        return test;
    }
    public String getMyData() {

        // Declaring and Initializing the ActivityManager
        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        // Declaring MemoryInfo object
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        // Fetching the data from the ActivityManager
        actManager.getMemoryInfo(memInfo);

        // Fetching the available and total memory and converting into Giga Bytes
        double availMemory = (double) memInfo.availMem/(1024*1024*1024);
        double totalMemory= (double) memInfo.totalMem/(1024*1024*1024);
        double percentUsed = 100 * (totalMemory - availMemory)/totalMemory;
        boolean lowMemory = memInfo.lowMemory;
        Long threshold = memInfo.threshold/(1024*1024*1024);

        // Displaying the memory info into the TextView
        String str1 = ("Available RAM: " + String.valueOf(availMemory)
                + "\nTotal RAM: " + String.valueOf(totalMemory)
                + "\nPercentage used: " + String.valueOf(percentUsed) + "%"
        );
        String str2 = ("Lowmemory ==" + String.valueOf(lowMemory) + "\n threshold == " + String.valueOf(threshold));
        return str1 + "\n" + str2;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}