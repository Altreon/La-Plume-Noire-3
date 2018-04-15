package SceneExtend;

public class Sword_of_hope extends SceneExtend{
	private static String sceneName = "Sword of hope";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+22.8F};
	
	public Sword_of_hope () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
