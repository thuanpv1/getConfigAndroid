package thuan.testing.asimgetconfig.ui.coms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lvrenyang.io.base.COMIO;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        String[] devicePaths = COMIO.enumPorts();
        String totalVals = "Danh sách các cổng com - thư viện com printer: \n";
        if (devicePaths != null) {
            for (int i = 0; i < devicePaths.length; ++i) {
                totalVals = totalVals  + devicePaths[i] + "\n";
            }
        }
        mText.setValue(totalVals);
    }

    public LiveData<String> getText() {
        return mText;
    }
}