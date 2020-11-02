/**
 *
 */
package net.scode.commons.db.generator;

import lombok.Data;

/**
 * 生成器相关配置
 *
 *
 */
@Data
public class GeneratorConfig {

    /**
     * 包前缀,如：net.scode.xxx
     */
    private String packagePrefix = "";

    /**
     * sqlMap目录，为null时默认java同一目录，可设置如/resources/
     */
    private String mapperDir = null;

    /**
     * 作者
     */
    private String author = "AutoGenerator";


}
