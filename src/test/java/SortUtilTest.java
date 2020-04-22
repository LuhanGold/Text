import com.luhan.utils.FileUtil;
import com.luhan.utils.SortUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h2>SortUtils测试类</h2>
 *
 * @author luHan
 * @create 2020/4/22 10:49
 * @since 1.0.0
 */
public class SortUtilTest {

    @Test
    public void testNaturalSort(){
        String directory = "/Users/luhan/学习/学习视频/193、Spring Cloud Alibaba微服务从入门到进阶（全）（不加密）";
        List<File> files = FileUtil.getFilesByDirectory(directory);
        List<String> list = files.stream().map(File::getName).collect(Collectors.toList());;// Arrays.asList("1IM0.dcm", "IM13.dcm", "IM12.dcm", "IM11.dcm", "IM2.dcm", "IM20.dcm");
        SortUtil.naturalSort(list);
        list.forEach(System.out::println);
    }
}
