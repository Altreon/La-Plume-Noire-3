package SceneExtend;

public class Shadowgate extends SceneExtend{
	private static String sceneName = "Shadowgate";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = true;
	private static int[][] textInfo = {{1110, 98, 90, 124, 12, 60+13}, {1110, 98, 90, 26, 4, 60+18}};
	private static float[] audioInfo = {2.1F, 60+23.1F};
	
	public Shadowgate () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
