package Scene;

import com.badlogic.gdx.Gdx;
//Import for DebugMode :
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import SceneExtend.SceneExtend;
import la.plume.noire3.LaPlumeNoire3;

public class Scene{
	protected FileHandle[] boucle;
	protected FileHandle introBeginFile;
	protected FileHandle introEndFile;
	protected FileHandle textBeginFile;
	protected FileHandle textEndFile;
	protected FileHandle endBeginFile;
	protected FileHandle endEndFile;
	
	protected Texture img;
	protected Pixmap introBegin;
	protected Pixmap introEnd;
	
	protected Texture imgText;
	protected Pixmap textBegin;
	protected Pixmap textEnd;
	
	protected FileHandle audioFile;
	protected Music audio;
	private boolean asBoucle;
	private int mouvement;
	protected boolean asEnd;
	protected boolean isEnd = false;
	
	protected boolean asClochette;
	protected boolean asBoite;
	private SceneExtend[] sceneExtends;
	protected static int sceneExtendStat = 0;
	protected boolean clochetteShow = false;
	protected boolean boiteShow = false;
	private boolean newText = false;
	private boolean beginBoucleExtend = false;
	private boolean speExtendBoucle = false;
	
	protected int[] boucleInfo;
	protected boolean speBoucle = false;
	protected float preMouvementPos = 0;
	protected float mouvementPos = 0;
	protected int mouvementSave = 0;
	protected boolean speMouvement = false;
	protected boolean mouv = false;
	protected float mouvSt = 0;
	protected int[][] textInfo;
	private float[] audioInfo;
	
	protected int[] preS = new int[25];
	protected int[] preTextS = new int[85];
	
	protected int frame = 0;
	protected long time;
	
	protected boolean singleDraw = false;
	
	protected int[] frameText;
	protected float[] startTimeText;
	protected long timeText;
	protected boolean[] startText;
	protected boolean[] endText;
	
	protected boolean firstScene = false;
	protected boolean passAudio = false;
	protected int stat = 0;
	
	protected boolean asText = true;
	
	protected static boolean isPause = false;
	protected static boolean isAudioFinish = false;
	
	private boolean isAddExtends = false;
		
	public Scene (String sceneName, boolean asBoucle, int mouvement, boolean asEnd, int[] boucleInfo, int[][] textInfo, float[] audioInfo, boolean asClochette,
			boolean asBoite, SceneExtend[] sceneExtends) {
		this.asBoucle = asBoucle;
		this.mouvement = mouvement;
		this.asEnd = asEnd;
		
		if(Gdx.app.getType().name().equals("Desktop")) {
			loadFile("bin/" + sceneName + "/boucle");
			introBeginFile = Gdx.files.internal("bin/introBegin.jpg");
			textBeginFile = Gdx.files.internal("bin/textBegin.jpg");
			if(asBoucle){
				introEndFile = Gdx.files.internal("bin/" + sceneName + "/boucle/img-0001.jpg");
			}else{
				introEndFile = Gdx.files.internal("bin/" + sceneName + "/introEnd.jpg");
			}
			if(!sceneName.equals("Gnothi seauton part1")){
				textEndFile = Gdx.files.internal("bin/" + sceneName + "/textEnd.jpg");
			}else{
				asText = false;
			}
			if(asEnd){
				endBeginFile = Gdx.files.internal("bin/endBegin.jpg");
				endEndFile = Gdx.files.internal("bin/" + sceneName + "/endEnd.jpg");
			}
			this.audioFile = Gdx.files.internal("bin/" + sceneName + "/audio.ogg");
		}else if(Gdx.app.getType().name().equals("Android")) {
			loadFile(LaPlumeNoire3.data + "/" + sceneName + "/boucle");
			introBeginFile = Gdx.files.internal("introBegin.jpg");
			textBeginFile = Gdx.files.internal("textBegin.jpg");
			if(asBoucle){
				introEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucle/img-0001.jpg");
			}else{
				introEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/introEnd.jpg");
			}
			if(!sceneName.equals("Gnothi seauton part1")){
				textEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/textEnd.jpg");
			}else{
				asText = false;
			}
			if(asEnd){
				endBeginFile = Gdx.files.internal("endBegin.jpg");
				endEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/endEnd.jpg");
			}
			this.audioFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/audio.ogg");
		}
		
		if(sceneName.equals("Le pacte")){
			speExtendBoucle = true;
			isAddExtends = true;
		}else if(sceneName.equals("La cabane dans les bois")){
			speMouvement = true;
		}else if(sceneName.equals("Gnothi seauton part2")){
			speBoucle = true;
		}else if(sceneName.equals("Loue soit le soleil")){
			speBoucle = true;
		}

		this.boucleInfo = boucleInfo;
		this.textInfo = textInfo;
		this.audioInfo = audioInfo;
		
		this.asClochette = asClochette;
		this.asBoite = asBoite;
		this.sceneExtends = sceneExtends;
	}
	
	public void pause(){
		preS = new int[25];
		preTextS = new int[85];
		
		frame = 0;
		
		stat = 0;
		if(!LaPlumeNoire3.isLastScenes() && canRepeat()){
			isAudioFinish = true;
		}
		audio.stop();
		unload();
		if(asClochette){
			sceneExtends[0].pause();
		}
		if(asBoite){
			sceneExtends[1].pause();
		}
		clochetteShow = false;
		boiteShow = false;
		isPause = true;
	}
	
	public void unPause () {
		load();
		if(isEnd){
			audio.play();
			audio.setPosition(audioInfo[2]);
		}
		if(asClochette){
			sceneExtends[0].unPause();;
		}
		if(asBoite){
			sceneExtends[1].unPause();
		}
		isPause = false;
		time = System.currentTimeMillis();
	}
	
	public void load () {
		audio = Gdx.audio.newMusic(audioFile);
		introBegin = new Pixmap(introBeginFile);
		textBegin = new Pixmap(textBeginFile);
		introEnd = new Pixmap(introEndFile);
		mouvementSave = introEnd.getWidth()-742;
		if(asText){
			textEnd = new Pixmap(textEndFile);
		}
		if(!isPause){
			img = new Texture(introBegin);
		}else{
			if(asBoucle){
				Pixmap pixmap = new Pixmap(boucle[0]);
				img = new Texture(pixmap);
				pixmap.dispose();
			}else{
				img = new Texture(introEndFile);
			}
		}
		imgText = new Texture(textBegin);
		singleDraw = false;
		if(asClochette){
			sceneExtends[0].load();;
		}
		if(asBoite){
			sceneExtends[1].load();
		}
	}
	
	public void unload () {
		introBegin.dispose();
		if(asText){
			textBegin.dispose();
			textEnd.dispose();
		}
		introEnd.dispose();
		img.dispose();
		imgText.dispose();
		audio.dispose();
		preMouvementPos = 0; 
		mouvementPos = 0;
		mouvementSave = 0;
		mouv = false;
		mouvSt = 0;
		if(asClochette){
			sceneExtends[0].unload();
		}
		if(asBoite){
			sceneExtends[1].unload();
		}
	}
	
	private void loadFile (String folderPath) {
		FileHandle folder = Gdx.files.absolute(folderPath);
		boucle = new FileHandle[folder.list().length];
		int i=0;
		for (FileHandle file : folder.list()) {
			boucle[i] = file;
			i++;
		}
	}
	
	public FileHandle[] getBoucle () {
		return boucle;
	}
	
	public void update () {
		if(isPause){
			unPause();
		}
		
		if(stat == 0){
			if(LaPlumeNoire3.getLanch().isPlaying()){
				if(firstScene){
					if(System.currentTimeMillis() - time >= 20){
						imgApear(true, true);
						time += 20;
					}
				}else{
					if(System.currentTimeMillis() - time >= 10){
						imgApear(false, true);
						time += 10;
					}
				}
			}else{
				introBegin.dispose();
				if(!speBoucle){
					introBegin = new Pixmap(introEndFile);
				}else if(asBoucle){
					introBegin = new Pixmap(boucle[boucleInfo[0]]);
				}else{
					introBegin = new Pixmap(boucle[boucleInfo[1]-1]);
				}
				introEnd.dispose();
				introEnd = new Pixmap(introBeginFile);
				
				stat++;
				LaPlumeNoire3.getLanch().stop();
				preS = new int[25];
				frameText = new int[textInfo.length];
				startTimeText = new float[textInfo.length];
				timeText = System.currentTimeMillis();
				startText = new boolean[textInfo.length];
				endText = new boolean[textInfo.length];
				for(int i=0; i < textInfo.length; i++){
					startText[i] = true;
					endText[i] = false;
				}
				System.out.println(sceneExtendStat);
				if(sceneExtendStat == 0){
					audio.play();
					if((passAudio || isAudioFinish) && !LaPlumeNoire3.isLastScenes()){
						audio.setPosition(audioInfo[1]);
					}
					if(mouvement != 0){
						mouvSt = audio.getPosition();
					}
				}else{
					if(sceneExtendStat == 1){
						clochetteShow = true;
					}else if(sceneExtendStat == 2){
						boiteShow = true;
					}
					sceneExtends[sceneExtendStat-1].setApear(true);
					sceneExtends[sceneExtendStat-1].active();
				}
			}
			
		}else if(stat == 1){
			Music audio;
			if(sceneExtendStat == 0){
				audio = this.audio;
			}else if(sceneExtendStat == 1){
				audio = sceneExtends[0].getAudio();
			}else{
				audio = sceneExtends[1].getAudio();
			}
			if(mouvement != 0 && !mouv){
				if(mouvement == 1){
					preMouvementPos = mouvementSave; 
					mouvementPos = mouvementSave;
				}else{
					preMouvementPos = 0; 
					mouvementPos = 0;
				}
				mouvSt = audio.getPosition();
				mouv = true;
			}
			if(!audio.isPlaying() && !LaPlumeNoire3.isLastScenes()){
				audio.play();
				audio.setPosition(audioInfo[1]);
			}
			
			//DebugMode :
			/*if(Gdx.input.isKeyJustPressed(Keys.ENTER)){
				if(LaPlumeNoire2.isLastScenes()){
					audio.stop();
				}else if(audioInfo[1] > audio.getPosition()){
					audio.setPosition(audioInfo[1]);
					if(asText){
						textBegin.drawPixmap(textEnd, 0, 0);
						for(int i=0; i < endText.length; i++){
							endText[0] = true;
						}
				
						imgText.dispose();
						imgText = new Texture(textBegin);
					}
					if(!passAudio && asItem && !isItem && itemTime <= audio.getPosition()){
						LaPlumeNoire2.addItem(itemName);
						isItem = true;
					}
				}
			}*/
			
			boolean asBoucle = false;
			if(sceneExtendStat != 0 && extendSceneBoucle()){
				asBoucle = true;
			}else if(sceneExtendStat == 0){
				asBoucle = this.asBoucle;
			}
			
			if(asBoucle){
				if(!speBoucle && (!speExtendBoucle || sceneExtendStat == 0)){
					if(System.currentTimeMillis() - time > 33){
						int[] boucleInfo;
						if(sceneExtendStat == 0){
							boucleInfo = this.boucleInfo;
						}else{
							boucleInfo = sceneExtends[sceneExtendStat-1].getBoucleInfo();
						}
						if(frame < boucleInfo[1]-1){
							frame++;
						}else{
							frame = boucleInfo[0];
						}
						img.dispose();
						Pixmap pixmap;
						if(sceneExtendStat == 0){
							pixmap = new Pixmap(boucle[frame]);
						}else{
							pixmap = new Pixmap(sceneExtends[sceneExtendStat-1].getBoucle(frame));
						}
						img = new Texture(pixmap);
						pixmap.dispose();
						time += 33;
					}
				}else{
					int[] boucleInfo;
					if(sceneExtendStat == 0){
						boucleInfo = this.boucleInfo;
					}else{
						boucleInfo = sceneExtends[sceneExtendStat-1].getBoucleInfo();
					}
					if(frame < boucleInfo[1]-1 && System.currentTimeMillis() - time > 33){
						frame++;
						img.dispose();
						Pixmap pixmap;
						if(sceneExtendStat == 0){
							pixmap = new Pixmap(boucle[frame]);
						}else{
							pixmap = new Pixmap(sceneExtends[sceneExtendStat-1].getBoucle(frame));
						}
						img = new Texture(pixmap);
						pixmap.dispose();
						time += 33;
					}
				}
				
			}else{
				if(!singleDraw && !this.asBoucle){
					img = new Texture(introEndFile);
					singleDraw = true;
				}
			}
			
			if(sceneExtendStat == 0){
				if(mouvement == 1 && mouvementPos > 0){
					
				}else if(mouvement == 2){
					if(!speMouvement){
						if(audio.getPosition() - mouvSt >= 0.05){//mouvementPos >= preMouvementPos
							if(passAudio && audio.getPosition() >= audioInfo[1]){
								mouvementPos = mouvementSave/2 + (int) (mouvementSave * Math.sin((audioInfo[1]-audio.getPosition())/10-1.55)/2);
							}else{
								mouvementPos = mouvementSave/2 + (int) (mouvementSave * Math.sin(audio.getPosition()/10-1.55)/2);
							}
							mouvSt += 0.05;
						}
					}else{
						if(audio.getPosition() - mouvSt >= 0.05 && mouvementPos >= preMouvementPos){//mouvementPos >= preMouvementPos
							preMouvementPos = mouvementPos;
							if(passAudio && audio.getPosition() >= audioInfo[1]){
								mouvementPos = mouvementSave/2 + (int) (mouvementSave * Math.sin((audioInfo[1]-audio.getPosition())/10-1.55)/2);
							}else{
								mouvementPos = mouvementSave/2 + (int) (mouvementSave * Math.sin(audio.getPosition()/10-1.55)/2);
							}
							if(mouvementPos < preMouvementPos){
								mouvementPos = preMouvementPos;
								preMouvementPos = mouvementPos+1;
							}
							mouvSt += 0.05;
						}
					}
				}
			}
			
			if(asText){
				if(!newText){
					if(passAudio){
						if(System.currentTimeMillis() - timeText >= 20){
							for(int i=0; i < textInfo.length; i++){
								if(isText(i)){
									textApear(i, false);
								}
							}
							timeText += 20;
						}
					}else{
						if(System.currentTimeMillis() - timeText >= 40){
							for(int i=0; i < textInfo.length; i++){
								if(isText(i)){
									textApear(i, false);
								}
							}
							timeText += 40;
						}
					}
				}else{
					if(sceneExtends[0].isApear() && sceneExtends[1].isApear()){
						if(System.currentTimeMillis() - timeText >= 40){
							if(sceneExtendStat == 0){
								for(int i=0; i < textInfo.length; i++){
									if(isText(i)){
										textApear(i, false);
									}
								}
							}else{
								for(int i=0; i < sceneExtends[sceneExtendStat-1].getTextInfo().length; i++){
									if(isText(i)){
										textApear(i, false);
									}
								}
							}
							timeText += 40;
						}
					}
				}
			}
			
			if(isAddExtends && !LaPlumeNoire3.haveExtends()){
				if(audio.getPosition() >= 240+56.1F){
					LaPlumeNoire3.addExtends();
				}
			}
			
		}else if(stat == 4){
			if(LaPlumeNoire3.getLanch().getPosition() < 1.5F){
				if(System.currentTimeMillis() - time >= 10){
					imgApear(false, false);
					if(asText){
						for(int i=0; i < textInfo.length; i++){
							textApear(i, true);
						}
					}
					time += 10;
				}
			}else{
				if(asText){
					textBegin.drawPixmap(textEnd, 0, 0);
					imgText.dispose();
					imgText = new Texture(textBegin);
				}
				stat = 0;
				preS = new int[25];
				preTextS = new int[85];
				firstScene = false;
				if(!LaPlumeNoire3.isLastScenes()){
					passAudio = true;
				}
				isEnd = false;
				LaPlumeNoire3.change();
			}
			
		}else if(stat == 5){
			if(System.currentTimeMillis() - timeText >= 40){
				if(sceneExtendStat == 0){
					if(audio.getPosition() < 5){
						textEndApear(false);
					}else{
						LaPlumeNoire3.changeButton(textInfo);
						preTextS = new int[85];
						startText = new boolean[textInfo.length];
						endText = new boolean[textInfo.length];
						for(int i=0; i < textInfo.length; i++){
							startText[i] = true;
							endText[i] = false;
						}
						textEnd.dispose();
						textEnd = new Pixmap(textEndFile);
						newText = true;
						frame = 0;
						stat = 1;
					}
				}else{
					if(sceneExtends[sceneExtendStat-1].getAudio().getPosition() < 5){
						textEndApear(false);
					}else{
						if(sceneExtends[sceneExtendStat-1].isTextDifferent()){
							LaPlumeNoire3.changeButton(sceneExtends[sceneExtendStat-1].getTextInfo());
						}else{
							LaPlumeNoire3.changeButton(textInfo);
						}
						preTextS = new int[85];
						startText = new boolean[textInfo.length];
						endText = new boolean[textInfo.length];
						for(int i=0; i < textInfo.length; i++){
							startText[i] = true;
							endText[i] = false;
						}
						textEnd.dispose();
						if(sceneExtends[sceneExtendStat-1].isTextDifferent()){
							textEnd = new Pixmap(sceneExtends[sceneExtendStat-1].getTextEnd());
						}else{
							textEnd = new Pixmap(textEndFile);
						}
						newText = true;
						frame = 0;
						stat = 1;
					}
				}
			}
		}
	}
	
	public void imgApear (boolean intro, boolean appear) {
		int s = 0;
		if(intro){
			s = 28 - (int)(((3f - LaPlumeNoire3.getLanch().getPosition())/3f) * 28);
		}else{
			s = 29 - (int)(((1.5f - LaPlumeNoire3.getLanch().getPosition())/1.5f) * 29);
			if(stat == 0){
				s = s-29;
			}
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		
		if(mouvement != 1 || !appear){
			for(int y=24-frame; y >= 0; y-=6){
				for(int f=0; f < s-preS[y]; f++){
					if(y%2 == 0){
						for(int x=0; x <= 742/2; x+=8){
							for(int a=x; a < x+8; a++){
								introBegin.drawPixel(x+a, (y*14)-s+f, introEnd.getPixel(x+a, (y*14)-s+f));
							}
						}
					}else{
						for(int x=4; x <= 742/2; x+=8){
							for(int a=x; a < x+8; a++){
								introBegin.drawPixel(x+a, (y*14)-s+f, introEnd.getPixel(x+a, (y*14)-s+f));
							}
						}
					}
				}
				preS[y] = s;
			}
		}else if(mouvement == 1){
			for(int y=24-frame; y >= 0; y-=6){
				for(int f=0; f < s-preS[y]; f++){
					if(y%2 == 0){
						for(int x=0; x <= 742/2; x+=8){
							for(int a=x; a < x+8; a++){
								introBegin.drawPixel(x+a, (y*14)-s+f, introEnd.getPixel(x+a+mouvementSave, (y*14)-s+f));
							}
						}
					}else{
						for(int x=4; x <= 742/2; x+=8){
							for(int a=x; a < x+8; a++){
								introBegin.drawPixel(x+a, (y*14)-s+f, introEnd.getPixel(x+a+mouvementSave, (y*14)-s+f));
							}
						}
					}
				}
				preS[y] = s;
			}
		}
		
		img.dispose();
		img = new Texture(introBegin);
	}
	
	public void imgEndApear (boolean intro) {
		int s = 0;
		if(intro){
			s = 28 - (int)(((2f - (audio.getPosition() - audioInfo[2]))/2f) * 28);
		}else{
			s = 29 - (int)(((2f - (audio.getPosition() - audioInfo[2] - 2))/2f) * 29);
		}
		if(frame < 6-1){
			frame ++;
		}else{
			frame = 0;
		}
		for(int x=frame; x <= 54; x+=6){
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
		}
		img.dispose();
		img = new Texture(introBegin);
	}
	
	public void textApear(int i, boolean end){
		int s = 0;
		int startY = 0;
		int endY =0;
		
		Music audio = this.audio;
		if(sceneExtendStat == 1){
			audio = sceneExtends[0].getAudio();
		}else if(sceneExtendStat == 2){
			audio = sceneExtends[1].getAudio();
		}
		int[][]textInfo = this.textInfo;
		if(sceneExtendStat == 1 && sceneExtends[0].isTextDifferent()){
			textInfo = sceneExtends[0].getTextInfo();
		}else if(sceneExtendStat == 2 && sceneExtends[1].isTextDifferent()){
			textInfo = sceneExtends[1].getTextInfo();
		}
		
		if(!end){
			if(passAudio){
				s = 28 - (int)(((1.5F - (audio.getPosition()-startTimeText[i]))/1.5F) * 28);
			}else{
				s = 28 - (int)(((4F - (audio.getPosition()-startTimeText[i]))/4F) * 28);
			}
			if(textInfo.length == 1 || passAudio){
				startY = 0;
				endY = 98;
			}else if(textInfo.length == 2){
				startY = (98/2)*i;
				endY = (98/2)*(i+1);
			}else{
				if(i == 2){
					startY = 66;
					endY = 98;
				}else{
					startY = (65/2)*i;
					endY = (65/2)*(i+1);
				}
			}
		}else{
			s = 29 - (int)(((1.5f - LaPlumeNoire3.getLanch().getPosition())/1.5f) * 29);
			startY = 0;
			endY = 98;
		}
		if(frameText[i] < 6-1){
			frameText[i] ++;
		}else{
			frameText[i] = 0;
		}
		for(int x=frameText[i]; x <= 84; x+=6){
			for(int f=0; f < s-preTextS[x]; f++){
				if(x%2 == 0){
					for(int y=startY; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=startY+4; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preTextS[x] = s;
		}
		
		if(s > 30){
			if(textInfo.length == 1){
				textBegin.drawPixmap(textEnd, 0, 0);
			}else if(textInfo.length == 2){
				textBegin.drawPixmap(textEnd, 0, 98*i, 0, 98*i, 1110, 98);
			}else{
				if(i == 2){
					textBegin.drawPixmap(textEnd, 0, 65+66, 0, 65+66, 1110, 66);
				}else if(i == 1){
					textBegin.drawPixmap(textEnd, 0, 65, 0, 65, 1110, 65);
				}else{
					textBegin.drawPixmap(textEnd, 0, 0, 0, 0, 1110, 65);
				}
			}
			endText[i] = true;
		}
		
		imgText.dispose();
		imgText = new Texture(textBegin);
	}
	
	public void textEndApear(boolean intro){
		int s = 0;
		int startY = 0;
		int endY = 98;
		if(intro){
			s = 28 - (int)(((2f - (audio.getPosition() - audioInfo[2]))/2f) * 28);
		}else{
			if(sceneExtendStat == 0){
				s = 29 - (int)(((2f - (audio.getPosition() - audioInfo[2] - 2))/2f) * 29);
			}else{
				s = 29 - (int)(((2f - sceneExtends[sceneExtendStat-1].getAudio().getPosition())/2f) * 28);
			}
		}
		if(frameText[0] < 6-1){
			frameText[0] ++;
		}else{
			frameText[0] = 0;
		}
		for(int x=frameText[0]; x <= 84; x+=6){
			for(int f=0; f < s-preTextS[x]; f++){
				if(x%2 == 0){
					for(int y=startY; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}else{
					for(int y=startY+4; y <= endY; y+=8){
						for(int a=y; a < y+8; a++){
							textBegin.drawPixel(((x*14)+s-f) - 14, y+a, textEnd.getPixel(((x*14)+s-f) - 14, y+a));
						}
					}
				}
			}
			preTextS[x] = s;
		}
		
		if(s > 30){
			textBegin.drawPixmap(textEnd, 0, 0);
			endText[endText.length-1] = true;
		}
		
		imgText.dispose();
		imgText = new Texture(textBegin);
	}
	
	public void setAudio() {
		if(sceneExtendStat == 0){
			audio.setPosition(audioInfo[0]);
			mouvSt = audio.getPosition();
		}else{
			sceneExtends[sceneExtendStat-1].getAudio().setPosition(sceneExtends[sceneExtendStat-1].getAudioInfo()[0]);
		}
		if(mouvement == 1 && mouvementPos > 0){
			preMouvementPos = 0;
			mouvementPos = 0;
		}else if(mouvement == 2 && mouvementPos < mouvementSave){
			preMouvementPos = mouvementSave;
			mouvementPos = mouvementSave;
		}
	}
	
	public Texture getImg() {
		return img;
	}
	
	public FileHandle getImgFile() {
		return introEndFile;
	}
	
	public Texture getImgText() {
		return imgText;
	}
	
	public Music getAudio () {
		if(sceneExtendStat == 0){
			return audio;
		}else{
			return sceneExtends[sceneExtendStat-1].getAudio();
		}
	}
	
	public int[] getBoucleInfo() {
		return boucleInfo;
	}
	
	public int[][] getTextInfo() {
		return textInfo;
	}
	
	public float[] getAudioInfo() {
		return audioInfo;
	}
	
	public void passAudio(){
		passAudio = true;
	}
	
	public void passAudioClochette(){
		sceneExtends[0].passAudio();
	}
	
	public void passAudioBoite(){
		sceneExtends[1].passAudio();
	}
	
	public void reniPassAudio(){
		passAudio = false;
		if(asClochette){
			sceneExtends[0].reniPassAudio();
		}
		if(asBoite){
			sceneExtends[1].reniPassAudio();
		}
	}
	
	public boolean getPassAudio () {
		return passAudio;
	}
	
	public boolean getPassAudioClochette() {
		return sceneExtends[0].getPassAudio();
	}
	
	public boolean getPassAudioBoite() {
		return sceneExtends[1].getPassAudio();
	}
	
	public boolean canClick (int i) {
		if(stat == 1){
			return endText[i];
		}else{
			return false;
		}
	}

	public boolean isText(int i) {
		Music audio = this.audio;
		if(sceneExtendStat == 1){
			audio = sceneExtends[0].getAudio();
		}else if(sceneExtendStat == 2){
			audio = sceneExtends[1].getAudio();
		}
		int[][]textInfo = this.textInfo;
		if(sceneExtendStat == 1 && sceneExtends[0].isTextDifferent()){
			textInfo = sceneExtends[0].getTextInfo();
		}else if(sceneExtendStat == 2 && sceneExtends[1].isTextDifferent()){
			textInfo = sceneExtends[1].getTextInfo();
		}
		boolean passAudio = this.passAudio;
		if(sceneExtendStat == 1 && sceneExtends[0].isTextDifferent()){
			passAudio = sceneExtends[0].getPassAudio();
		}else if(sceneExtendStat == 2 && sceneExtends[1].isTextDifferent()){
			passAudio = sceneExtends[1].getPassAudio();
		}
		
		if(audio.getPosition() >= textInfo[i][5] || passAudio){
			if(startText[i]){
				startTimeText[i] = audio.getPosition();
				startText[i] = false;
			}
			if(!endText[i]){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public boolean isMouvement(){
		if(mouvement == 0 || (sceneExtendStat != 0 && sceneExtends[sceneExtendStat-1].isApear() || (boiteShow && clochetteShow))){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isXMouvement(){
		if(mouvement == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public float getMouvementPos(){
		return mouvementPos;
	}
	
	public boolean asClochette() {
		return asClochette;
	}

	public boolean asBoite() {
		return asBoite;
	}
	
	public boolean canActiveExtend() {
		if(stat == 1 && sceneExtends[0].isApear() && sceneExtends[1].isApear()){
			return true;
		}else{
			return false;
		}
	}
	
	public void activeClochette(){
		if(speMouvement){
			mouvementPos = mouvementSave;
		}else{
			mouv = false;
		}
		if(sceneExtendStat != 1){
			sceneExtends[0].active();
			sceneExtends[0].setApear(false);
			sceneExtends[0].setApearSens(true);
			if(sceneExtendStat == 2){
				sceneExtends[1].desactive();
				sceneExtends[1].setApear(false);
				sceneExtends[1].setApearSens(false);
				sceneExtends[0].setApear(true);
				LaPlumeNoire3.desactiveBrume();
				boiteShow = true;
				sceneExtends[1].passAudio();
			}else{
				audio.stop();
			}
			
			if(asText){
				imgText.dispose();
				textBegin.dispose();
				if(sceneExtendStat == 2 && sceneExtends[1].isTextDifferent()){
					imgText = new Texture(sceneExtends[1].getTextEnd());
					textBegin = new Pixmap(sceneExtends[1].getTextEnd());
				}else{
					imgText = new Texture(textEndFile);
					textBegin = new Pixmap(textEndFile);
				}
				for(int i=0; i < endText.length; i++){
					endText[i] = true;
					startText[i] = true;
					preTextS = new int[85];
				}
			}
			if(sceneExtendStat == 2){
				LaPlumeNoire3.desactiveBrume();
			}
			
			if(sceneExtends[0].isTextDifferent() || (sceneExtendStat == 2 && sceneExtends[1].isTextDifferent())){
				stat = 5;
				textEnd.dispose();
				textEnd = new Pixmap(textBeginFile);
				timeText = System.currentTimeMillis();
			}
			
			if(!speBoucle && asBoucle){
				frame = boucleInfo[0];
				img.dispose();
				img = new Texture(boucle[frame]);
			}
			
			passAudio = true;
			clochetteShow = true;
			beginBoucleExtend = false;
			sceneExtendStat = 1;
			LaPlumeNoire3.save();
		}else{
			desactiveExtend();
		}
	}

	public void activeBoite(){
		if(speMouvement){
			mouvementPos = mouvementSave;
		}else{
			mouv = false;
		}
		if(sceneExtendStat != 2){
			sceneExtends[1].active();
			sceneExtends[1].setApear(false);
			sceneExtends[1].setApearSens(true);
			if(sceneExtendStat == 1){
				sceneExtends[0].desactive();
				sceneExtends[0].setApearSens(false);
				sceneExtends[1].setApearSens(true);
				sceneExtends[0].passAudio();
			}else{
				audio.stop();
			}
			
			if(asText){
				imgText.dispose();
				textBegin.dispose();
				if(sceneExtendStat == 1 && sceneExtends[0].isTextDifferent()){
					imgText = new Texture(sceneExtends[0].getTextEnd());
					textBegin = new Pixmap(sceneExtends[0].getTextEnd());
				}else{
					imgText = new Texture(textEndFile);
					textBegin = new Pixmap(textEndFile);
				}
				for(int i=0; i < endText.length; i++){
					endText[i] = true;
					startText[i] = true;
					preTextS = new int[85];
				}
			}
			if(sceneExtends[1].asBrume()){
				LaPlumeNoire3.activeBrume();
			}
			
			if(sceneExtends[1].isTextDifferent() || (sceneExtendStat == 1 && sceneExtends[0].isTextDifferent())){
				stat = 5;
				textEnd.dispose();
				textEnd = new Pixmap(textBeginFile);
				timeText = System.currentTimeMillis();
			}
			
			passAudio = true;
			boiteShow = true;
			beginBoucleExtend = false;
			sceneExtendStat = 2;
			
			if(!speBoucle && asBoucle){
				frame = boucleInfo[0];
				img.dispose();
				img = new Texture(boucle[frame]);
			}
			LaPlumeNoire3.save();
		}else{
			desactiveExtend();
		}
	}
	
	private void desactiveExtend() {
		if(speExtendBoucle){
			img.dispose();
			img = new Texture(introEndFile);
		}
		
		sceneExtends[sceneExtendStat-1].desactive();
		sceneExtends[sceneExtendStat-1].setApear(false);
		sceneExtends[sceneExtendStat-1].setApearSens(false);
		
		LaPlumeNoire3.desactiveBrume();
		
		audio.play();
		
		if(asText){
			imgText.dispose();
			textBegin.dispose();
			if((sceneExtendStat == 1 && sceneExtends[0].isTextDifferent()) || (sceneExtendStat == 2 && sceneExtends[1].isTextDifferent())){
				imgText = new Texture(sceneExtends[sceneExtendStat-1].getTextEnd());
				textBegin = new Pixmap(sceneExtends[sceneExtendStat-1].getTextEnd());
			}else{
				imgText = new Texture(textEndFile);
				textBegin = new Pixmap(textEndFile);
			}
			for(int i=0; i < endText.length; i++){
				endText[i] = true;
				startText[i] = true;
				preTextS = new int[85];
			}
		}
		
		if(sceneExtends[sceneExtendStat-1].isTextDifferent()){
			stat = 5;
			textEnd.dispose();
			textEnd = new Pixmap(textBeginFile);
			timeText = System.currentTimeMillis();
		}
		sceneExtends[sceneExtendStat-1].passAudio();
		sceneExtendStat = 0;
		frame = boucleInfo[0];
		LaPlumeNoire3.save();
	}
	
	public void setClochetteShow (boolean clochetteShow) {
		this.clochetteShow = clochetteShow;
	}
	
	public void setBoiteShow (boolean boiteShow) {
		this.boiteShow = boiteShow;
	}
	
	public boolean isClochetteShow () {
		return clochetteShow;
	}
	
	public boolean isBoiteShow () {
		return boiteShow;
	}
	
	public Sprite getClochetteImg() {
		if(boiteShow){
			if(sceneExtends[1].isApear() && !sceneExtends[1].getApearSens()){
				boiteShow = false;
			}
		}
		return sceneExtends[0].getImg();
	}
	
	public Sprite getBoiteImg() {
		if(clochetteShow){
			if(sceneExtends[1].isApear()){
				clochetteShow = false;
			}
		}
		return sceneExtends[1].getImg();
	}
	
	public boolean extendSceneBoucle() {
		if(sceneExtendStat != 0){
			boolean transition = false;
			if(clochetteShow && boiteShow){
				transition = true;
			}
			if(sceneExtends[sceneExtendStat-1].isApear() && sceneExtends[sceneExtendStat-1].asBoucle() && !transition){
				if(!beginBoucleExtend){
					img.dispose();
					Pixmap pixmap = new Pixmap(sceneExtends[sceneExtendStat-1].getBoucle(0));
					img = new Texture(pixmap);
					pixmap.dispose();
					time = System.currentTimeMillis();
					beginBoucleExtend = true;
				}
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void setFirstScene() {
		firstScene = true;
	}
	
	public void start() {
		time = System.currentTimeMillis();
	}

	public void close(boolean back) {
		LaPlumeNoire3.desactiveBrume();
		LaPlumeNoire3.removeBrume();
		
		if(mouvement == 1){
			preMouvementPos = 0;
			mouvementPos = 0;
		}else if(mouvement == 2){
			preMouvementPos = 0;
			mouvementPos = 0;
		}
		if(sceneExtendStat == 0){
			audio.stop();
		}else{
			sceneExtends[sceneExtendStat-1].getAudio().stop();
		}
		isAudioFinish = false;
		textBegin.dispose();
		if(!isEnd && asText){
			textBegin = new Pixmap(textEndFile);
		}else if(asText){
			textBegin = new Pixmap(endEndFile);
		}
		if(asText){
			textEnd.dispose();
			textEnd = new Pixmap(textBeginFile);
		}
		if(back){
			stat = 0;
		}else{
			stat = 4;
			if(!LaPlumeNoire3.isLastScenes()){
				if(sceneExtendStat == 0){
					passAudio = true;
				}else{
					sceneExtends[sceneExtendStat-1].passAudio();
				}
			}
			if(sceneExtendStat != 0){
				introBegin.dispose();
				introBegin = new Pixmap(sceneExtends[sceneExtendStat-1].getIntroEnd());
				if(sceneExtends[sceneExtendStat-1].isTextDifferent()){
					textBegin.dispose();
					textBegin = new Pixmap(sceneExtends[sceneExtendStat-1].getTextEnd());
				}
			}
		}
		if(asClochette){
			sceneExtends[0].close();
		}
		if(asBoite){
			sceneExtends[1].close();
		}
		boiteShow = false;
		clochetteShow = false;
		sceneExtendStat = 0;
		time = System.currentTimeMillis();
	}
	
	public void closeForFight() {
		audio.stop();
		isAudioFinish = false;
		stat = 0;
		passAudio = true;
	}

	public boolean canRepeat() {
		if(sceneExtendStat == 0){
			if(audioInfo[1] <= audio.getPosition() && endText[endText.length-1] && !asEnd){
				return true;
			}else{
				return false;
			}
		}else{
			if(sceneExtends[sceneExtendStat-1].getAudioInfo()[1] <= sceneExtends[sceneExtendStat-1].getAudio().getPosition() && endText[endText.length-1] && !asEnd){
				return true;
			}else{
				return false;
			}
		}
	}
	
	//uniquement pour la scène "Au clair de la lune"
	public boolean canClickFontaine () {
		if(sceneExtendStat == 1 && sceneExtends[0].getAudio().getPosition() >= 240+4 && sceneExtends[0].getAudio().getPosition() <= 240+14){
			return true;
		}else{
			return false;
		}
	}
}
