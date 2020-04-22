import com.luhan.utils.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * <h2>FileUtils的测试类</h2>
 *
 * @author luHan
 * @create 2020/4/22 10:40
 * @since 1.0.0
 */
public class FileUtilTest {

    @Test// 测试rejectCharacter方法
    public void testRejectCharacter(){
        String directory = "/Users/luhan/学习/学习视频/193、Spring Cloud Alibaba微服务从入门到进阶（全）（不加密）";
        List<File> files = FileUtil.getFilesByDirectory(directory);
        FileUtil.rejectCharacter(files, "【瑞客论坛 www.ruike1.com】");
    }
}
