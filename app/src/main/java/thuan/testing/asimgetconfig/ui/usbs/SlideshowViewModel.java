package thuan.testing.asimgetconfig.ui.usbs;

import android.content.Context;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import asim.sdk.locker.DeviceInfo;
import asim.sdk.locker.SDKLocker;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
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

    public LiveData<String> getText() {
        return mText;
    }
}