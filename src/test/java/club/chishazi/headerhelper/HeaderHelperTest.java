package club.chishazi.headerhelper;

import com.alibaba.fastjson.JSONArray;
import junit.framework.TestCase;

public class HeaderHelperTest extends TestCase {


    public void testToTree() {
        String header = "[\n" +
                "    [\n" +
                "        {\n" +
                "            \"rowspan\": 3,\n" +
                "            \"colspan\": 1,\n" +
                "            \"id\": \"1\",\n" +
                "            \"label\": \"Date\",\n" +
                "            \"prop\": \"data\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rowspan\": 1,\n" +
                "            \"colspan\": 5,\n" +
                "            \"id\": \"2\",\n" +
                "            \"label\": \"Delivery Info\"\n" +
                "        }\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"rowspan\": 2,\n" +
                "            \"colspan\": 1,\n" +
                "            \"id\": \"3\",\n" +
                "            \"label\": \"Name\",\n" +
                "            \"prop\": \"name\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rowspan\": 1,\n" +
                "            \"colspan\": 4,\n" +
                "            \"id\": \"4\",\n" +
                "            \"label\": \"Address Info\"\n" +
                "        }\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"rowspan\": 1,\n" +
                "            \"colspan\": 1,\n" +
                "            \"id\": \"5\",\n" +
                "            \"label\": \"State\",\n" +
                "            \"prop\": \"state\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rowspan\": 1,\n" +
                "            \"colspan\": 1,\n" +
                "            \"id\": \"6\",\n" +
                "            \"label\": \"City\",\n" +
                "            \"prop\": \"city\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rowspan\": 1,\n" +
                "            \"colspan\": 1,\n" +
                "            \"id\": \"7\",\n" +
                "            \"label\": \"Address\",\n" +
                "            \"prop\": \"address\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rowspan\": 1,\n" +
                "            \"colspan\": 1,\n" +
                "            \"id\": \"8\",\n" +
                "            \"label\": \"Zip\",\n" +
                "            \"prop\": \"zip\"\n" +
                "        }\n" +
                "    ]\n" +
                "]";
        String newHeader = HeaderHelper.toTree(header);
        JSONArray headerArr = JSONArray.parseArray(newHeader);
        assertEquals(headerArr.size(), 2);
        assertNull(headerArr.getJSONObject(0).getJSONArray("children"));
        assertEquals(headerArr.getJSONObject(1).getJSONArray("children").size(), 2);
        assertEquals(headerArr.getJSONObject(1).getJSONArray("children").getJSONObject(1).getJSONArray("children").size(), 4);
    }

    public void testToRowspanColspan() {
        String header = "[\n" +
                "    {\n" +
                "        \"id\": \"1\",\n" +
                "        \"label\": \"Date\",\n" +
                "        \"prop\": \"data\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": \"2\",\n" +
                "        \"label\": \"Delivery Info\",\n" +
                "        \"children\":\n" +
                "        [\n" +
                "            {\n" +
                "                \"id\": \"3\",\n" +
                "                \"label\": \"Name\",\n" +
                "                \"prop\": \"name\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"4\",\n" +
                "                \"label\": \"Address Info\",\n" +
                "                \"children\":\n" +
                "                [\n" +
                "                    {\n" +
                "                        \"id\": \"5\",\n" +
                "                        \"label\": \"State\",\n" +
                "                        \"prop\": \"state\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"6\",\n" +
                "                        \"label\": \"City\",\n" +
                "                        \"prop\": \"city\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"7\",\n" +
                "                        \"label\": \"Address\",\n" +
                "                        \"prop\": \"address\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"id\": \"8\",\n" +
                "                        \"label\": \"Zip\",\n" +
                "                        \"prop\": \"zip\"\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "]";
        String newHeader = HeaderHelper.toRowspanColspan(header);
        JSONArray headerArr = JSONArray.parseArray(newHeader);
        assertEquals(headerArr.size(), 3);
        assertEquals(headerArr.getJSONArray(0).size(), 2);
        assertEquals(headerArr.getJSONArray(1).size(), 2);
        assertEquals(headerArr.getJSONArray(2).size(), 4);
    }
}