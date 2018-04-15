package SceneExtend;

public class Branche_morte extends SceneExtend{
	private static String sceneName = "Branche morte";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+39.5F};
	
	public Branche_morte () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
