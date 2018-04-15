package SceneExtend;

public class Ombre_et_lumiere extends SceneExtend{
	private static String sceneName = "Ombre et lumiere";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.15F, 60+22.2F};
	
	public Ombre_et_lumiere () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
