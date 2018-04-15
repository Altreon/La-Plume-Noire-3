package SceneExtend;

public class Items extends SceneExtend{
	private static String sceneName = "Items";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+52.2F};
	
	public Items () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
