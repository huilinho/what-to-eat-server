/**
 *
 */
package net.scode.commons.db.generator;

import net.scode.commons.util.StringUtil;

/**
 * 生成器工具类
 *
 *
 */
public class GeneratorUtil {

    /**
     * 可以转为int的数据库字段类型
     */
    private static final String[] intType = {"int", "tinyint", "smallint"};

    /**
     * 获得项目src/main的绝对路径
     *
     * @return
     */
    public static String getMainPath() {
        return System.getProperty("user.dir").replace('\\', '/') + "/src/main/";
    }

    /**
     * 获得项目src/main/java的绝对路径
     *
     * @return
     */
    public static String getSrcPath() {
        return getMainPath() + "java/";
    }

    /**
     * 获得po对象包名
     *
     * @param config
     * @return
     */
    public static String getEntityPackage(GeneratorConfig config) {
        return config.getPackagePrefix() + ".po";
    }

    /**
     * 获得po对象包路径
     *
     * @param config
     * @return
     */
    public static String getEntityPath(GeneratorConfig config) {
        return getSrcPath() + getEntityPackage(config).replaceAll("\\.", "/");
    }

    /**
     * 获得po对象全名(包括包名)
     *
     * @param config
     * @param entityName
     * @return
     */
    public static String getEntityFullName(GeneratorConfig config, String entityName) {
        return getEntityPackage(config) + "." + entityName;
    }

    /**
     * 获得po对象名
     *
     * @param entityName
     * @return
     */
    public static String getDaoName(String entityName) {
        return entityName + "Dao";
    }

    /**
     * 获得po对象文件名
     *
     * @param entityName
     * @return
     */
    public static String getDaoFileName(String entityName) {
        return getDaoName(entityName) + ".java";
    }

    /**
     * 获得dao包名
     *
     * @param config
     * @return
     */
    public static String getDaoPackage(GeneratorConfig config) {
        return config.getPackagePrefix() + ".dao";
    }

    /**
     * 获得dao路径
     *
     * @param config
     * @return
     */
    public static String getDaoPath(GeneratorConfig config) {
        return getSrcPath() + getDaoPackage(config).replaceAll("\\.", "/");
    }

    /**
     * 获得dao全名（包括包名）
     *
     * @param config
     * @param entityName
     * @return
     */
    public static String getDaoFullName(GeneratorConfig config, String entityName) {
        return getDaoPackage(config) + "." + getDaoName(entityName);
    }

    /**
     * 获得Mybatis映射文件路径
     *
     * @param config
     * @return
     */
    public static String getMapperPath(GeneratorConfig config) {
        if (StringUtil.isEmpty(config.getMapperDir())) {
            return getSrcPath() + getDaoPackage(config).replaceAll("\\.", "/");
        } else {
            return getMainPath() + config.getMapperDir();
        }
    }

    /**
     * 获得Mybatis映射文件名
     *
     * @param config
     * @param tableName
     * @return
     */
    public static String getMapperFileName(GeneratorConfig config, String tableName) {
        return tableName + ".xml";
    }

    /**
     * 获得service的包名
     *
     * @param config
     * @return
     */
    public static String getServicePackage(GeneratorConfig config) {
        return config.getPackagePrefix() + ".service";
    }

    /**
     * 获得service的包路径
     *
     * @param config
     * @return
     */
    public static String getServicePath(GeneratorConfig config) {
        return getSrcPath() + getServicePackage(config).replaceAll("\\.", "/");
    }

    /**
     * 获得service名
     *
     * @param entityName
     * @return
     */
    public static String getServiceName(String entityName) {
        return entityName + "Service";
    }

    /**
     * 获得service文件名
     *
     * @param entityName
     * @return
     */
    public static String getServiceFileName(String entityName) {
        return getServiceName(entityName) + ".java";
    }

    /**
     * 获得service实现的包名
     *
     * @param config
     * @return
     */
    public static String getServiceImplPackage(GeneratorConfig config) {
        return config.getPackagePrefix() + ".service.impl";
    }

    /**
     * 获得service实现类文件名
     *
     * @param entityName
     * @return
     */
    public static String getServiceImplName(String entityName) {
        return entityName + "ServiceImpl";
    }

    /**
     * 获得service实现类文件名
     *
     * @param entityName
     * @return
     */
    public static String getServiceImplFileName(String entityName) {
        return getServiceImplName(entityName) + ".java";
    }

    /**
     * 获得service实现的包路径
     *
     * @param config
     * @return
     */
    public static String getServiceImplPath(GeneratorConfig config) {
        return getSrcPath() + getServiceImplPackage(config).replaceAll("\\.", "/");
    }

    /**
     * 判断数据库字段对应的Java类型
     *
     * @param str
     * @return
     */
    public static String getJavaType(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        str = str.toLowerCase();
        for (String type : intType) {
            if (str.startsWith(type)) {
                return "int";
            }
        }
        if (str.startsWith("bigint")) {
            return "Long";
        } else if (str.startsWith("float") || str.startsWith("decimal")) {
            return "BigDecimal";
        } else if (str.startsWith("double")) {
            return "Double";
        } else if (str.startsWith("date") || str.startsWith("time")) {
            return "java.util.Date";
        } else {
            return "String";
        }
    }

}
