package com.zzg.mybatis.generator.plugins;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

public class OracleVoCommentGenerator implements CommentGenerator {
    /**
     * properties配置文件
     */
    private Properties properties;

    /*
     * 父类时间
     */
    private boolean suppressDate;

    /**
     * 父类所有注释
     */
    private boolean suppressAllComments;

    /**
     * 当前时间
     */
    private String currentDateStr;

    public OracleVoCommentGenerator() {
        super();
        properties = new Properties();
        currentDateStr = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    /**
     * Description (Java类的类注释)
     * @author lk
     * @date 2019/12/19 23:05
     * @param innerClass :
     * @param introspectedTable :
     * @return void
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        innerClass.addJavaDocLine(String.format("@ApiModel(name = \"%s\")", introspectedTable.getRemarks()));
    }

    /**
     * Description (为类添加注释)
     * @author lk
     * @date 2019/12/19 23:05
     * @param innerClass :
     * @param introspectedTable :
     * @param markAsDoNotDelete :
     * @return void
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        innerClass.addJavaDocLine(String.format("@ApiModel(name = \"%s\")", introspectedTable.getRemarks()));
    }

    /**
     * Description (Mybatis的Mapper.xml文件里面的注释)
     * @author lk
     * @date 2019/12/19 23:05
     * @param xmlElement :
     * @return void
     */
    @Override
    public void addComment(XmlElement xmlElement) {

    }
    /**
     * Description (从该配置中的任何属性添加此实例的属性CommentGenerator配置。
     *                   这个方法将在任何其他方法之前被调用。)
     * @author lk
     * @date 2019/12/19 23:04
     * @param properties :
     * @return void
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }
    /**
     * Description (此方法返回格式化的日期字符串以包含在Javadoc标记中和XML注释。 如果您不想要日期，则可以返回null在这些文档元素中。)
     * @author lk
     * @date 2019/12/19 23:06
     * @return java.lang.String
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = currentDateStr;
        }
        return result;
    }
    /**
     * Description (此方法为其添加了自定义javadoc标签。)
     * @author lk
     * @date 2019/12/19 23:06
     * @param javaElement :
     * @param markAsDoNotDelete :
     * @return void
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
    }


    /**
     * 为枚举添加注释
     */
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
    }

    /**
     * Java属性注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        field.addJavaDocLine(String.format("@ApiModelProperty(name = \"%s\")", introspectedTable.getRemarks()));
    }

    /**
     * 为字段添加注释
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine(String.format("@ApiModelProperty(name = \"%s\")", introspectedColumn.getRemarks()));
    }

    /**
     * 普通方法的注释，这里主要是XXXMapper.java里面的接口方法的注释
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    }


    /**
     * 给getter方法加注释
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
    }

    /**
     * 给Java文件加注释，这个注释是在文件的顶部，也就是package上面。
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
    }

    /**
     * 为模型类添加注释
     */
    @Override
    public void addModelClassComment(TopLevelClass arg0, IntrospectedTable arg1) {
        arg0.addImportedType("lombok.Data");
        arg0.addJavaDocLine(String.format("@ApiModel(name = \"%s\")", arg1.getRemarks()));
    }

    /**
     * 为调用此方法作为根元素的第一个子节点添加注释。
     */
    @Override
    public void addRootComment(XmlElement arg0) {
    }


    /**
     * 给setter方法加注释
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,IntrospectedColumn introspectedColumn) {
    }
}