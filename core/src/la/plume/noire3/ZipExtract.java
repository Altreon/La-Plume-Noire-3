package la.plume.noire3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.badlogic.gdx.Gdx;

public class ZipExtract implements Runnable{
	private String packageName;
	private File obbLocation;
	private File obbDestination;
	private boolean isInstall = false;
	private static double zipSize = 0;
	private static double unzipSize = 0;
	
	public ZipExtract (String packName) {
		packageName = packName;
		File searchStorage = null;
		
		File storage = new File("/storage");
		if(storage.exists()){
			searchStorage = storage;
		}else{
			storage = new File("/mnt");
			if(storage.exists()){
				searchStorage = storage;
			}
		}
		
		for(File file : searchStorage.listFiles()){
			File f = new File(file.getAbsolutePath() + "/Android/obb/" + packageName + File.separator + "main.3" + "." + packageName + ".obb");
			if(f.exists()){
				zipSize = f.length();
				obbLocation = f;
				isInstall = true;
			}
		}
	}
	
	public boolean isInstall () {
		return isInstall;
	}
	
	public boolean obbDestinationExist(){
		obbDestination = new File(Gdx.files.getLocalStoragePath() + "/Scene");
		if(obbDestination.exists()){
			return true;
		}else{
			return false;
		}
	}
	
	public int getProgress(){
		if(obbLocation.exists()){
			return (int)(1280*(unzipSize/zipSize));
		}else{
			return 1280;
		}
	}

	@Override
	public void run() {
        try {
        	obbDestinationExist();
        	if(!obbDestination.exists()){
        		obbDestination.mkdir();
        	}
        	Gdx.app.log("Extract zip", obbLocation.getAbsolutePath());
        	Gdx.app.log("Extract to", obbDestination.getAbsolutePath());
        	unzip(obbLocation, obbDestination);
        	new File(obbLocation.getPath()).delete();
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
        LaPlumeNoire3.endInstall();
	}
	
	public static void unzip(File zipFile, File targetDirectory) throws IOException {
	    ZipInputStream zis = new ZipInputStream(
	            new BufferedInputStream(new FileInputStream(zipFile)));
	    try {
	        ZipEntry ze;
	        int count;
	        byte[] buffer = new byte[8192];
	        while ((ze = zis.getNextEntry()) != null) {
	            File file = new File(targetDirectory, ze.getName());
	            Gdx.app.log("Extract File", file.getAbsolutePath());
	            File dir = ze.isDirectory() ? file : file.getParentFile();
	            if (!dir.isDirectory() && !dir.mkdirs())
	                throw new FileNotFoundException("Failed to ensure directory: " +
	                        dir.getAbsolutePath());
	            if (ze.isDirectory())
	                continue;
	            FileOutputStream fout = new FileOutputStream(file);
	            try {
	                while ((count = zis.read(buffer)) != -1)
	                    fout.write(buffer, 0, count);
	            } finally {
	                fout.close();
	            }
	            unzipSize += ze.getCompressedSize();
	        }
	    } finally {
	        zis.close();
	    }
	}
}
