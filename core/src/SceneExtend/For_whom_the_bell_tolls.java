package SceneExtend;

import la.plume.noire3.LaPlumeNoire3;

public class For_whom_the_bell_tolls extends SceneExtend{
	private static String sceneName = "For whom the bell tolls";
	private static boolean asBrume = false;
	private static boolean asBoucle = true;
	private static int[] boucleInfo = {0, 60};
	private static boolean textDifferent = true;
	private static int[][] textInfo = {{1110, 196, 90, 26, 4, 120+38}};
	private static float[] audioInfo = {2.3F, 120+44.6F};
	
	public For_whom_the_bell_tolls () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
	
	public boolean isTextDifferent () {
		if(LaPlumeNoire3.canShowGoFointaineIfClochette()){
			return true;
		}else{
			return false;
		}
	}
}
