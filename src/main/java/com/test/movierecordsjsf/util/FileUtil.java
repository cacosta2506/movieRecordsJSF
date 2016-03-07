package com.test.movierecordsjsf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    /**
     *
     * @param name
     * @param content
     * @param url
     * @return
     * @throws Exception
     */
    public static boolean writeFile(String name, byte[] content,
            String url) throws Exception {
        boolean correct = false;

        FileOutputStream fos = null;
        File f = new File(url);

        boolean exist = false;
        String[] arrayUrl = url.split("\\\\");
        String auxUrl = "";
        for (int i = 1; i < arrayUrl.length; i++) {
            auxUrl = auxUrl.concat("\\" + arrayUrl[i]);
            f = new File(auxUrl);
            if (!f.exists()) {
                if (f.mkdir()) {
                    exist = true;
                    continue;
                } else {
                    JsfUtil.msgError("The directory couldn't be created.");
                }
            } else {
                exist = true;
            }
        }

        if (exist) {
            File f2 = new File(f.getAbsolutePath() + "\\" + name);
            if (!f2.exists()) {
                try {
                    f2.createNewFile();
                    fos = new FileOutputStream(f2);
                    fos.write(content);
                    correct = true;
                    fos.close();
                } catch (IOException e) {
                    JsfUtil.msgError("Error while creating the file.");
                    throw new Exception(e.getMessage());
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JsfUtil.msgAdvert("The file you are trying to upload already exists.");
            }
        }
        return correct;
    }

    public static void deleteMovie(String origen) {

        try {
            File inFile = new File(origen);

            FileInputStream in = new FileInputStream(inFile);
            in.close();

            File file = new File(origen);
            if (file.exists()) {
                file.delete();
            }

        } catch (IOException e) {
            JsfUtil.msgError("Input/ Output error");
        }
    }

}
