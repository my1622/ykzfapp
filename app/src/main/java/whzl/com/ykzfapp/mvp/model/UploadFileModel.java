package whzl.com.ykzfapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import whzl.com.ykzfapp.bean.BaseEntity;
import whzl.com.ykzfapp.mvp.contract.UploadFileContract;
import whzl.com.ykzfapp.mvp.model.api.service.CommonService;


@ActivityScope
public class UploadFileModel extends BaseModel implements UploadFileContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public UploadFileModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseEntity<String>> upLoadFiles(List<LocalMedia> selectList) {

        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i <selectList.size() ; i++) {
            File f=new File(selectList.get(i).getPath());
            if (f!=null) {
                builder.addFormDataPart("uploadFile", f.getName(), RequestBody.create(MediaType.parse(selectList.get(i).getPictureType()), f));
            }
        }
        MultipartBody  myfiles = builder.build();
        return mRepositoryManager
                .obtainRetrofitService(CommonService.class)
                .upLoadFiles(myfiles);
    }
}