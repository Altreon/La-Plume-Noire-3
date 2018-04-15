package SceneExtend;

public class Dying_light extends SceneExtend{
	private static String sceneName = "Dying light";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.07F, 60+4.7F};
	
	public Dying_light () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
