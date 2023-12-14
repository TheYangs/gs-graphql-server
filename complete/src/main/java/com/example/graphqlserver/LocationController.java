package com.example.graphqlserver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocationController {
    @QueryMapping
    public Location locationById(@Argument String id) {
        return Location.getById(id);
    }

//    @SchemaMapping
//    public Company company(Location location) {
//        return Company.getById(location.companyId());
//    }
//
//    @SchemaMapping
//    public Project project(Location location) {
//        return Project.getById(location.projectId());
//    }

    /*
query locationListByIds {
  locationListByIds(ids: [10,11], snapshotDateStart: "2023-12-12", timeSeriesFields: ["dailyLogCount","submittalCount"]) {
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
     */
    @QueryMapping
    public List<Location> locationListByIds(@Argument List<String> ids) {
        return Location.getByIds(ids);
    }

    @QueryMapping
    public List<Location> locationListWithHistoryByIds(@Argument List<String> ids,
                                            @Argument String snapshotDateStart,
                                            @Argument String snapshotDateEnd,
                                            @Argument List<String> timeSeriesFields) {
        return Location.getByIds(ids, snapshotDateStart, snapshotDateEnd, timeSeriesFields);
    }

}