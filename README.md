# LUtils
## 目前已经实现的工具类如下:

## AnnotationUtil(注解相关的工具类)
1. updateAnnotationValue:修改类指定注解的指定属性的值
2. updateMethodAnnotationValue:修改类方法指定的注解的值
3. updateFieldAnnotationValue:修改类属性指定的注解的值
4. **updateAnnotationValue:修改指定注解的值**

## ArabicToChineseUtils(阿拉伯数字转化为汉字的工具类)
1. formatInteger:将阿拉伯数字转化为汉字

## DataUtils(数据工具类)
1. **datePaging:数据集合的分页方法，根据传入总共的数据跟页码，返回页码所需要显示多少条的数据**
2. getPageSize:求出一共有多少页
3. countPercentReturnString:求出两数的百分比值，默认精确到百分比的后两位
4. countPercentReturnInt:求出两数的百分比值,返回两数的百分比不带有%符号的，为Int类型
5. **isCardID:判断cardID是不是身份证**
6. concat:将多个数组合并成一个数组
7. getNumber:从字符串中获取数字

## DateUtils(日期工具类)
1. formDate:获取当前时间，默认格式为"yyyy-MM-dd HH:mm:ss"
2. timesStampToDate:将时间戳转化为日期
3. dateToTimestamp:将指定的日期转化为时间戳

## FileUtils(文件相关的工具类)
1. **getFilesBySuffixName:通过指定文件夹下的的指定后缀名称的文件**
2. readFile:读取txt文件里面的内容
3. writeFile:将内容写出到文件中
4. **getFilesByDirectory:获取指定文件路径下的所有文件**

## NetWorkUtils(网络工具类)
1. getJsonByURL:获取网络上的JSON数据

## PropertiesUtils(properties文件的工具类)
1. parseProperties:读取pro文件内容