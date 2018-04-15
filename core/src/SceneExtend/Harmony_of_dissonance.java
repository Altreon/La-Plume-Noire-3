package SceneExtend;

public class Harmony_of_dissonance extends SceneExtend{
	private static String sceneName = "Harmony of dissonance";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.05F, 60+59.9F};
	
	public Harmony_of_dissonance () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
