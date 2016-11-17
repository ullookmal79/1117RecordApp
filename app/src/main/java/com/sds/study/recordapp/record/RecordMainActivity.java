package com.sds.study.recordapp.record;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.sds.study.recordapp.R;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordMainActivity extends AppCompatActivity{
    String TAG;
    static final int REQUEST_RECORD_PERMISSION=1;
    MediaRecorder recorder;
    boolean isRun=false;
    ImageView img;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=this.getClass().getName();
        setContentView(R.layout.record_main);

        img=(ImageView)findViewById(R.id.img);
        init();
    }

    public void init(){
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
    }

    /*저장파일 구하기*/
    public String getSaveFile(){
        File dir = new File(Environment.getExternalStorageDirectory(), "IOT_record");
        Date d = new Date();
        String currentTime=new SimpleDateFormat("yyyy-MM-dd HHmmss").format(d);
        File saveFile = new File(dir, currentTime+".mp4");

        Log.d(TAG, "현재 시간은 "+saveFile.getAbsolutePath());

        return saveFile.getAbsolutePath();
    }

    /*녹음파일 화면 띄우기*/
    public void showList(){
        Intent intent = new Intent(this , FileListActivity.class);
        startActivity(intent);
    }

    public void startRecord(){
        if(isRun){//정지상태라면...
            recorder.stop();
            recorder.reset();/*재녹음을 위한 초기화*/
            img.setImageResource(R.drawable.record);
            isRun=false;

            /*녹음이 완료된 화면을 보여주자!!*/
            showList();

        }else{
            try {
                recorder.setOutputFile(getSaveFile());
                recorder.prepare();
                recorder.start();
                isRun=true;
                img.setImageResource(R.drawable.stop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*유저의 처리결과 받기!!*/
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "requestCode:"+requestCode+",grantResults[0]:"+grantResults[0]+",grantResults"+grantResults[1]);
        switch (requestCode){
            case REQUEST_RECORD_PERMISSION:
                if(permissions.length>0 && grantResults[0]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"앱사용을 위해서는 미디어 접근 권한을 주셔야 합니다", Toast.LENGTH_SHORT).show();
                }else if(permissions.length>0 && grantResults[1]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"앱사용을 위해서는 오디오 권한을 주셔야 합니다", Toast.LENGTH_SHORT).show();
                }
        }
    }

    /*각종 권한을 체크하자*/
    public void btnClick(View view){
        int writePermission= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordPermission=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO);

        if(writePermission== PackageManager.PERMISSION_DENIED || recordPermission== PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(this,
                        new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO
                        }, REQUEST_RECORD_PERMISSION);
        }else {
            startRecord();
        }
    }

}



