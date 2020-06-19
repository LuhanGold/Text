/**
 *
 */
package com.luhan.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**@effect {文件相关的工具类}
 * @author luhan
 * @date 2017年4月1日 下午3:06:46
 */
public class FileUtil {

    /**私有的构造方法*/
    private FileUtil() {
    }

    ;

    /**
     * 通过指定文件夹下的的指定后缀名称的文件
     * @param filePath 文件夹路径
     * @param suffixName 后缀名称
     * @return 返回装有条件满足的文件的数组
     * @throws IllegalArgumentException 抛出不是文件夹的异常
     */
    public static File[] getFilesBySuffixName(String filePath, String suffixName) throws IllegalArgumentException {
        //创建一个空的文件的数组用来装查找到满足条件的文件
        File[] files = new File[0];
        //通过文件路径获取文件
        File folder = new File(filePath);
        //判断这个文件是不是文件夹，如果是文件夹就继续，否则直接return
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("请输入文件夹的路径");
        } else {
            files = folder.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    //判断是不是文件，并且后缀是不是满足要求
                    if (pathname.isFile() && pathname.getName().indexOf(suffixName) > -1) {
                        return true;
                    }
                    return false;
                }
            });
            return files;
        }
    }

    /**
     * 读取txt文件里面的内容
     * @param filePath 文件地址
     * @return 返回读取后的内容
     * @throws IllegalArgumentException 抛出不是文件异常
     * @throws IOException
     */
    public static String readFile(String filePath) throws IllegalArgumentException, IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("没有这个文件");
        }
        //用来存储文件里面读取的内容
        StringBuffer content = new StringBuffer();
        //用来保存文件读取的一行内容
        String tempStr = "";
        //文件读取流
        FileInputStream inputStream = new FileInputStream(file);
        //文件读取操作对象流,设置读取的编码格式为GBK
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
        //循环读取，如果当读取到的内容为null是就停止读取
        while ((tempStr = reader.readLine()) != null) {
            content.append(tempStr);
        }

        //关闭读取流
        reader.close();
        inputStream.close();

        //最后返回读取的内容
        return content.toString();
    }

    /**
     * 写文件的方法
     * @param filePath 需要写到那个文件上
     * @param content 写的内容是什么
     * @param isReplace 是否追加到已经有的内容后面，默认是的
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void writeFile(String filePath, String content, boolean isReplace) throws UnsupportedEncodingException, IOException {
        //通过传入的文件路径来新建一个文件
        File file = new File(filePath);
        //判断有没有这个文件，没有则则创建
        if (!file.exists()) {
            file.createNewFile();
        }
        //获取文件写流
        FileOutputStream outputStream = new FileOutputStream(file, isReplace);
        //获取文件写的操作对象
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        //写文件内容
        writer.write(content);

        //关闭流
        writer.flush();
        writer.close();
    }

    /**
     * 文件写内容
     * @param filePath 需要写到那个文件上
     * @param content 写的内容是什么
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void writeFile(String filePath, String content) throws UnsupportedEncodingException, IOException {
        writeFile(filePath, content, true);
    }

    /**
     * 获取指定文件路径下的所有文件
     * @param directoryPath 文件夹
     */
    public static List<File> getFilesByDirectory(String directoryPath) {
        List<File> fileList = new ArrayList<>();
        File file = new File(directoryPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                fileList.addAll(getFilesByDirectory(file1.getAbsolutePath()));
            }
        } else {
            fileList.add(file);
        }
        return fileList;
    }

    /**
     * 剔除文件名称中的指定字符
     * <p>
     *     例如：a_1[2].txt ==剔除[2]==> a_1.txt
     * </p>
     * @param sourceFiles 源文件集合
     * @param character 指定剔除的字符
     */
    public static void rejectCharacter(List<File> sourceFiles, String character) {
        sourceFiles.stream().forEach(file -> {
            // 获取文件的绝对路径(包含文件名称)
            String absolutePath = file.getAbsolutePath();
            // 获取文件名称
            String fileName = file.getName();
            // 获取文件的绝对路径(不包含文件名称)
            String absolutePathNotFileName = absolutePath.replace(fileName, "");
            // 剔除文件名称中指定的字符
            String newFileName = fileName.replace(character, "");
            // 新的文件的绝对路径
            String newAbsolutePath = absolutePathNotFileName + newFileName;
            // 文件重命名
            file.renameTo(new File(newAbsolutePath));
        });
    }

    /**
     * 将指定的文件移动到指定的目录中
     * @param files 源文件集合
     * @param toFilePath 目的路径
     * @param fileType 文件类型
     */
    @Deprecated
    public static void removeToByType(List<File> files, String toFilePath, String fileType) {
        // 如果目的目录路径不存在，则进行创建
        File toFilePathDesc = new File(toFilePath);
        if (!toFilePathDesc.exists()) {
            toFilePathDesc.mkdirs();
        }
    }
}
