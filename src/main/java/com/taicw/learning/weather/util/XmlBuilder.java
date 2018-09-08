package com.taicw.learning.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by taichangwei on 2018/9/1.
 */
public class XmlBuilder {
    public static Object xmlStrToObject(Class clazz, String xmlStr) throws Exception {

        JAXBContext context = JAXBContext.newInstance(clazz);

        //xml转为对象的接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Reader reader = new StringReader(xmlStr);
        Object xmlObject = unmarshaller.unmarshal(reader);

        reader.close();

        return xmlObject;
    }
}
