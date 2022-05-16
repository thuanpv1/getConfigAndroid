package thuan.testing.asimgetconfig.ui.configs;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import thuan.testing.asimgetconfig.MainActivity;
import thuan.testing.asimgetconfig.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public TextView textView = null;
    public TextView textViewDeviceInfo = null;
    public TextView textMemInfo = null;
    public TextView textStorage = null;
    public TextView textServices = null;
    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.getMyData();
//        String memInfo = activity.        getCommandData("cat /proc/stat"); //cat /proc/stat
        String memInfo = activity.        getCommandData("cat /proc/meminfo"); //cat /proc/stat
        String storageInfo = activity.        getCommandData("df -h"); //cat /proc/stat
        String allservice = activity.        getCommandData("ps"); //cat /proc/stat
//        String test = activity.getCommandData("am stack list");
//        Log.d("Testing command", activity.getCommandData("stack list"));

        textViewDeviceInfo.setText("Device Information \n" + myDataFromActivity);
        textMemInfo.setText("Memory Information \n" + memInfo);
        textStorage.setText("Storage Information \n" + storageInfo);
        textServices.setText("Services Information \n" + allservice);

//        switchTask();



    }
    void switchTask()
    {
        Log.d("runingSwitchTask", "Running to switch tasks");
        int tid;
        ActivityManager am;
        am = (ActivityManager)getContext().getSystemService( Context.ACTIVITY_SERVICE );
        List<ActivityManager.AppTask> tasks = am.getAppTasks();
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList =  am.getRunningTasks(3);
        for (int index = 0; index < tasks.size(); index++) {
            Log.d("hihi", String.valueOf(tasks.get(index).getTaskInfo().description));
            Log.d("hihi", String.valueOf(tasks.get(index).getTaskInfo().id));
            Log.d("hihi", String.valueOf(tasks.get(index).getTaskInfo().persistentId));
            Log.d("hihi", String.valueOf(tasks.get(index).getTaskInfo().affiliatedTaskId));
        }

        tid = 2469;  // read task id of *other* app from file
        am.moveTaskToFront( tid, 0, null );
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        textViewDeviceInfo = root.findViewById(R.id.text_device_info);
        textMemInfo = root.findViewById(R.id.mem_info);
        textStorage = root.findViewById(R.id.storage_info);
        textServices = root.findViewById(R.id.servicesInfo);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button fab = root.findViewById(R.id.refreshHome);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refresh done !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    onResume();

            }
        });

        return root;
    }
}