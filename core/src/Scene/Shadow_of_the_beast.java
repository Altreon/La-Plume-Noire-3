package Scene;

import SceneExtend.SceneExtend;

import SceneExtend.Le_caveau_de_verre;
import SceneExtend.Alone_in_the_dark;

public class Shadow_of_the_beast extends Scene{
	private static String sceneName = "Shadow of the beast";
	private static boolean asBoucle = false;
	private static int mouvement = 1;
	private static boolean asEnd = false;
	private static boolean asClochette = true;
	private static boolean asBoite = true;
	private static SceneExtend[] sceneExtends = {new Le_caveau_de_verre(), new Alone_in_the_dark()};
	private static int[] boucleInfo = {0, 0};
	private static int[][] textInfo = {{1110, 98, 90, 124, 10, 60+54}, {1110, 98, 90, 26, 5, 120+13}};
	private static float[] audioInfo = {2.1F, 120+18.7F};
	
	public Shadow_of_the_beast () {
		super(sceneName, asBoucle, mouvement, asEnd, boucleInfo, textInfo, audioInfo, asClochette, asBoite, sceneExtends);
	}
}
