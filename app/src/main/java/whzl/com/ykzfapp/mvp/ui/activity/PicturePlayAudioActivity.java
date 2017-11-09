package whzl.com.ykzfapp.mvp.ui.activity;

/**
 * Created by my on 2017/11/9.
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.luck.picture.lib.PictureBaseActivity;
import com.luck.picture.lib.R.id;
import com.luck.picture.lib.R.layout;
import com.luck.picture.lib.R.string;
import com.luck.picture.lib.tools.DateUtils;

import whzl.com.ykzfapp.utils.ToastUtil;

public class PicturePlayAudioActivity extends PictureBaseActivity implements OnClickListener {
    private String audio_path;
    private MediaPlayer mediaPlayer;
    private SeekBar musicSeekBar;
    private boolean isPlayAudio = false;
    private TextView tv_PlayPause;
    private TextView tv_Stop;
    private TextView tv_Quit;
    private TextView tv_musicStatus;
    private TextView tv_musicTotal;
    private TextView tv_musicTime;
    public Handler handler = new Handler();
    public Runnable runnable = () -> {
        try {
            if(PicturePlayAudioActivity.this.mediaPlayer != null) {
                PicturePlayAudioActivity.this.tv_musicTime.setText(DateUtils.timeParse((long)PicturePlayAudioActivity.this.mediaPlayer.getCurrentPosition()));
                PicturePlayAudioActivity.this.musicSeekBar.setProgress(PicturePlayAudioActivity.this.mediaPlayer.getCurrentPosition());
                PicturePlayAudioActivity.this.musicSeekBar.setMax(PicturePlayAudioActivity.this.mediaPlayer.getDuration());
                PicturePlayAudioActivity.this.tv_musicTotal.setText(DateUtils.timeParse((long)PicturePlayAudioActivity.this.mediaPlayer.getDuration()));
                PicturePlayAudioActivity.this.handler.postDelayed(PicturePlayAudioActivity.this.runnable, 200L);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    };
    private boolean isError=false;

    public PicturePlayAudioActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(1024, 1024);
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_picture_play_audio);
        this.audio_path = this.getIntent().getStringExtra("audio_path");
        this.tv_musicStatus = (TextView)this.findViewById(id.tv_musicStatus);
        this.tv_musicTime = (TextView)this.findViewById(id.tv_musicTime);
        this.musicSeekBar = (SeekBar)this.findViewById(id.musicSeekBar);
        this.tv_musicTotal = (TextView)this.findViewById(id.tv_musicTotal);
        this.tv_PlayPause = (TextView)this.findViewById(id.tv_PlayPause);
        this.tv_Stop = (TextView)this.findViewById(id.tv_Stop);
        this.tv_Quit = (TextView)this.findViewById(id.tv_Quit);
        this.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                PicturePlayAudioActivity.this.initPlayer(PicturePlayAudioActivity.this.audio_path);
            }
        }, 30L);
        this.tv_PlayPause.setOnClickListener(this);
        this.tv_Stop.setOnClickListener(this);
        this.tv_Quit.setOnClickListener(this);
        this.musicSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    PicturePlayAudioActivity.this.mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void initPlayer(String path) {
        this.mediaPlayer = new MediaPlayer();

        try {
            this.mediaPlayer.setDataSource(path);
            //this.mediaPlayer.prepare();

            this.mediaPlayer.prepareAsync();
            this.mediaPlayer.setOnPreparedListener(mp -> {
                // 装载完毕 开始播放流媒体
                this.mediaPlayer.setLooping(true);
                this.playAudio();

            });

            this.mediaPlayer.setOnCompletionListener(mp -> isPlayAudio = false);
            this.mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                isError = true;
                ToastUtil.show(this,"请重新上传录音！");
                return false;
            });

        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == id.tv_PlayPause) {
            this.playAudio();
        }

        if(i == id.tv_Stop) {
            this.tv_musicStatus.setText(this.getString(string.picture_stop_audio));
            this.tv_PlayPause.setText(this.getString(string.picture_play_audio));
            this.stop(this.audio_path);
        }

        if(i == id.tv_Quit) {
            this.handler.removeCallbacks(this.runnable);
            (new Handler()).postDelayed(() -> PicturePlayAudioActivity.this.stop(PicturePlayAudioActivity.this.audio_path), 30L);

            try {
                this.closeActivity();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

    }

    private void playAudio() {
        if(this.mediaPlayer != null) {
            this.musicSeekBar.setProgress(this.mediaPlayer.getCurrentPosition());
            this.musicSeekBar.setMax(this.mediaPlayer.getDuration());
        }

        String ppStr = this.tv_PlayPause.getText().toString();
        if(ppStr.equals(this.getString(string.picture_play_audio))) {
            this.tv_PlayPause.setText(this.getString(string.picture_pause_audio));
            this.tv_musicStatus.setText(this.getString(string.picture_play_audio));
            this.playOrPause();
        } else {
            this.tv_PlayPause.setText(this.getString(string.picture_play_audio));
            this.tv_musicStatus.setText(this.getString(string.picture_pause_audio));
            this.playOrPause();
        }

        if(!this.isPlayAudio&& !isError) {
            this.handler.post(this.runnable);
            this.isPlayAudio = true;
        }

    }

    public void stop(String path) {
        if(this.mediaPlayer != null) {
            try {
                this.mediaPlayer.stop();
                this.mediaPlayer.reset();
                this.mediaPlayer.setDataSource(path);
//                this.mediaPlayer.prepare();
                this.mediaPlayer.prepareAsync();
                this.mediaPlayer.setOnPreparedListener(mp -> {
                    // 装载完毕 开始播放流媒体
                    this.mediaPlayer.setLooping(true);


                });

                this.mediaPlayer.setOnCompletionListener(mp -> isPlayAudio = false);
                this.mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                    isError = true;
//                    ToastUtil.show(this,"请重新上传录音！");
                    return false;
                });
                this.mediaPlayer.seekTo(0);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    public void playOrPause() {
        try {
            if(this.mediaPlayer != null) {
                if(this.mediaPlayer.isPlaying()) {
                    this.mediaPlayer.pause();
                } else {
                    this.mediaPlayer.start();
                }
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.closeActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.mediaPlayer != null && this.handler != null) {
            this.handler.removeCallbacks(this.runnable);
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }

    }
}

