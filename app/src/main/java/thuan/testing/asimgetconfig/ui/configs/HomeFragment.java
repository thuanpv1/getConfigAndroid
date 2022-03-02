package thuan.testing.asimgetconfig.ui.configs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import thuan.testing.asimgetconfig.MainActivity;
import thuan.testing.asimgetconfig.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public TextView textView = null;
    public TextView textViewDeviceInfo = null;
    public TextView textMemInfo = null;
    public TextView textStorage = null;
    @Override
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.getMyData();
//        String memInfo = activity.        getCommandData("cat /proc/stat"); //cat /proc/stat
        String memInfo = activity.        getCommandData("cat /proc/meminfo"); //cat /proc/stat
        String storageInfo = activity.        getCommandData("df -h"); //cat /proc/stat

        textViewDeviceInfo.setText(myDataFromActivity);
        textMemInfo.setText(memInfo);
        textStorage.setText(storageInfo);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        MainActivity activity = (MainActivity) getActivity();
        String myDataFromActivity = activity.getMyData();
//        String memInfo = activity.        getCommandData("cat /proc/stat"); //cat /proc/stat
        String memInfo = activity.        getCommandData("cat /proc/meminfo"); //cat /proc/stat
        String storageInfo = activity.        getCommandData("df -h"); //cat /proc/stat

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        textViewDeviceInfo = root.findViewById(R.id.text_device_info);
        textMemInfo = root.findViewById(R.id.mem_info);
        textStorage = root.findViewById(R.id.storage_info);
        textViewDeviceInfo.setText(myDataFromActivity);
        textMemInfo.setText(memInfo);
        textStorage.setText(storageInfo);


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