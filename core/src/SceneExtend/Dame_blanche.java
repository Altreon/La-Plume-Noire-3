package SceneExtend;

public class Dame_blanche extends SceneExtend{
	private static String sceneName = "Dame blanche";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.15F, 120+36.5F};
	
	public Dame_blanche () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
