package SceneExtend;

public class Rope extends SceneExtend{
	private static String sceneName = "Rope";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 180+2.9F};
	
	public Rope () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
