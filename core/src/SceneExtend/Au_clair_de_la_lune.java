package SceneExtend;

public class Au_clair_de_la_lune extends SceneExtend{
	private static String sceneName = "Au clair de la lune";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2F, 120+23.4F};
	
	public Au_clair_de_la_lune () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
