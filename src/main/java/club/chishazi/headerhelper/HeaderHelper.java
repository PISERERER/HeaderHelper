package club.chishazi.headerhelper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Optional;

//测试
public class HeaderHelper {

    //将[rowspan-colspan]形式转换为[树]形式
    public static String toTree(String header) {
        JSONArray headerArr = JSONArray.parseArray(header);
        int maxRow = headerArr.size();
        JSONArray newHeader = headerArr.getJSONArray(0);
        headerArr.remove(0);

        JSONArray lastRows = new JSONArray();

        for (int i = 0; i < newHeader.size(); i++) {
            JSONObject item = newHeader.getJSONObject(i);
            int rowspan = getRowspan(item);
            int lastRowsNumber = maxRow - rowspan;
            item.put("lastRowsNumber", lastRowsNumber);
            if (lastRowsNumber > 0) {
                lastRows.add(item);
            }
        }

        for (int i = 0; i < headerArr.size(); i++) {
            JSONArray newLastRows = new JSONArray();
            JSONArray curRow = headerArr.getJSONArray(i);
            int begin = 0;
            for (int j = 0; j < lastRows.size(); j++) {
                JSONObject item = lastRows.getJSONObject(j);
                int colspan = getColspan(item);
                Integer lastRowsNumber = item.getInteger("lastRowsNumber");
                JSONArray children = new JSONArray();
                for (int k = begin; colspan > 0; k++) {
                    JSONObject addItem = curRow.getJSONObject(k);
                    int addColspan = getColspan(addItem);
                    int addRowspan = getRowspan(addItem);

                    int addLastRowsNumber = lastRowsNumber - addRowspan;
                    addItem.put("lastRowsNumber", addLastRowsNumber);
                    if (addLastRowsNumber > 0) {
                        newLastRows.add(addItem);
                    }

                    children.add(addItem);

                    begin++;
                    colspan -= addColspan;
                }
                item.put("children", children);
            }
            lastRows = newLastRows;
        }
        return newHeader.toJSONString();
    }

    //将[树]形式转换为[rowspan-colspan]形式
    public static String toRowspanColspan(String header) {
        JSONArray headerArr = JSONArray.parseArray(header);
        int headerDeep = getDeep(headerArr);
        JSONArray newHeader = new JSONArray();
        toRowspanColspan(newHeader, headerArr, headerDeep, 0);
        return newHeader.toJSONString();
    }

    private static int getRowspan(JSONObject item) {
        return Optional.ofNullable(item.getInteger("rowspan")).orElse(1);
    }

    private static int getColspan(JSONObject item) {
        return Optional.ofNullable(item.getInteger("colspan")).orElse(1);
    }

    private static int getDeep(JSONArray tree) {
        if (tree == null || tree.size() == 0) {
            return 0;
        }
        int deep = 0;
        for (int i = 0; i < tree.size(); i++) {
            JSONObject item = tree.getJSONObject(i);
            int oDeep = getDeep(item.getJSONArray("children"));
            if (oDeep > deep) {
                deep = oDeep;
            }
        }
        return deep + 1;
    }

    private static int toRowspanColspan(JSONArray newHeader, JSONArray header, int lastDeep, int deep) {
        JSONArray deepHeader;
        if (newHeader.size() - 1 < deep) {
            deepHeader = new JSONArray();
            newHeader.add(deepHeader);
        } else {
            deepHeader = newHeader.getJSONArray(deep);
        }
        int totalCols = 0;
        for (int i = 0; i < header.size(); i++) {
            JSONObject item = header.getJSONObject(i);
            JSONArray children = item.getJSONArray("children");
            if (children == null || children.size() == 0) {
                item.put("colspan", 1);
                item.put("rowspan", lastDeep);
                totalCols++;
            } else {
                int oTotalCols = toRowspanColspan(newHeader, children, lastDeep - 1, deep + 1);
                item.put("colspan", oTotalCols);
                item.put("rowspan", 1);
                totalCols += oTotalCols;
            }
            item.remove("children");
            deepHeader.add(item);
        }
        return totalCols;
    }
}
