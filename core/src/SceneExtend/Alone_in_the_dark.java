package SceneExtend;

public class Alone_in_the_dark extends SceneExtend{
	private static String sceneName = "Alone in the dark";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.05F, 120+14.3F};
	
	public Alone_in_the_dark () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
