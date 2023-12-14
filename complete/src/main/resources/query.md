# Example GraphQL Queries
## Get location by id
```graphql
query locationById {
    locationById(id: 10) {
        id
        snapshotDate
        companyId
        projectId
        dailyLogCount
        submittalCount
        observationCount
        rfiCount
        inspectionCount
        punchListCount
        photosCount
    }
}
```

### Response
```json
{
  "data": {
    "locationById": {
      "id": "10",
      "snapshotDate": "2023-12-13",
      "companyId": 101,
      "projectId": 201,
      "dailyLogCount": 40,
      "submittalCount": 50,
      "observationCount": 60,
      "rfiCount": 70,
      "inspectionCount": 80,
      "punchListCount": 90,
      "photosCount": 100
    }
  }
}
```

## Get location list by multiple ids
```graphql
query locationListByIds {
    locationListByIds(ids: [10,11]) {
        id
        snapshotDate
        companyId
        projectId
        dailyLogCount
        submittalCount
        observationCount
        rfiCount
        inspectionCount
        punchListCount
        photosCount
    }
}
```

### Response
```json
{
  "data": {
    "locationListByIds": [
      {
        "id": "11",
        "snapshotDate": "2023-12-13",
        "companyId": 102,
        "projectId": 202,
        "dailyLogCount": 41,
        "submittalCount": 51,
        "observationCount": 61,
        "rfiCount": 71,
        "inspectionCount": 81,
        "punchListCount": 91,
        "photosCount": 111
      },
      {
        "id": "10",
        "snapshotDate": "2023-12-13",
        "companyId": 101,
        "projectId": 201,
        "dailyLogCount": 40,
        "submittalCount": 50,
        "observationCount": 60,
        "rfiCount": 70,
        "inspectionCount": 80,
        "punchListCount": 90,
        "photosCount": 100
      }
    ]
  }
}
```

## Get location list with history by ids
```graphql
query locationListWithHistoryByIds {
    locationListWithHistoryByIds(
        ids: [10,11,12],
        snapshotDateStart: "2023-12-12",
        timeSeriesFields: ["inspectionCount", "dailyLogCount","submittalCount"]
    ) {
        id
        snapshotDate
        companyId
        projectId
        dailyLogCount
        submittalCount
        observationCount
        rfiCount
        inspectionCount
        punchListCount
        photosCount
        _historical
    }
}
```

### Response
```json
{
  "data": {
    "locationListWithHistoryByIds": [
      {
        "id": "11",
        "snapshotDate": "2023-12-13",
        "companyId": 102,
        "projectId": 202,
        "dailyLogCount": 41,
        "submittalCount": 51,
        "observationCount": 61,
        "rfiCount": 71,
        "inspectionCount": 81,
        "punchListCount": 91,
        "photosCount": 111,
        "_historical": {
          "inspectionCount": {
            "2023-12-12": 81,
            "2023-12-13": 81
          },
          "dailyLogCount": {
            "2023-12-12": 41,
            "2023-12-13": 41
          },
          "submittalCount": {
            "2023-12-12": 51,
            "2023-12-13": 51
          }
        }
      },
      {
        "id": "12",
        "snapshotDate": "2023-12-13",
        "companyId": 103,
        "projectId": 203,
        "dailyLogCount": 42,
        "submittalCount": 52,
        "observationCount": 62,
        "rfiCount": 72,
        "inspectionCount": 82,
        "punchListCount": 92,
        "photosCount": 122,
        "_historical": {
          "inspectionCount": {
            "2023-12-12": 82,
            "2023-12-13": 82
          },
          "dailyLogCount": {
            "2023-12-12": 42,
            "2023-12-13": 42
          },
          "submittalCount": {
            "2023-12-12": 52,
            "2023-12-13": 52
          }
        }
      },
      {
        "id": "10",
        "snapshotDate": "2023-12-13",
        "companyId": 101,
        "projectId": 201,
        "dailyLogCount": 40,
        "submittalCount": 50,
        "observationCount": 60,
        "rfiCount": 70,
        "inspectionCount": 80,
        "punchListCount": 90,
        "photosCount": 100,
        "_historical": {
          "inspectionCount": {
            "2023-12-12": 80,
            "2023-12-13": 80
          },
          "dailyLogCount": {
            "2023-12-12": 40,
            "2023-12-13": 40
          },
          "submittalCount": {
            "2023-12-12": 50,
            "2023-12-13": 50
          }
        }
      }
    ]
  }
}
```
