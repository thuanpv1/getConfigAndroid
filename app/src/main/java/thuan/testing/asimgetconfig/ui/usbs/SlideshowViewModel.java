package thuan.testing.asimgetconfig.ui.usbs;

import android.content.Context;

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
        List<DeviceInfo> devices2 = SDKLocker.getListUsbDevices(context);
        String result = "Danh sách các cổng usb - thư viện SDKLocker \n";
        for (DeviceInfo devi: devices2) {
            result += devi.device.getDeviceName()  + ", productId=" + devi.device.getProductId() + ", vendorId=" + devi.device.getVendorId()+ ", having driver: " + (devi.driver != null ? "yes" : "no") + "\n";
        }

        return result;
    }

    public LiveData<String> getText() {
        return mText;
    }
}