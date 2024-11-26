package com.wallen.test.spring6.ioc.bean;

import com.wallen.test.spring6.ioc.annotation.Bean;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wallen
 * @date 2024/11/22 14:31
 */
public class AnnotationApplicationContext implements ApplicationContext {
    //创建map集合，放bean对象
    private        Map<Class, Object> beanFactory = new HashMap<>();
    private static String             rootPath;

    /**
     * 返回对象
     *
     * @param clazz
     * @return
     */
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    //创建有参构造，传递包路径，设置包扫码规则
    //当前包及其子包，哪个类有@Bean注解，把这个类通过反射实例化
    public AnnotationApplicationContext(String basePackage) throws Exception {
        //1.把.替换成\
        String packagePath = basePackage.replaceAll("\\.", "/");

        //2.获取包绝对路径
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (urls.hasMoreElements()) {
            URL    url      = urls.nextElement();
            String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
            //路径截取
            rootPath = filePath.substring(0, filePath.length() - packagePath.length());
            //包扫描
            loadBean(new File(filePath));
        }
    }

    /**
     * 包扫描，bean实例化
     *
     * @param file
     */
    private void loadBean(File file) throws Exception {
        //1.判断当前是否文件夹
        if (file.isDirectory()) {
            //2.获取文件夹里面所有内容
            File[] childrenFiles = file.listFiles();
            //3.判断文件夹里面为空，直接返回
            if (childrenFiles == null || childrenFiles.length == 0) {
                return;
            }
            //4.如果文件夹不为空，遍历文件夹所有内容
            for (File child : childrenFiles) {
                //4.1遍历得到每个file对象，继续判断，如果还是文件夹，返回第1步递归
                if (child.isDirectory()) {
                    loadBean(child);
                } else {
                    //4.2遍历得到file对象不是文件夹是文件
                    //4.3得到包路径+类名称-字符串截取
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length());
                    //4.4判断当前文件类型是否是.class
                    if (pathWithClass.contains(".class")) {
                        //4.5如果是.class类型，把路径\替换成. 把.class去掉
                        String allName = pathWithClass.replace("/", ".").replace(".class", "");
                        //4.6判断类上是否有@Bean注解，如果有使用反射进行实例化
                        //4.6.1获取类的class
                        Class<?> clazz = Class.forName(allName);
                        //4.6.2判断不是接口
                        if (!clazz.isInterface()) {
                            //4.6.3判断类上是否有注解@Bean
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            if (annotation != null) {
                                //4.6.4实例化
                                Object instance = clazz.getDeclaredConstructor().newInstance();
                                //4.7把对象实例化滞后，放到map集合beanFactory
                                //4.7.1当前的类如果有接口，让接口的class作为map的key
                                if (clazz.getInterfaces().length > 0) {
                                    beanFactory.put(clazz.getInterfaces()[0], instance);
                                } else {
                                    beanFactory.put(clazz, instance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
