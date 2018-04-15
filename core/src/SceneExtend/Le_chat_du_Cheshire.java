package SceneExtend;

public class Le_chat_du_Cheshire extends SceneExtend{
	private static String sceneName = "Le chat du Cheshire";
	private static boolean asBrume = true;
	private static boolean asBoucle = false;
	private static int[] boucleInfo = {0, 0};
	private static boolean textDifferent = false;
	private static int[][] textInfo = {{}};
	private static float[] audioInfo = {2.1F, 120+11.9F};
	
	public Le_chat_du_Cheshire () {
		super(sceneName, asBrume, asBoucle, boucleInfo, textDifferent, textInfo, audioInfo);
	}
}
