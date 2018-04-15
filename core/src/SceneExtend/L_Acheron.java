package SceneExtend;

public class L_Acheron extends SceneExtend{
	private static String sceneName = "L Acheron";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+29.5F};
	
	public L_Acheron () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
