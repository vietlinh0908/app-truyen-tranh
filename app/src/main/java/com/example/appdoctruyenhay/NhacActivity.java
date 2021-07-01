package com.example.appdoctruyenhay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appdoctruyenhay.moldel.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NhacActivity extends AppCompatActivity {

    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageButton btnPre,btnPlay,btnStop,btnNext;
    ImageView imgvAnhxoay;
    ArrayList<Song> arraySong;
    int position=0;
    MediaPlayer mediaPlayer;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhac);
        Anhxa();
        AddSong();
        Context context;
        animation= AnimationUtils.loadAnimation(this, R.anim.disc_rotate);
        KhoiTaoMediaPlayer();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///kiểm tra xem có đang phát hay ko
                if(mediaPlayer.isPlaying()){
                    //nếu đang phát thì --> pause và đổi hình thành play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.nut_tam_dung);
                }else {
                    /// đang ngừng thì ---> play và đổi hình thành pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.nut_tiep_tuc2);
                }
                setTimetotal();
                UpdateTimeSong();
                imgvAnhxoay.startAnimation(animation);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();//giải phóng
                btnPlay.setImageResource(R.drawable.nut_tam_dung);
                KhoiTaoMediaPlayer();
                setTimetotal();
                UpdateTimeSong();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position>arraySong.size()-1){
                    position=0;
                }
                ///kiểm tra phát, nếu đang phát thì stop lại
                //tránh tình trạng hát chồng lên nhau
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.nut_tiep_tuc2);
                setTimetotal();
                UpdateTimeSong();
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position<0){
                    position=arraySong.size()-1;
                }
                ///kiểm tra phát, nếu đang phát thì stop lại
                //tránh tình trạng hát chồng lên nhau
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.nut_tiep_tuc2);
                setTimetotal();
                UpdateTimeSong();
            }
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ////
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }
    private void UpdateTimeSong(){
        ///sử dụng quản lý tiến trình Handler để quản lí thời gian
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio=new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                handler.postDelayed(this, 500);// trong vòng nửa giây thì cập nhật lại
                ///kiểm tra bài hát nếu hết thì next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position>arraySong.size()-1){
                            position=0;
                        }
                        ///kiểm tra phát, nếu đang phát thì stop lại
                        //tránh tình trạng hát chồng lên nhau
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.nut_resume);
                        setTimetotal();
                        UpdateTimeSong();
                    }
                });
            }
        },100);///tham số delay


    }
    private void KhoiTaoMediaPlayer(){
        mediaPlayer= MediaPlayer.create(NhacActivity.this,arraySong.get(position).getFile());
        //mediaPlayer.start();
        txtTitle.setText(arraySong.get(position).getTitle());
    }

    private void AddSong() {
        arraySong=new ArrayList<>();
        arraySong.add(new Song("Bài hát dễ thương (Doraemon)", R.raw.doremon_music));
        arraySong.add(new Song("Bài hát nhạc kịch tính (Conan)", R.raw.connan_music));
        arraySong.add(new Song("Bài hát sôi động(Doraemon)", R.raw.doraemon_music_remix));
    }
    private void setTimetotal(){
        //chuyển dạng int của tổng số thời gian về phút và giây
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));// trả về kiểu int nên phải ép thành kiểu chuỗi
        ///gán max của skSong= mediaPlayer.getDuration();
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void Anhxa() {
        txtTitle=findViewById(R.id.textviewTitle);
        txtTimeSong=findViewById(R.id.textviewtimesong);
        txtTimeTotal=findViewById(R.id.textviewtotal);
        skSong=findViewById(R.id.seekBarsong);
        btnPre=findViewById(R.id.imagebtnPre);
        btnPlay=findViewById(R.id.imagebtnPlay);
        btnStop=findViewById(R.id.imagebtnStop);
        btnNext=findViewById(R.id.imagebtnNext);
        imgvAnhxoay=findViewById(R.id.imgv_dianhac);
    }
}