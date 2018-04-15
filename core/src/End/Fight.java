package End;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import la.plume.noire3.LaPlumeNoire3;

public class Fight {
	private int stat = 0;
	
	private FileHandle musicFile;
	private Music music;
	private final float reloadMusicPosition = 2.6F;
	
	private FileHandle[] fightSoundFile = new FileHandle[2];
	private Sound[] fightSound = new Sound[2];
	
	private FileHandle[][] imgFile;
	private boolean up;
	private Texture img;
	private Sprite img2;
	
	private boolean canAttack = true;
	private float attackSave;
	private long shakeTime;
	private boolean right;
	
	private float musicSave;
	
	private boolean isPause = false;
	
	private boolean isBegin;
	private FileHandle imgBeginFile;
	private FileHandle imgEndFile;
	private Texture imgFull;
	private Pixmap introBegin;
	private Pixmap introEnd;
	private int frame = 0;
	private int[] preS;
	
	private long time;
	
	private FileHandle[] boucleBegin;
	
	private FileHandle squareFile;
	private Sprite square;
	
	public Fight () {
		if(Gdx.app.getType().name().equals("Desktop")) {
			loadFile("bin/fight/f");
			loadFileBoucle("bin/fight/boucleBeginFight");
			this.musicFile = Gdx.files.internal("bin/fight/music.ogg");
			fightSoundFile[0] = Gdx.files.internal("bin/fight/fightSound1.ogg");
			fightSoundFile[1] = Gdx.files.internal("bin/fight/fightSound2.ogg");
			imgBeginFile = Gdx.files.internal("bin/fight/imgBegin.jpg");
			imgEndFile = Gdx.files.internal("bin/fight/imgEnd.jpg");
			squareFile = Gdx.files.internal("bin/fight/square.png");
		}else if(Gdx.app.getType().name().equals("Android")) {
			loadFile(LaPlumeNoire3.data + "/fight/f");
			loadFileBoucle(LaPlumeNoire3.data + "/fight/boucleBeginFight");
			this.musicFile = Gdx.files.absolute(LaPlumeNoire3.data + "/fight/music.ogg");
			fightSoundFile[0] = Gdx.files.absolute(LaPlumeNoire3.data + "/fight/fightSound1.ogg");
			fightSoundFile[1] = Gdx.files.absolute(LaPlumeNoire3.data + "/fight/fightSound2.ogg");
			imgBeginFile = Gdx.files.absolute(LaPlumeNoire3.data + "/fight/imgBegin.jpg");
			imgEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/fight/imgEnd.jpg");
			squareFile = Gdx.files.absolute(LaPlumeNoire3.data + "/fight/square.png");
		}
	}
	
	private void loadFile (String folderPath) {
		FileHandle folder = Gdx.files.absolute(folderPath);
		imgFile = new FileHandle[9][2];
		int i=0;
		boolean b = true;
		for (FileHandle file : folder.list()) {
			if(b){
				imgFile[i][0] = file;
			}else{
				imgFile[i][1] = file;
				i++;
			}
			b = !b;
		}
	}
	
	private void loadFileBoucle (String folderPath) {
		FileHandle folder = Gdx.files.absolute(folderPath);
		boucleBegin = new FileHandle[folder.list().length];
		int i=0;
		for (FileHandle file : folder.list()) {
			boucleBegin[i] = file;
			i++;
		}
	}
	
	public void pause(){				
		unload();
		music.stop();
		isPause = true;
	}
	
	public void unPause () {
		load(false);
		music.play();
		isPause = false;
	}
	
	public void load (boolean first) {
		music = Gdx.audio.newMusic(musicFile);
		fightSound[0] = Gdx.audio.newSound(fightSoundFile[0]);
		fightSound[1] = Gdx.audio.newSound(fightSoundFile[1]);
		img = new Texture(imgFile[stat][0]);
		if(first){
			imgFull = new Texture(boucleBegin[0]);
		}else{
			imgFull = new Texture(imgBeginFile);
		}
		square = new Sprite(new Texture(squareFile));
		music.play();
		up = true;
		right = true;
		musicSave = music.getPosition();
		attackSave = music.getPosition();
		shakeTime = 0;
		frame = 0;
		time = System.currentTimeMillis();
		canAttack = true;
	}
	
	public void unload () {
		img.dispose();
		imgFull.dispose();
		if(introBegin != null){
			introBegin.dispose();
			introEnd.dispose();
		}
		square.getTexture().dispose();
		music.dispose();
		fightSound[0].dispose();
		fightSound[1].dispose();
	}
	
	public void begin () {
		preS = new int[93];
		introBegin = new Pixmap(imgBeginFile);
		introEnd = new Pixmap(imgFile[0][0]);
		isBegin = true;
	}
	
	public void end () {
		preS = new int[93];
		introBegin = new Pixmap(imgFile[stat][0]);
		introEnd = new Pixmap(imgEndFile);
		music.stop();
		isBegin = false;
	}
	
	public void update () {
		if(isPause){
			unPause();
		}
		
		if(!music.isPlaying()){
			music.play();
			music.setPosition(reloadMusicPosition);
			musicSave = reloadMusicPosition;
			attackSave = reloadMusicPosition;
		}
		
		if(canAttack && music.getPosition() - musicSave >= 0.5F){
			int i = 0;
			if(up){
				i = 1;
			}
			img.dispose();
			img = new Texture(imgFile[stat][i]);
			up = !up;
			musicSave = music.getPosition();
		}
		
		if(!canAttack){
			if(music.getPosition() - attackSave >= 1){
				img.dispose();
				img = new Texture(imgFile[stat][0]);
				canAttack = true;
			}else{
				float alpha = music.getPosition() - attackSave;
				img2.setAlpha(alpha);
			}
		}
	}
	
	public boolean updateFull (boolean beginFight) {
		if(isPause){
			unPause();
		}
		
		if(beginFight){
			if(music.getPosition() < 1.5F){
				if(System.currentTimeMillis() - time >= 20){
					imgApear(isBegin);
					time += 20;
				}
				return false;
			}else{
				return true;
			}
		}else{
			if(LaPlumeNoire3.getLanch().getPosition() < 1.5F){
				if(System.currentTimeMillis() - time >= 20){
					imgApear(isBegin);
					time += 20;
				}
				return false;
			}else{
				return true;
			}
		}
		
	}
	
	public boolean updateBoucle() {
		if(frame < boucleBegin.length-1 && System.currentTimeMillis() - time > 33){
			frame++;
			imgFull.dispose();
			Pixmap pixmap = new Pixmap(boucleBegin[frame]);
			imgFull = new Texture(pixmap);
			pixmap.dispose();
			time += 33;
			return false;
		}else if(frame >= boucleBegin.length-1){
			return true;
		}else{
			return false;
		}
	}
	
	public void iniTime() {
		time = System.currentTimeMillis();
	}
	
	public void imgApear (boolean begin) {
		int s = 0;
		if(begin){
			s = 28 - (int)(((1.5f - music.getPosition())/1.5f) * 28);
		}else{
			s = 29 - (int)(((1.5f - LaPlumeNoire3.getLanch().getPosition())/1.5f) * 29);
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		/*for(int x=frame; x <= 54; x+=6){
			for(int f=0; f < s-preS[x]; f++){
				if(x%2 == 0){
					for(int y=0; y <= 318/2; y+=8){
						for(int a=y; a < y+8; a++){
							introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=4; y <= 318/2; y+=8){
						for(int a=y; a < y+8; a++){
							introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preS[x] = s;
		}*/
		for(int x=92-frame; x >= 0; x-=6){
			for(int f=0; f < s-preS[x]; f++){
				if(x%2 == 0){
					for(int y=0; y <= 720/2; y+=8){
						for(int a=y; a < y+8; a++){
							introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=4; y <= 720/2; y+=8){
						for(int a=y; a < y+8; a++){
							introBegin.drawPixel(((x*14)+s-f) - 14, y+a, introEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preS[x] = s;
		}
		imgFull.dispose();
		imgFull = new Texture(introBegin);
	}
	
	public Texture getImg(){
		return img;
	}
	
	public Sprite getImg2(){
		return img2;
	}
	
	public Texture getImgFull(){
		return imgFull;
	}
	
	public void attack(int i){
		img.dispose();
		img = new Texture(imgFile[stat][0]);
		up = true;
		
		if(stat == 0){
			if(i == 0){
				stat = 3;
				fightSound[1].play();
			}else if(i == 1){
				stat = 1;
				fightSound[0].play();
			}else{
				stat = 3;
				fightSound[1].play();
			}
			
		}else if(stat == 1){
			if(i == 0){
				stat = 2;
				fightSound[0].play();
			}else if(i == 1){
				stat = 4;
				fightSound[1].play();
			}else{
				stat = 4;
				fightSound[1].play();
			}
			
		}else if(stat == 2){
			if(i == 0){
				stat = 5;
				fightSound[1].play();
			}else if(i == 1){
				stat = -2;
				fightSound[0].play();
			}else{
				stat = 5;
				fightSound[1].play();
			}
			
		}else if(stat == 3){
			if(i == 0){
				stat = 6;
				fightSound[1].play();
			}else if(i == 1){
				stat = 6;
				fightSound[1].play();
			}else{
				stat = 4;
				fightSound[0].play();
			}
			
		}else if(stat == 4){
			if(i == 0){
				stat = 7;
				fightSound[1].play();
			}else if(i == 1){
				stat = 5;
				fightSound[0].play();
			}else{
				stat = 7;
				fightSound[1].play();
			}
			
		}else if(stat == 5){
			if(i == 0){
				stat = -2;
				fightSound[0].play();
			}else if(i == 1){
				stat = 8;
				fightSound[1].play();
			}else{
				stat = 8;
				fightSound[1].play();
			}
			
		}else if(stat == 6){
			if(i == 0){
				stat = -1;
				fightSound[1].play();
			}else if(i == 1){
				stat = -1;
				fightSound[1].play();
			}else{
				stat = 7;
				fightSound[0].play();
			}
			
		}else if(stat == 7){
			if(i == 0){
				stat = -1;
				fightSound[1].play();
			}else if(i == 1){
				stat = 8;
				fightSound[0].play();
			}else{
				stat = -1;
				fightSound[1].play();
			}
			
		}else if(stat == 8){
			if(i == 0){
				stat = -1;
				fightSound[1].play();
			}else if(i == 1){
				stat = -2;
				fightSound[0].play();
			}else{
				stat = -1;
				fightSound[1].play();
			}
			
		}
		if(stat == -1){
			stat = 0;
			LaPlumeNoire3.loseFight();
		}else if(stat == -2){
			stat = 0;
			LaPlumeNoire3.winFight();
		}else{
			if(img2 != null){
				img2.getTexture().dispose();
			}
			img2 = new Sprite(new Texture(imgFile[stat][0]));
		}
		canAttack = false;
		attackSave = music.getPosition();
		shakeTime = System.currentTimeMillis();
	}
	
	public boolean canAttack() {
		return canAttack;
	}
	
	public int shake () {
		if(System.currentTimeMillis() - shakeTime >= 50){
			right = !right;
			shakeTime += 50;
		}
		if(right){
			img2.setX(-8);
			return -8;
		}else{
			img2.setX(8);
			return 8;
		} 
	}
	
	public Sprite getSquare () {
		return square;
	}

}
