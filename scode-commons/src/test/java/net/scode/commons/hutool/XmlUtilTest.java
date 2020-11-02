package net.scode.commons.hutool;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.XmlUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * XML处理测试
 *
 * @author tanghuang 2020年02月26日
 */
public class XmlUtilTest {

    @Test
    public void parseTest() {
        String result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"//
                + "<returnsms>"//
                + "<returnstatus>Success</returnstatus>"//
                + "<message>ok</message>"//
                + "<remainpoint>1490</remainpoint>"//
                + "<taskID>885</taskID>"//
                + "<successCounts>1</successCounts>"//
                + "</returnsms>";
        Document docResult = XmlUtil.parseXml(result);
        String elementText = XmlUtil.elementText(docResult.getDocumentElement(), "returnstatus");
        Assert.assertEquals("Success", elementText);
    }

    @Test
    @Ignore
    public void writeTest() {
        String result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"//
                + "<returnsms>"//
                + "<returnstatus>Success（成功）</returnstatus>"//
                + "<message>ok</message>"//
                + "<remainpoint>1490</remainpoint>"//
                + "<taskID>885</taskID>"//
                + "<successCounts>1</successCounts>"//
                + "</returnsms>";
        Document docResult = XmlUtil.parseXml(result);
        XmlUtil.toFile(docResult, "e:/aaa.xml", "utf-8");
    }

    @Test
    public void xpathTest() {
        String result = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"//
                + "<returnsms>"//
                + "<returnstatus>Success（成功）</returnstatus>"//
                + "<message>ok</message>"//
                + "<remainpoint>1490</remainpoint>"//
                + "<taskID>885</taskID>"//
                + "<successCounts>1</successCounts>"//
                + "</returnsms>";
        Document docResult = XmlUtil.parseXml(result);
        Object value = XmlUtil.getByXPath("//returnsms/message", docResult, XPathConstants.STRING);
        Assert.assertEquals("ok", value);
    }

    @Test
    public void xmlToMapTest() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>"//
                + "<returnsms>"//
                + "<returnstatus>Success</returnstatus>"//
                + "<message>ok</message>"//
                + "<remainpoint>1490</remainpoint>"//
                + "<taskID>885</taskID>"//
                + "<successCounts>1</successCounts>"//
                + "<newNode><sub>subText</sub></newNode>"//
                + "</returnsms>";
        Map<String, Object> map = XmlUtil.xmlToMap(xml);
        Console.log(map);

        Assert.assertEquals(6, map.size());
        Assert.assertEquals("Success", map.get("returnstatus"));
        Assert.assertEquals("ok", map.get("message"));
        Assert.assertEquals("1490", map.get("remainpoint"));
        Assert.assertEquals("885", map.get("taskID"));
        Assert.assertEquals("1", map.get("successCounts"));
        Assert.assertEquals("subText", ((Map<?, ?>) map.get("newNode")).get("sub"));
    }

    @Test
    public void xmlToMapTest2() {
        String xml = "<root><name>张三</name><name>李四</name></root>";
        Map<String, Object> map = XmlUtil.xmlToMap(xml);

        Assert.assertEquals(1, map.size());
        Assert.assertEquals(CollUtil.newArrayList("张三", "李四"), map.get("name"));
    }

    @Test
    public void mapToXmlTest() {
        Map<String, Object> map = MapBuilder.create(new LinkedHashMap<String, Object>())//
                .put("name", "张三")//
                .put("age", 12)//
                .put("game", MapUtil.builder(new LinkedHashMap<String, Object>()).put("昵称", "Looly").put("level", 14).build())//
                .build();
        Document doc = XmlUtil.mapToXml(map, "user");
        // Console.log(XmlUtil.toStr(doc, false));
        Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"//
                        + "<user>"//
                        + "<name>张三</name>"//
                        + "<age>12</age>"//
                        + "<game>"//
                        + "<昵称>Looly</昵称>"//
                        + "<level>14</level>"//
                        + "</game>"//
                        + "</user>", //
                XmlUtil.toStr(doc, false));
    }

    @Test
    @Ignore
    public void readTest() {
        Document doc = XmlUtil.readXML("test.xml");
        Assert.assertNotNull(doc);
    }

    @Test
    public void mapToXmlTestWithOmitXmlDeclaration() {
        Map<String, Object> map = MapBuilder.create(new LinkedHashMap<String, Object>())
                .put("name", "ddatsh")
                .build();
        String xml = XmlUtil.mapToXmlStr(map, true);
        Assert.assertEquals(xml, "<xml><name>ddatsh</name></xml>");
    }

}
