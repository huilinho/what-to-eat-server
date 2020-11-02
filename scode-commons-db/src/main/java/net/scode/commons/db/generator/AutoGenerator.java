/**
 *
 */
package net.scode.commons.db.generator;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import net.scode.commons.db.JdbcUtil;
import net.scode.commons.util.StringUtil;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * MyBatis DAO相关代码自动生产器<br>
 * 根据数据库表结构生成Bean和iBatis用的sqlMap文件
 *
 *
 */
public class AutoGenerator {

    private DataSource dataSource;

    /**
     * 构造器，必须有dataSource
     *
     * @param dataSource
     */
    public AutoGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 生成Bean和iBatis用的sqlMap文件<br>
     * 已包含generateEntity、generateDao、generateMapper、generateService四个动作
     *
     * @param config     相关配置
     * @param tableName  表名
     * @param entityName 实体对象名
     */
    public void generate(GeneratorConfig config, String tableName, String entityName) {
        generate(config, tableName, entityName, false);
    }

    /**
     * 生成Bean和iBatis用的sqlMap文件<br>
     * 已包含generateEntity、generateDao、generateMapper、generateService四个动作
     *
     * @param config     相关配置
     * @param tableName  表名
     * @param entityName 实体对象名
     * @param returnId   是否生成新增数据返回自增主键id值的方法
     */
    public void generate(GeneratorConfig config, String tableName, String entityName, boolean returnId) {
        generateEntity(config, tableName, entityName, returnId);
        generateDao(config, tableName, entityName);
        generateMapper(config, tableName, entityName);
        generateService(config, tableName, entityName);
    }

    /**
     * 生成实体对象
     *
     * @param config
     * @param tableName
     * @param entityName
     * @param returnId 主键id，确保主键名称为id
     */
    public void generateEntity(GeneratorConfig config, String tableName, String entityName, boolean returnId) {
        JdbcUtil jdbc = null;
        try {
            jdbc = new JdbcUtil(this.dataSource);
            String sql = "show full fields from `" + tableName + "`";
            jdbc.prepareStatement(sql);
            List<Map<String, Object>> list = jdbc.executeList();
            if (list != null) {
                String entityPath = GeneratorUtil.getEntityPath(config);
                String codeFullName = entityPath + "/" + entityName + ".java";
                System.out.println(codeFullName + "开始生成。");
                FileUtil.mkdir(entityPath);

                String buf = "package " + GeneratorUtil.getEntityPackage(config) + ";\n\n";
                buf += "import com.baomidou.mybatisplus.annotation.TableName;\n";
                buf += "import com.baomidou.mybatisplus.annotation.TableField;\n";
                buf += "import com.baomidou.mybatisplus.annotation.TableId;\n";
                buf += "import com.baomidou.mybatisplus.annotation.IdType;\n";
                buf += "import io.swagger.annotations.ApiModel;\n";
                buf += "import io.swagger.annotations.ApiModelProperty;\n";
                buf += "import lombok.Data;\n";
                boolean hasBigDecimal = false;
                String obuf = "\n/**\n * 表[" + tableName + "]对应实体类\n * \n";
                obuf += " * @author " + config.getAuthor() + " " + DateUtil.formatChineseDate(DateUtil.date(), false) + " \n */\n";
                obuf += "@Data\n";
                obuf += "@TableName(value = \"`" + tableName + "`\")\n";
                obuf += "@ApiModel(value = \"表[" + tableName + "]的实体类\")\n";
                obuf += "public class " + entityName + " {\n\n";
                for (Map<String, Object> row : list) {
                    // 统一转驼峰
                    String tableField = (String) row.get("COLUMN_NAME");
                    String field = StringUtil.toCamelCase(tableField);
                    String type = GeneratorUtil.getJavaType((String) row.get("COLUMN_TYPE"));
                    if (!hasBigDecimal && "BigDecimal".equals(type)) {
                        hasBigDecimal = true;
                    }
                    obuf += "    /**\n     * " + MapUtil.getStr(row, "COLUMN_COMMENT") + "\n     */\n";
                    // Swagger注解
                    obuf += "    @ApiModelProperty(value = \"" + MapUtil.getStr(row, "COLUMN_COMMENT") + "\", dataType = \"" + type + "\")\n";
                    obuf += "    @TableField(\"`" + tableField + "`\")\n";
                    if (returnId && "id".equals(tableField)) {
                        obuf += "    @TableId(value=\"`" + tableField + "`\",type = IdType.AUTO)\n";
                    }
                    obuf += "    private " + type + " " + field + ";\n\n";
                }
                if (hasBigDecimal) {
                    buf += "import java.math.BigDecimal;";
                }
                buf += obuf + "}";
                FileUtil.writeUtf8String(buf, codeFullName);
                System.out.println(codeFullName + "生成完毕。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jdbc != null) {
                jdbc.close();
            }
        }
    }

    /**
     * 生成Dao类
     *
     * @param config
     * @param tableName
     * @param entityName
     */
    public void generateDao(GeneratorConfig config, String tableName, String entityName) {
        try {
            String daoPath = GeneratorUtil.getDaoPath(config);
            String codeFullName = daoPath + "/" + GeneratorUtil.getDaoFileName(entityName);
            System.out.println(codeFullName + "开始生成。");
            FileUtil.mkdir(daoPath);
            // interface代码
            StringBuilder ibuf = new StringBuilder();
            ibuf.append("package " + GeneratorUtil.getDaoPackage(config) + ";\n\n");
            ibuf.append("import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n");
            ibuf.append("import " + GeneratorUtil.getEntityFullName(config, entityName) + ";\n\n");
            ibuf.append("/**\n * 表[" + tableName + "]对应实体类\n * \n");
            ibuf.append(" * @author " + config.getAuthor() + " " + DateUtil.formatChineseDate(DateUtil.date(), false) + " \n */\n");
            ibuf.append("public interface " + GeneratorUtil.getDaoName(entityName) + " extends BaseMapper<" + entityName + "> {\n\n");
            ibuf.append("}");
            FileUtil.writeUtf8String(ibuf.toString(), codeFullName);
            System.out.println(codeFullName + "生成完毕。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成sql映射文件
     *
     * @param config
     * @param tableName
     * @param entityName
     */
    public void generateMapper(GeneratorConfig config, String tableName, String entityName) {
        String mapperPath = GeneratorUtil.getMapperPath(config);
        String codeFullName = mapperPath + GeneratorUtil.getMapperFileName(config, tableName);
        System.out.println(codeFullName + "开始生成。");
        FileUtil.mkdir(mapperPath);

        String buf = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
        buf += "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n";
        buf += "<mapper namespace=\"" + GeneratorUtil.getDaoFullName(config, entityName) + "\">\n\n";
        buf += "</mapper>";
        FileUtil.writeUtf8String(buf, codeFullName);
        System.out.println(codeFullName + "生成完毕。");
    }

    /**
     * 生成service与实现
     *
     * @param config
     * @param tableName
     * @param entityName
     * @param returnId
     */
    public void generateService(GeneratorConfig config, String tableName, String entityName) {
        try {
            String servicePackageName = GeneratorUtil.getServicePackage(config);
            String servicePath = GeneratorUtil.getServicePath(config);
            String codeFullName = servicePath + "/" + GeneratorUtil.getServiceFileName(entityName);
            System.out.println(codeFullName + "开始生成。");
            FileUtil.mkdir(servicePath);
            String ibuf = "";
            ibuf += "package " + servicePackageName + ";\n\n";
            ibuf += "import com.baomidou.mybatisplus.extension.service.IService;\n";
            ibuf += "import " + GeneratorUtil.getEntityFullName(config, entityName) + ";\n";
            ibuf += "import org.springframework.beans.factory.annotation.Autowired;\n";
            ibuf += "import org.springframework.stereotype.Service;\n\n";
            ibuf += "/**\n * " + entityName + "对应Service\n * \n";
            ibuf += " * @author " + config.getAuthor() + " " + DateUtil.formatChineseDate(DateUtil.date(), false) + " \n */\n";
            ibuf += "public interface " + GeneratorUtil.getServiceName(entityName) + " extends IService<" + entityName + "> {\n\n";
            ibuf += "}";
            FileUtil.writeUtf8String(ibuf.toString(), codeFullName);
            System.out.println(codeFullName + "生成完毕。");

            String serviceImplPackageName = GeneratorUtil.getServiceImplPackage(config);
            servicePath = GeneratorUtil.getServiceImplPath(config);
            codeFullName = servicePath + "/" + GeneratorUtil.getServiceImplFileName(entityName);
            System.out.println(codeFullName + "开始生成。");
            FileUtil.mkdir(servicePath);
            ibuf = "";
            ibuf += "package " + serviceImplPackageName + ";\n\n";
            ibuf += "import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;\n";
            ibuf += "import " + GeneratorUtil.getDaoFullName(config, entityName) + ";\n";
            ibuf += "import " + GeneratorUtil.getEntityFullName(config, entityName) + ";\n";
            ibuf += "import " + servicePackageName + "." + GeneratorUtil.getServiceName(entityName) + ";\n";
            ibuf += "import org.springframework.stereotype.Service;\n\n";
            ibuf += "/**\n * " + entityName + "对应service实现\n * \n";
            ibuf += " * @author " + config.getAuthor() + " " + DateUtil.formatChineseDate(DateUtil.date(), false) + " \n */\n";
            ibuf += "@Service\n";
            ibuf += "public class " + GeneratorUtil.getServiceImplName(entityName)
                    + " extends ServiceImpl<" + GeneratorUtil.getDaoName(entityName) + "," + entityName + "> implements "
                    + GeneratorUtil.getServiceName(entityName) + " {\n\n";
            ibuf += "}";
            FileUtil.writeUtf8String(ibuf, codeFullName);
            System.out.println(codeFullName + "生成完毕。");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成返回自增id的sql
     *
     * @param config
     * @param tableName
     * @param entityName
     * @return
     */
    private String doReturnIdSql(GeneratorConfig config, String tableName, String entityName) {
        StringBuilder buf = new StringBuilder();
        JdbcUtil jdbc = null;
        try {
            jdbc = new JdbcUtil(this.dataSource);
            String sql = "show full fields from `" + tableName + "`";
            jdbc.prepareStatement(sql);
            List<Map<String, Object>> list = jdbc.executeList();
            if (list == null) {
                return buf.toString();
            }
            // 主键Map，支持联合主键、是否自增主键
            Map<String, Object> idRow = MapUtil.newHashMap();
            for (Map<String, Object> map : list) {
                if ("PRI".equals(MapUtil.getStr(map, "COLUMN_KEY"))) {
                    boolean autoIncrement = false;
                    if ("auto_increment".equals(MapUtil.getStr(map, "EXTRA"))) {
                        autoIncrement = true;
                    }
                    idRow.put(MapUtil.getStr(map, "COLUMN_NAME"), autoIncrement);
                }
            }
            if (idRow.size() == 0) {
                return buf.toString();
            }
            // 拼凑insert和update相关
            String fields = "";
            String insert = "";
            for (Map<String, Object> row : list) {
                String field = MapUtil.getStr(row, "COLUMN_NAME");
                // 自增主键不作insert和update
                if (!idRow.containsKey(field) || !MapUtil.getBool(idRow, field)) {
                    if (insert.length() > 0) {
                        fields += ",";
                        insert += ",";
                    }
                    fields += "`" + field + "`";
                    insert += "#{" + StringUtil.toCamelCase(field) + "}";
                }
            }
            String autoIncrIdField = null;
            for (String idField : idRow.keySet()) {
                if (idRow.containsKey(idField)) {
                    autoIncrIdField = idField;
                    break;
                }
            }
            // 插入
            buf.append("    <!-- 插入一条记录, 并携带返回自增主键id的值 -->\n");
            buf.append("    <insert id=\"insertReturnId\" parameterType=\"" + GeneratorUtil.getEntityFullName(config, entityName) + "\"");
            buf.append(" keyColumn=\"" + autoIncrIdField + "\" keyProperty=\"" + StringUtil.toCamelCase(autoIncrIdField) + "\" ");
            buf.append(" useGeneratedKeys=\"true\">\n");
            buf.append("        insert into `" + tableName + "`\n		(" + fields + ")\n");
            buf.append("        values (" + insert + ")\n");
            buf.append("    </insert>\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jdbc != null) {
                jdbc.close();
            }
        }
        return buf.toString();
    }

}
