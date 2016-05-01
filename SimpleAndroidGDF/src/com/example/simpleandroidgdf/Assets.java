package com.example.simpleandroidgdf;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.AudioManager;
import android.media.SoundPool;
import android.content.res.AssetManager;
import com.example.simpleandroidgdf.GameMainActivity;

public class Assets {
	
	private static SoundPool soundPool;
	public static Bitmap welcome;
	public static AssetManager assets;
	//AssetManager mgr= getAssets();//�õ�AssetManager
	
	
	
	public static void load(){
		System.out.println("load");
			welcome = loadBitmap("welcome.jpg",false);
			if(welcome == null){
				System.out.println("load welcome error!");
				System.exit(0);
			}
		
	}
	
	private static Bitmap loadBitmap(String filename,boolean transparency){
		InputStream inputStream = null;
		try{
			inputStream = GameMainActivity.assets.open(filename);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Options options = new Options();
		if(transparency){
			options.inPreferredConfig = Config.ARGB_8888;
		}else{
			options.inPreferredConfig = Config.RGB_565;
		}
		
		Bitmap bitmap = BitmapFactory.decodeStream(inputStream,null,options);
		return bitmap;
	}
	
	
	private static int loadSound(String filename){
		int soundID = 0;
		if(soundPool ==null){
			soundPool = new SoundPool(25,AudioManager.STREAM_MUSIC,0);
		}
		try{
			soundID = soundPool.load(GameMainActivity.assets.openFd(filename), 1);
		}catch(IOException e){
			e.printStackTrace();
		}
		return soundID;
	}
	
	public static void playSound(int soundID){
		soundPool.play(soundID, 1, 1, 1, 0, 1);//int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate
	}

}
