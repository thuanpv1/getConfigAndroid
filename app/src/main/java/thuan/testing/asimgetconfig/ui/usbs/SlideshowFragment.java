package thuan.testing.asimgetconfig.ui.usbs;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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

import java.util.List;

import asim.sdk.locker.DeviceInfo;
import asim.sdk.locker.SDKLocker;
import thuan.testing.asimgetconfig.R;

public class SlideshowFragment extends Fragment {

    public TextView textView = null;
    @Override
    public void onResume() {
        super.onResume();
        textView.setText(getConfigs(getContext()));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        textView = root.findViewById(R.id.text_slideshow);
        textView.setText(getConfigs(getContext()));


        Button fab = root.findViewById(R.id.refreshSlide);
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
    public String getConfigs(Context context) {
        List<DeviceInfo> devices2 = SDKLocker.getAllUsbDevices(context);
        String result = "Danh sách các cổng usb - thư viện SDKLocker: usb-to-com \n";
        for (DeviceInfo devi: devices2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    result += "getDeviceName=" + devi.device.getDeviceName()
                            + "| getProductName=" + devi.device.getProductName()
                            + "| getSerialNumber=" + devi.device.getSerialNumber()
                            + "| getDeviceId=" + devi.device.getDeviceId()
                            + "| getVersion=" + devi.device.getVersion()
                            + "| getDeviceProtocol=" + devi.device.getDeviceProtocol()
                            + "| getManufacturerName=" + devi.device.getManufacturerName()
                            + "| productId=" + devi.device.getProductId()
                            + "| vendorId=" + devi.device.getVendorId()
                            + "| having usb-to-com driver: " + (devi.driver != null ? "yes" : "no") + "\n\n\n\n";
                }
            }
        }

        return result;
    }
}