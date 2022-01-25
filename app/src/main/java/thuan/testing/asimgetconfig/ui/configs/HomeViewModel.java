package thuan.testing.asimgetconfig.ui.configs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        String version = System.getProperty("os.version"); // OS version
        String versionsdk = android.os.Build.VERSION.SDK ;     // API Level
        String device = android.os.Build.DEVICE   ;        // Device
        String model = android.os.Build.MODEL  ;          // Model
        String product = android.os.Build.PRODUCT    ;      // Product

        mText.setValue("os version: " + version + ", "
                + "\nversionSDK: " + versionsdk + ", "
                + "\ndevice: " + device + ", "
                + "\nmodel =" + model + ", "
                + "\nproduct = " + product
        );
    }

    public LiveData<String> getText() {
        return mText;
    }
}