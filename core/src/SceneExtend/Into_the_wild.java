package SceneExtend;

public class Into_the_wild extends SceneExtend{
	private static String sceneName = "Into the wild";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.08F, 60+39.1F};
	
	public Into_the_wild () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
