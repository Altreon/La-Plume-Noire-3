package SceneExtend;

import la.plume.noire3.LaPlumeNoire3;

public class Symphony_of_the_night extends SceneExtend{
	private static String sceneName = "Symphony of the night";
	private static boolean asBrume = true;
	private static boolean asBoucle = true;
	private static int[] boucleInfo = {0, 60};
	private static boolean textDifferent = true;
	private static int[][] textInfo = {{1110, 196, 90, 26, 4, 180+40}};
	private static float[] audioInfo = {2.3F, 180+46F};
	
	public Symphony_of_the_night () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
	
	public boolean isTextDifferent () {
		if(LaPlumeNoire3.canShowGoFointaineIfBoite()){
			return true;
		}else{
			return false;
		}
	}
}
