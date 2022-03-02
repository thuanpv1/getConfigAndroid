package thuan.testing.asimgetconfig.ui.coms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lvrenyang.io.base.COMIO;

import asim.sdk.printer.SDKPrints;
import thuan.testing.asimgetconfig.R;

public class GalleryFragment extends Fragment {

    public TextView textView = null;

    @Override
    public void onResume() {
        super.onResume();
        textView.setText(getComList());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        textView = root.findViewById(R.id.text_gallery);
        textView.setText(getComList());

        Button fab = root.findViewById(R.id.refreshGellary);
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

    public String getComList() {
        String[] devicePaths = COMIO.enumPorts();
        String totalVals = "Danh sách các cổng com - thư viện com printer: \n";
        if (devicePaths != null) {
            for (int i = 0; i < devicePaths.length; ++i) {
                totalVals = totalVals  + devicePaths[i] + "\n";
            }
        }
        return  totalVals;
    }
}