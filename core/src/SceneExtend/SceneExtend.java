package SceneExtend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import la.plume.noire3.LaPlumeNoire3;

public class SceneExtend {
		
	protected FileHandle[] boucle;
	protected FileHandle introEndFile;
	protected FileHandle textEndFile;
	
	private boolean asBrume;
	
	private boolean asBoucle;
	private int[] boucleInfo;
	
	protected FileHandle audioFile;
	protected Music audio;
		
	private int[][] textInfo;
	private float[] audioInfo;
	
	protected boolean passAudio = false;
	
	private boolean apear = true;
	private boolean apearSens = true;
	private Sprite img;
	private long extendTime;

	public SceneExtend(String sceneName, boolean asBrume, boolean asBoucle, int[] boucleInfo, boolean textDifferent, int[][] textInfo, float[] audioInfo) {
		this.asBrume = asBrume;
		this.asBoucle = asBoucle;
		this.boucleInfo = boucleInfo;
		
		if(Gdx.app.getType().name().equals("Desktop")) {
			if(asBoucle){
				loadFile("bin/" + sceneName + "/boucle");
				introEndFile = Gdx.files.internal("bin/" + sceneName + "/boucle/img-0001.jpg");
			}else{
				introEndFile = Gdx.files.internal("bin/" + sceneName + "/introEnd.jpg");
			}
			if(textDifferent){
				textEndFile = Gdx.files.absolute("bin/" + sceneName + "/textEnd.jpg");
			}
			this.audioFile = Gdx.files.internal("bin/" + sceneName + "/audio.ogg");
			
		}else if(Gdx.app.getType().name().equals("Android")) {
			if(asBoucle){
				loadFile(LaPlumeNoire3.data + "/" + sceneName + "/boucle");
				introEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/boucle/img-0001.jpg");
			}else{
				introEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/introEnd.jpg");
			}
			if(textDifferent){
				textEndFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/textEnd.jpg");
			}
			this.audioFile = Gdx.files.absolute(LaPlumeNoire3.data + "/" + sceneName + "/audio.ogg");
		}
		this.textInfo = textInfo;
		this.audioInfo = audioInfo;
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

	public void pause(){
		audio.stop();
		unload();
		close();
	}
	
	public void unPause () {
		load();
	}
	
	public void load () {
		img = new Sprite(new Texture(introEndFile));
		img.setPosition(273, 321);
		audio = Gdx.audio.newMusic(audioFile);
	}
	
	public void unload () {
		img.getTexture().dispose();
		audio.dispose();
	}
	
	public void active () {
		audio.play();
		extendTime = System.currentTimeMillis();
	}
	
	public void desactive () {
		audio.stop();
	}
	
	public boolean asBrume () {
		return asBrume;
	}

	public Sprite getImg() {
		if(!apear){
			float alpha;
			if(apearSens){
				alpha = (System.currentTimeMillis() - extendTime)/9000F;
				if(alpha >= 1){
					alpha = 1;
					apear = true;
				}
			}else{
				alpha = 1 - ((System.currentTimeMillis() - extendTime)/9000F);
				if(alpha <= 0){
					alpha = 0;
					apear = true;
				}
			}
			img.setAlpha(alpha);
		}
		return img;
	}
	
	public void setApear (boolean apear) {
		this.apear = apear;
		img.setAlpha(1);
		extendTime = System.currentTimeMillis();
	}
	
	public boolean isApear() {
		return apear;
	}
	
	public void setApearSens (boolean apearSens){
		this.apearSens = apearSens;
	}
	
	public boolean getApearSens (){
		return apearSens;
	}

	public Music getAudio() {
		return audio;
	}
	
	public boolean isTextDifferent () {
		if(textEndFile != null){
			return true;
		}else{
			return false;
		}
	}
	
	public FileHandle getIntroEnd () {
		return introEndFile;
	}
	
	public FileHandle getTextEnd() {
		return textEndFile;
	}

	public int[][] getTextInfo() {
		return textInfo;
	}
	
	public float[] getAudioInfo() {
		return audioInfo;
	}
	
	public boolean asBoucle() {
		return asBoucle;
	}
	
	public int[] getBoucleInfo () {
		return boucleInfo;
	}

	public FileHandle getBoucle(int frame) {
		return boucle[frame];
	}
	
	public void passAudio(){
		passAudio = true;
	}
	
	public void reniPassAudio(){
		passAudio = false;
	}
	
	public boolean getPassAudio () {
		return passAudio;
	}

	public void close() {
		apear = true;
		apearSens = true;
		img.setAlpha(1);
	}
}
