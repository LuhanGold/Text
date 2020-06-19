package com.luhan.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

/**
 * 〈视频文件相关工具类〉<br>
 *
 * @author luHan
 * @create 2019-10-14 17:19
 * @since 1.0.0
 */
public class VideoUtil {

    /**
     * 获取视频文件的第几帧图片
     * 依赖FrameToBufferedImage方法：将frame转换为bufferedImage对象
     *
     * @param videoFilePath 视频文件的路径
     * @param frameNum 第几帧图片
     */
    public static BufferedImage grabberVideoFramer(String videoFilePath,int frameNum) throws Exception {
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        FFmpegFrameGrabber fFmpegFrameGrabber = null;
        try {
            // 获取视频文件
            fFmpegFrameGrabber = new FFmpegFrameGrabber(videoFilePath);
            fFmpegFrameGrabber.start();

            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();

            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
				/*
				对视频的第五帧进行处理
				 */
                if (frame != null && flag == frameNum) {
                    return FrameToBufferedImage(frame);
                }
                flag++;
            }
        } catch (Exception e) {
            throw new Exception("文件可能已经损坏", e);
        }finally {
            if(null != fFmpegFrameGrabber){
                fFmpegFrameGrabber.stop();
                fFmpegFrameGrabber.close();
            }
        }
        return null;
    }

    /**
     * 将Frame转换为图片
     * @param frame Frame
     * @return BufferedImage
     */
    public static BufferedImage FrameToBufferedImage(Frame frame) {
        //创建BufferedImage对象
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bufferedImage = converter.getBufferedImage(frame);
        return bufferedImage;
    }
}

