package com.example.graphqlserver;

import org.springframework.core.annotation.AliasFor;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public final class Location extends HistoricalEntity {

    private static List<Location> locations = Arrays.asList(
            new Location("2023-12-11", "10", 101, 201, 40, 50, 60, 70, 80, 90, 100),
            new Location("2023-12-11", "11", 102, 202, 41, 51, 61, 71, 81, 91, 111),
            new Location("2023-12-11", "12", 103, 203, 42, 52, 62, 72, 82, 92, 122),
            new Location("2023-12-11", "13", 102, 202, 43, 53, 63, 73, 83, 93, 133),
            new Location("2023-12-12", "10", 101, 201, 40, 50, 60, 70, 80, 90, 100),
            new Location("2023-12-12", "11", 102, 202, 41, 51, 61, 71, 81, 91, 111),
            new Location("2023-12-12", "12", 103, 203, 42, 52, 62, 72, 82, 92, 122),
            new Location("2023-12-12", "13", 102, 202, 43, 53, 63, 73, 83, 93, 133),
            new Location("2023-12-13", "10", 101, 201, 40, 50, 60, 70, 80, 90, 100),
            new Location("2023-12-13", "11", 102, 202, 41, 51, 61, 71, 81, 91, 111),
            new Location("2023-12-13", "12", 103, 203, 42, 52, 62, 72, 82, 92, 122),
            new Location("2023-12-13", "13", 102, 202, 43, 53, 63, 73, 83, 93, 133)
    );
    private final String snapshotDate;
    private final String id;
    private final int companyId;
    private final int projectId;
    private final int dailyLogCount;
    private final int submittalCount;
    private final int observationCount;
    private final int rfiCount;
    private final int inspectionCount;
    private final int punchListCount;
    private final int photosCount;

    public Location(String snapshotDate, String id, int companyId, int projectId, int dailyLogCount, int submittalCount,
                    int observationCount, int rfiCount, int inspectionCount, int punchListCount,
                    int photosCount) {
        this.snapshotDate = snapshotDate;
        this.id = id;
        this.companyId = companyId;
        this.projectId = projectId;
        this.dailyLogCount = dailyLogCount;
        this.submittalCount = submittalCount;
        this.observationCount = observationCount;
        this.rfiCount = rfiCount;
        this.inspectionCount = inspectionCount;
        this.punchListCount = punchListCount;
        this.photosCount = photosCount;
    }

    public static Location getById(String id) {
        System.out.println("Location.getById: " + id);
        return locations.stream()
                .filter(author -> author.id().equals(id))
                .max(Comparator.comparing(Location::snapshotDate))
                .orElse(null);
    }

    public static List<Location> getByIds(List<String> ids) {
        System.out.println("Location.getByIds: " + ids);

        var filteredLocations = locations.stream()
                .filter(item -> ids.contains(item.id()));

        var locationMap = filteredLocations
                .collect(Collectors.groupingBy(Location::id));

        return locationMap.values().stream()
                .map(list -> list.stream()
                        .max(Comparator.comparing(Location::snapshotDate))
                        .orElse(null))
                .collect(Collectors.toList())
                ;
    }

    public static List<Location> getByIds(List<String> ids, String snapshotDateStart, String snapshotDateEnd, List<String> timeSeriesFields) {
        System.out.println("Location.getByIds: " + ids);
        var filteredLocations = locations.stream()
                .filter(item -> ids.contains(item.id()))
                .filter(author -> {
                    if (snapshotDateStart != null && snapshotDateEnd != null) {
                        return author.snapshotDate().compareTo(snapshotDateStart) >= 0 && author.snapshotDate().compareTo(snapshotDateEnd) <= 0;
                    } else if (snapshotDateStart != null) {
                        return author.snapshotDate().compareTo(snapshotDateStart) >= 0;
                    } else if (snapshotDateEnd != null) {
                        return author.snapshotDate().compareTo(snapshotDateEnd) <= 0;
                    } else {
                        return true;
                    }
                });

        var locationMap = filteredLocations
                .collect(Collectors.groupingBy(Location::id));

        var latest = locationMap.values().stream()
                .map(list -> list.stream()
                        .max(Comparator.comparing(Location::snapshotDate))
                        .orElse(null))
                .collect(Collectors.toList());
        if (latest == null) {
            return Collections.emptyList();
        }

        if (CollectionUtils.isEmpty(timeSeriesFields)) {
            return latest;
        }

        return latest.stream()
                .map(location -> {
                    var historical = new LinkedHashMap<String, Map<String, Object>>();
                    timeSeriesFields.forEach(field -> {
                        var fieldHistoricalMap =
                                locationMap.get(location.id()).stream()
                                        .collect(Collectors.toMap(Location::snapshotDate, item -> item.getFieldValue(field)));
                        historical.put(field, fieldHistoricalMap);
                    });
                    location.set_historical(historical);
                    return location;
                })
                .collect(Collectors.toList());
    }

    public Object getFieldValue(final String field) {
        try {
            return this.getClass().getDeclaredField(field).get(this);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return null;
        }
    }

    public String snapshotDate() {
        return snapshotDate;
    }

    public String id() {
        return id;
    }

    public int companyId() {
        return companyId;
    }

    public int projectId() {
        return projectId;
    }

    public int dailyLogCount() {
        return dailyLogCount;
    }

    public int submittalCount() {
        return submittalCount;
    }

    public int observationCount() {
        return observationCount;
    }

    public int rfiCount() {
        return rfiCount;
    }

    public int inspectionCount() {
        return inspectionCount;
    }

    public int punchListCount() {
        return punchListCount;
    }

    public int photosCount() {
        return photosCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Location) obj;
        return Objects.equals(this.snapshotDate, that.snapshotDate) &&
                Objects.equals(this.id, that.id) &&
                this.companyId == that.companyId &&
                this.projectId == that.projectId &&
                this.dailyLogCount == that.dailyLogCount &&
                this.submittalCount == that.submittalCount &&
                this.observationCount == that.observationCount &&
                this.rfiCount == that.rfiCount &&
                this.inspectionCount == that.inspectionCount &&
                this.punchListCount == that.punchListCount &&
                this.photosCount == that.photosCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(snapshotDate, id, companyId, projectId, dailyLogCount, submittalCount, observationCount, rfiCount, inspectionCount, punchListCount, photosCount);
    }

    @Override
    public String toString() {
        return "Location[" +
                "snapshotDate=" + snapshotDate + ", " +
                "id=" + id + ", " +
                "companyId=" + companyId + ", " +
                "projectId=" + projectId + ", " +
                "dailyLogCount=" + dailyLogCount + ", " +
                "submittalCount=" + submittalCount + ", " +
                "observationCount=" + observationCount + ", " +
                "rfiCount=" + rfiCount + ", " +
                "inspectionCount=" + inspectionCount + ", " +
                "punchListCount=" + punchListCount + ", " +
                "photosCount=" + photosCount + ']';
    }

}