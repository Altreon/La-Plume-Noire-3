package SceneExtend;

public class Shining extends SceneExtend{
	private static String sceneName = "Shining";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.2F, 51F};
	
	public Shining () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
