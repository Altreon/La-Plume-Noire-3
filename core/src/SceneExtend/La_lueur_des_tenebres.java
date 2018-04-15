package SceneExtend;

public class La_lueur_des_tenebres extends SceneExtend{
	private static String sceneName = "La lueur des tenebres";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.15F, 60+11.9F};
	
	public La_lueur_des_tenebres () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
