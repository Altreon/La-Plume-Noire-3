package SceneExtend;

public class Light_my_fire extends SceneExtend{
	private static String sceneName = "Light my fire";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+8.9F};
	
	public Light_my_fire () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
