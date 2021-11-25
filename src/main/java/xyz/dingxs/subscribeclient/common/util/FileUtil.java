package xyz.dingxs.subscribeclient.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 文件操作工具类
 *
 * @author dingxs
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static String read(String fileName) {
        FileReader fileReader = null;
        BufferedReader reader = null;
        String res = null;
        try {
            fileReader = new FileReader(fileName);
            reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            StringBuilder config = new StringBuilder(line);
            while (line != null) {
                line = reader.readLine();
                config.append(line);
            }
            res = config.toString();
        } catch (Exception e) {
            logger.error("file read Exception", e);
        } finally {
            try {
                assert fileReader != null;
                fileReader.close();
                assert reader != null;
                reader.close();
            } catch (Exception e) {
                logger.error("close Exception", e);
            }
        }
        return res;
    }

    public static void write(String fileName, String content) {
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            fileWriter = new FileWriter(fileName);
            writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.flush();

        } catch (Exception e) {
            logger.error("file read Exception", e);
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (Exception e) {
                logger.error("close Exception", e);
            }
        }

    }
}
