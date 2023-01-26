# HeaderHelper
表头助手，提供两个方法对常见的两种表格表头形式进行转换。

**toTree**方法将**[rowspan-colspan]**形式转换为**[tree]**形式；

**toRowspanColspan**方法将**[tree]**形式转换为**[rowspan-colspan]**形式；

### 表格样式

<table>
<tr>
<th rowspan="3" colspan="1">Date</th>
<th rowspan="1" colspan="5">Delivery Info</th>
</tr>
<tr>
<th rowspan="2" colspan="1">Name</th>
<th rowspan="1" colspan="4">Address Info</th>
</tr>
<tr>
<th>State</th>
<th>City</th>
<th>Address</th>
<th>Zip</th>
</tr>
<tr>
<td>2016-05-03</td>
<td>Tom</td>
<td>California</td>
<td>Los Angeles</td>
<td>No. 189, Grove St, Los Angeles</td>
<td>CA 90036</td>
</tr>
</table>

### [rowspan-colspan]形式的json表头

```json
[
    [
        {
            "rowspan": 3,
            "colspan": 1,
            "id": "1",
            "label": "Date",
            "prop": "data"
        },
        {
            "rowspan": 1,
            "colspan": 5,
            "id": "2",
            "label": "Delivery Info"
        }
    ],
    [
        {
            "rowspan": 2,
            "colspan": 1,
            "id": "3",
            "label": "Name",
            "prop": "name"
        },
        {
            "rowspan": 1,
            "colspan": 4,
            "id": "4",
            "label": "Address Info"
        }
    ],
    [
        {
            "rowspan": 1,
            "colspan": 1,
            "id": "5",
            "label": "State",
            "prop": "state"
        },
        {
            "rowspan": 1,
            "colspan": 1,
            "id": "6",
            "label": "City",
            "prop": "city"
        },
        {
            "rowspan": 1,
            "colspan": 1,
            "id": "7",
            "label": "Address",
            "prop": "address"
        },
        {
            "rowspan": 1,
            "colspan": 1,
            "id": "8",
            "label": "Zip",
            "prop": "zip"
        }
    ]
]
```

### [tree]形式的json表头

```json
[
    {
        "id": "1",
        "label": "Date",
        "prop": "data"
    },
    {
        "id": "2",
        "label": "Delivery Info",
        "children":
        [
            {
                "id": "3",
                "label": "Name",
                "prop": "name"
            },
            {
                "id": "4",
                "label": "Address Info",
                "children":
                [
                    {
                        "id": "5",
                        "label": "State",
                        "prop": "state"
                    },
                    {
                        "id": "6",
                        "label": "City",
                        "prop": "city"
                    },
                    {
                        "id": "7",
                        "label": "Address",
                        "prop": "address"
                    },
                    {
                        "id": "8",
                        "label": "Zip",
                        "prop": "zip"
                    }
                ]
            }
        ]
    }
]
```

