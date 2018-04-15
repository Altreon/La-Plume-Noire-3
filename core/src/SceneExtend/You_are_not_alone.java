package SceneExtend;

public class You_are_not_alone extends SceneExtend{
	private static String sceneName = "You are not alone";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2F, 60+28.7F};
	
	public You_are_not_alone () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
