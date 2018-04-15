package End;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import la.plume.noire3.LaPlumeNoire3;

public class End {
	private FileHandle[] fin;
	private FileHandle transiFile;
	
	private Texture img;
	private Pixmap finBegin;
	private Pixmap finEnd;
	
	private FileHandle musicFile;
	private Music music;
		
	private int[] preS = new int[93];
	
	private static int frame = 0;
	private long time;
	
	private boolean b = true;
	
	private static int stat = 0;
	private double r = 5.7;
	
	private static boolean isPause = false;
	
	public End () {
		if(Gdx.app.getType().name().equals("Desktop")) {
			loadFile("bin/fin");
			transiFile = Gdx.files.internal("bin/fin/fin-0.jpg");
			this.musicFile = Gdx.files.internal("bin/fin/music.ogg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			loadFile("fin");
			transiFile = Gdx.files.internal("fin/fin-0.jpg");
			this.musicFile = Gdx.files.internal("fin/music.ogg");
		}
	}
	
	public void pause(){
		preS = new int[93];
		
		frame = 0;
				
		unload();
		music.stop();
		isPause = true;
	}
	
	public void unPause () {
		load();
		music.play();
		music.setPosition((float)(r*(stat+1)));
		isPause = false;
		time = System.currentTimeMillis();
	}
	
	public void load () {
		music = Gdx.audio.newMusic(musicFile);
		finBegin = new Pixmap(transiFile);
		finEnd = new Pixmap(fin[0]);
		img = new Texture(transiFile);
		music.play();
		time = System.currentTimeMillis();
	}
	
	public void unload () {
		finBegin.dispose();
		finEnd.dispose();
		img.dispose();
		music.dispose();
	}
	
	private void loadFile (String folderPath) {
		FileHandle folder = Gdx.files.internal(folderPath);
		fin = new FileHandle[folder.list().length-2];
		int i=0;
		for (FileHandle file : folder.list()) {
			if(!file.nameWithoutExtension().equals("music") && !file.nameWithoutExtension().equals("fin-0")){
				fin[i] = file;
				i++;
			}
		}
	}
	
	public Texture getImg () {
		return img;
	}
	
	public void update () {
		if(isPause){
			unPause();
		}
		
		if(b && music.getPosition() > r*(stat+1) && stat < fin.length){
			finBegin.dispose();
			finBegin = new Pixmap(fin[stat]);
			finEnd.dispose();
			finEnd = new Pixmap(transiFile);
			b = false;
		}else if(music.getPosition() > r*(stat+1)+2 && stat+1 < fin.length){
			stat++;
			finBegin.dispose();
			finBegin = new Pixmap(transiFile);
			finEnd.dispose();
			finEnd = new Pixmap(fin[stat]);
			b = true;
		}else if(!music.isPlaying()){
			unload();
			stat = 0;
			b = true;
			time = 0;
			preS = new int[93];
			LaPlumeNoire3.reboot();
		}else{
			if(System.currentTimeMillis() - time >= 30){
				imgApear(b);
				time += 30;
			}
		}
	}
	
	public void imgApear (boolean intro) {
		int s = 0;
		if(intro){
			s = 28 - (int)(((2f - (music.getPosition() - r*(stat+1)))/2f) * 28);
		}else{
			s = 32 - (int)(((2f - (music.getPosition() - r*(stat+1) - 2))/2f) * 32);
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		if(intro){
		for(int x=92-frame; x >= 0; x-=6){
			for(int f=0; f < s-preS[x]; f++){
				if(x%2 == 0){
					for(int y=0; y <= 720/2; y+=8){
						for(int a=y; a < y+8; a++){
							finBegin.drawPixel(((x*14)+s-f) - 14, y+a, finEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=4; y <= 720/2; y+=8){
						for(int a=y; a < y+8; a++){
							finBegin.drawPixel(((x*14)+s-f) - 14, y+a, finEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preS[x] = s;
		}
		}else{
			for(int x=92-frame; x >= 0; x-=6){
				for(int f=0; f < s-preS[x]; f++){
					if(x%2 == 0){
						for(int y=0; y <= 720/2; y+=8){
							for(int a=y; a < y+8; a++){
								finBegin.drawPixel(((x*14)+s-f) - 14, y+a, 16974591);
							}
						}
					}else{
						for(int y=4; y <= 720/2; y+=8){
							for(int a=y; a < y+8; a++){
								finBegin.drawPixel(((x*14)+s-f) - 14, y+a, 16974591);
							}
						}
					}
				}
				preS[x] = s;
			}
		}
		img.dispose();
		img = new Texture(finBegin);
	}
}
