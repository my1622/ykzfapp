package whzl.com.ykzfapp.mvp.ui.activity;

import android.os.Bundle;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import whzl.com.ykzfapp.R;

/**
 * Created by my on 2017/11/8.
 */

public class VideoPlayActivity extends BaseActivity{

    String videoPath;
    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_video_play;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        try {
            videoPath = getIntent().getStringExtra("video_path");
            initVideo(videoPath);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void initVideo(String  videoPath) {

//        JCVideoPlayerStandard player = (JCVideoPlayerStandard) findViewById(R.id.player_video);
        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class,
                videoPath,"");





    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
