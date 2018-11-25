package io.development.millionlady.airapp_wear;

import java.util.ArrayList;

import io.development.millionlady.airapp_wear.XMLStructure.Channel;
import io.development.millionlady.airapp_wear.XMLStructure.Item;

public class XMLToObject {
    public static Channel toChannel(String xml) {
        Channel data = new Channel();

        xml = xml.replaceAll("\n", "").trim();
        //System.out.println(xml);

        xml = xml.substring(xml.indexOf("<channel>") + 9, xml.indexOf("</channel>"));
        //System.out.println(xml);

        data.setTitle(xml.substring(xml.indexOf("<title>") + 7, xml.indexOf("</title>")));

        while (xml.contains("</item>")) {
            data.addItemToList(toItem(xml.substring(xml.indexOf("<item>") + 6, xml.indexOf("</item>"))));
            xml = xml.substring(xml.indexOf("</item>") + 7, xml.length() - 1);
        }

        return data;
    }

    private static Item toItem(String xml) {
        Item item = new Item();

        item.setTitle(xml.substring(xml.indexOf("<title>") +  7, xml.indexOf("</title>")));
        item.setDescription(xml.substring(xml.indexOf("<description>") +  13, xml.indexOf("</description>")));
        item.setParam(xml.substring(xml.indexOf("<Param>") + 7, xml.indexOf("</Param>")));
        item.setConc(Double.parseDouble(xml.substring(xml.indexOf("<Conc>") + 6, xml.indexOf("</Conc>"))));
        item.setNowCastConc(Double.parseDouble(xml.substring(xml.indexOf("<NowCastConc>") + 13, xml.indexOf("</NowCastConc>"))));
        item.setAqi(Integer.parseInt(xml.substring(xml.indexOf("<AQI>") + 5, xml.indexOf("</AQI>"))));
        item.setDesc(xml.substring(xml.indexOf("<Desc>") + 6, xml.indexOf("</Desc>")));
        item.setReadingDataTime(xml.substring(xml.indexOf("<ReadingDateTime>") + 17, xml.indexOf("</ReadingDateTime>")));

        return item;
    }
}
