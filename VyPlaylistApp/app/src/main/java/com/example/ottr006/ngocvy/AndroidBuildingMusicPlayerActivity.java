package com.example.ottr006.ngocvy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AndroidBuildingMusicPlayerActivity extends Activity implements OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    private ImageView btnPlay, btnForward, btnBackward, btnNext, btnPrevious, btnPlaylist,btnRepeat,btnShuffle;
    SeekBar  songProgressBar;
    private TextView songCurrentDurationLabel,songTitleLabel;
    private TextView songTotalDurationLabel;
    // Trình chạy Nhạc
    private  MediaPlayer mp;
    // Thời gian nhạc, thanh trạng thái,...
    private Handler mHandler = new Handler();;
    private SongsManager plm;
    private Utilities utils;
    private int seekForwardTime = 5000; // Đơn vị thời gian
    private int seekBackwardTime = 5000; // Đơn vị thời gian
    private int currentSongIndex = 0;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);

        // Các nút chơi nhạc
        btnPlay = (ImageView) findViewById(R.id.btnPlay);
        btnForward = (ImageView) findViewById(R.id.btnForward);
        btnBackward = (ImageView) findViewById(R.id.btnBackward);
        btnNext = (ImageView) findViewById(R.id.btnNext);
        btnPrevious = (ImageView) findViewById(R.id.btnPrevious);
        btnPlaylist = (ImageView) findViewById(R.id.btnPlaylist);
        btnRepeat = (ImageView) findViewById(R.id.btnRepeat);
        btnShuffle = (ImageView) findViewById(R.id.btnShuffle);
        songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);
        songTitleLabel = (TextView) findViewById(R.id.songTitle);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);

        // Mediaplayer
        mp = new MediaPlayer();
        plm = new SongsManager(AndroidBuildingMusicPlayerActivity.this);
        utils = new Utilities();

        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // không thể không có
        mp.setOnCompletionListener(this); // không thể không có


        // Lấy nhạc từ thẻ SD
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                // dành cho các thiết bị >= 23 API
                this.songsList = plm.getPlayList();
            } else {
                requestPermission();
            }
        } else {

            // dành cho các thiết bị < 23 API
            this.songsList = plm.getPlayList();
        }


        // Tự động chạy bài nhạc đầu tiên
        playSong(0);

        /**
         * tạo sự kiện cho nút chạy nhạc
         * */
        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Kiểm tra nhạc đã được chơi
                if(mp.isPlaying()){
                    if(mp!=null){
                        mp.pause();
                        // Đổi hình ảnh nút chạy nhạc khi nhạc chạy
                        btnPlay.setImageResource(R.drawable.btn_play);
                    }
                }else{
                    // Tiếp tục đoạn nhạc
                    if(mp!=null){
                        mp.start();
                        // Đổi hình ảnh nút chạy nhạc khi nhạc dừng
                        btnPlay.setImageResource(R.drawable.btn_pause);
                    }
                }

            }
        });

        /**
         * Tạo sự kiện cho nút tua tới
         * Tua tới 1 khoản thời gian có sẵn
         * */
        btnForward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // xác định thời gian đang chạy của bài nhạc
                int currentPosition = mp.getCurrentPosition();
                // kiểm tra xem thời lượng tua tới có lớn hơn thời lượng còn lại của bài nhạc hay không
                if(currentPosition + seekForwardTime <= mp.getDuration()){
                    // Tua tới
                    mp.seekTo(currentPosition + seekForwardTime);
                }else{
                    // nếu lớn hơn thì tua sang bài khác
                    mp.seekTo(mp.getDuration());
                }
            }
        });

        /**
         * Tạo sự kiện cho nút tua lùi
         * Tua lùi 1 khoản thời gian có sẵn
         * */
        btnBackward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Xác định vị trí đang chạy của bài nhạc
                int currentPosition = mp.getCurrentPosition();
                // Kiểm tra xem nếu tua lùi thì thời gian tua có >= 0 hay không
                if(currentPosition - seekBackwardTime >= 0){
                    // tua lùi
                    mp.seekTo(currentPosition - seekBackwardTime);
                }else{
                    // Nếu <= 0 thì trở về điểm xuất phát của bài hát
                    mp.seekTo(0);
                }

            }
        });

        /**
         * Tạo sự kiện cho nút tới
         * ý tưởng: chơi bản nhạc tiếp theo của bài đang chạy
         * */
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // kiểm tra xem bài tiếp theo có hay không
                if(currentSongIndex < (songsList.size() - 1)){
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;
                }else{
                    // nếu không có thì chạy bài đầu tiên
                    playSong(0);
                    currentSongIndex = 0;
                }

            }
        });

        /**
         * Tạo sự kiện cho nút lùi
         * ý tưởng: chơi bản nhạc trước của bài đang chạy
         * */
        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(currentSongIndex > 0){
                    playSong(currentSongIndex - 1);
                    currentSongIndex = currentSongIndex - 1;
                }else{
                    // chơi bản nhạc trước
                    playSong(songsList.size() - 1);
                    currentSongIndex = songsList.size() - 1;
                }

            }
        });

        /**
         * Nút lặp lại
         * Đặt cờ hiệu cho nút lặp là true ( cờ hiệu là true/false)
         * */
        btnRepeat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(isRepeat){
                    isRepeat = false;
                    Toast.makeText(getApplicationContext(), "Repeat is OFF", Toast.LENGTH_SHORT).show();
                    btnRepeat.setImageResource(R.drawable.btn_repeat);
                }else{
                    // đặt cờ hiệu là true
                    isRepeat = true;
                    Toast.makeText(getApplicationContext(), "Repeat is ON", Toast.LENGTH_SHORT).show();
                    // đặt cờ hiệu là false
                    isShuffle = false;
                    btnRepeat.setImageResource(R.drawable.btn_repeat_focused);
                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
                }
            }
        });

        /**
         * Nút trộn
         * Đặt cờ hiệu cho nút trộn là true ( giống nút lặp)
         * */
        btnShuffle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if(isShuffle){
                    isShuffle = false;
                    Toast.makeText(getApplicationContext(), "Shuffle is OFF", Toast.LENGTH_SHORT).show();
                    btnShuffle.setImageResource(R.drawable.btn_shuffle);
                }else{
                    // cờ hiệu true
                    isShuffle= true;
                    Toast.makeText(getApplicationContext(), "Shuffle is ON", Toast.LENGTH_SHORT).show();
                    // cờ hiệu false
                    isRepeat = false;
                    btnShuffle.setImageResource(R.drawable.btn_shuffle_focused);
                    btnRepeat.setImageResource(R.drawable.btn_repeat);
                }
            }
        });

        /**
         * Tạo sự kiện cho nút danh sách nhạc
         * Khi click vào thì sẽ hiển thị danh sách nhạc
         * */
        btnPlaylist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), PlayListActivity.class);
                startActivityForResult(i, 100);
            }
        });

    }

    /**
     * Lấy mục lục của các bài nhạc vào playlist
     * chạy nhạc khi click
     * */
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100){
            currentSongIndex = data.getExtras().getInt("songIndex");
            // chạy bài nhạc được chọn
            playSong(currentSongIndex);
        }

    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(AndroidBuildingMusicPlayerActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(AndroidBuildingMusicPlayerActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(AndroidBuildingMusicPlayerActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(AndroidBuildingMusicPlayerActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                    // Code for Below 23 API Oriented Device
                    this.songsList = plm.getPlayList();
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }

    /**
     * Hàm để chạy nhạc
     * @param songIndex - mục lục của nhạc
     * */
    public void  playSong(int songIndex){
        // chạy nhạc
        try {
            mp.reset();
            mp.setDataSource(songsList.get(songIndex).get("songPath"));
            mp.prepare();
            mp.start();
            // hiển thị tên của bài nhạc
            String songTitle = songsList.get(songIndex).get("songTitle");
            songTitleLabel.setText(songTitle);

            // Chuyển đổi thành nút dừng
            btnPlay.setImageResource(R.drawable.btn_pause);

            // giá trị cho thanh trạng thái của bài nhạc
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            // Cập nhật thanh trạng thái, ở đây là cập nhật quá trình của bài nhạc, nhạc chạy thì thanh trạng thái chạy theo
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cập nhật thời gian chạy nhạc
     * */
    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    /**
     * Nền
     * */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

            // Hiển thị thời gian tổng của nhạc
            songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
            // Hiển thị thời gian đang chạy nhạc
            songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));

            // Cập nhật thanh trạng thái
            int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            mHandler.postDelayed(this, 100);
        }
    };

    /**
     *
     * */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    /**
     * Dùng cho việc người dùng kéo thanh trạng thái
     * */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // dừng việc cập nhật thanh trạng thái
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    /**
     * Khi người dùng ngừng kéo thanh trạng thái
     * */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // Kéo tới hoặc kéo lùi
        mp.seekTo(currentPosition);

        // cập nhật thời gian
        updateProgressBar();
    }

    /**
     * Khi một bài hát được chạy xong
     * Nếu nút lặp lại được bật thì chạy lại bài đó ( cờ hiệu )
     * Nếu nút trộn được bật thì chạy bài ngẫu nhiên ( cờ hiệu )
     * */
    @Override
    public void onCompletion(MediaPlayer arg0) {

        // Kiểm tra nút lặp đang bật hay tắt
        if(isRepeat){
            // nút lặp hiện đang bật
            playSong(currentSongIndex);
        } else if(isShuffle){
            // nú trộn hiện dang bật
            Random rand = new Random();
            currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
            playSong(currentSongIndex);
        } else{
            // khi nút trộn và lặp đều tắt thì chạy bài tiếp theo
            if(currentSongIndex < (songsList.size() - 1)){
                playSong(currentSongIndex + 1);
                currentSongIndex = currentSongIndex + 1;
            }else{
                // hàm kiểm tra bài nhạc tiếp theo
                playSong(0);
                currentSongIndex = 0;
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.release();
    }

}