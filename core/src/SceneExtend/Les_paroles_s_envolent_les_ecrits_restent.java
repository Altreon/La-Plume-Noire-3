package SceneExtend;

public class Les_paroles_s_envolent_les_ecrits_restent extends SceneExtend{
	private static String sceneName = "Les paroles s envolent, les ecrits restent";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 60+51.6F};
	
	public Les_paroles_s_envolent_les_ecrits_restent () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
