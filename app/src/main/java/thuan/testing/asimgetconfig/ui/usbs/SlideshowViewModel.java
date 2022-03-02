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


    public LiveData<String> getText() {
        return mText;
    }
}