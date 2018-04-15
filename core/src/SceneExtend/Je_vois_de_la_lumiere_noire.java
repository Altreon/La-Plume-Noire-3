package SceneExtend;

public class Je_vois_de_la_lumiere_noire extends SceneExtend{
	private static String sceneName = "Je vois de la lumiere noire";
	private static boolean asBrume = false;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2F, 60+20.2F};
	
	public Je_vois_de_la_lumiere_noire () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
