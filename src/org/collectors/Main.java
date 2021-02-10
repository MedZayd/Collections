package org.collectors;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.sun.xml.internal.fastinfoset.util.QualifiedNameArray;
import org.collectors.dto.*;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.logging.Logger;

import static java.util.stream.Collectors.*;
import static org.collectors.dto.Op.*;

public class Main {
    private static Logger logger = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) throws IOException {
        List<Family> families = getFamilies();
        writeToFile(new Gson().toJson(families));

        // Map<F, Map<C, List<Q>>> => Map<F, List<Q>>
//
//        Map<Category, List<Quality>> categoryListMap = new HashMap<>();
//        categoryListMap.put(new Category(2L, "cat2"), Arrays.asList(
//                new Quality(3L, "prod3"),
//                new Quality(4L, "prod4")
//        ));
//        categoryListMap.put(new Category(1L, "cat1"), Arrays.asList(
//                new Quality(1L, "prod1"),
//                new Quality(2L, "prod2")
//        ));
//        Map<Family, Map<Category, List<Quality>>> map = new HashMap<>();
//        Family family = new Family(1L, "fam1");
//        map.put(family, categoryListMap);
//
//        Map<Family, List<Quality>> result = map.entrySet()
//                .stream()
//                .collect(toMap(
//                        e -> e.getKey(),
//                        e -> e.getValue().values().stream().flatMap(Collection::stream).collect(toList())
//                ));
//
//        System.out.println(result);
    }

    private static void populateFields(List<Metric> metrics, Entity entity) {
        Map<String, Double> groupedByOp = metrics.stream().collect(groupingBy(Metric::getOp, summingDouble(Metric::getVolume)));
        entity.setLoaded(groupedByOp.get(LOADED.toString()));
        entity.setUnderLoading(groupedByOp.get(UNDERLOADING.toString()));
        entity.setAnchored(groupedByOp.get(ANCHORED.toString()));
        entity.setNominated(groupedByOp.get(NOMINATED.toString()));
        entity.setScheduled(groupedByOp.get(SCHEDULED.toString()));
        entity.setConfirmed(groupedByOp.get(CONFIRMED.toString()));
        entity.setUnderDiscussion(groupedByOp.get(UNDER_DISCUSSION.toString()));
        entity.setForecastAdHoc(groupedByOp.get(FORECAST_ADHOC.toString()));
    }

    private static List<Family> getFamilies() throws IOException {
        List<Metric> metrics = getDataSet();
        logger.info("metrics found: "+metrics.size());
        long time = System.nanoTime();
        // Map<Long, List<Metric>>
        List<Family> families = new ArrayList<>();
        metrics.stream().collect(groupingBy(Metric::getFamilyId)).forEach((familyId, byFamilyId) -> {
            Family family = new Family(byFamilyId.get(0).getFamilyId(), byFamilyId.get(0).getFamily());
            populateFields(byFamilyId, family);
            List<Category> categories = new ArrayList<>();
            byFamilyId.stream().collect(groupingBy(Metric::getCategoryId)).forEach((categoryId, byCategoryId) -> {
                Category category = new Category(byCategoryId.get(0).getCategoryId(), byCategoryId.get(0).getCategory());
                populateFields(byCategoryId, category);
                List<Quality> qualities = new ArrayList<>();
                byCategoryId.stream().collect(groupingBy(Metric::getProductId)).forEach((productId, byProductId) -> {
                    Quality quality = new Quality(byProductId.get(0).getProductId(), byProductId.get(0).getProduct());
                    populateFields(byProductId, quality);
                    qualities.add(quality);
                });
                category.setQualities(qualities);
                categories.add(category);
            });
            family.setCategories(categories);
            families.add(family);
        });
        logger.info("time: "+((System.nanoTime()-time)/1000000)+"ms");
        return families;
    }

    private static List<Metric> getDataSet() throws IOException {
        logger.info("Getting Data Set from file");
        List<Metric> metrics = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("src/dataset.csv"))) {
            reader.skip(1);
            for (String[] nextLine : reader) {
                Metric metric =
                        new Metric(
                                Long.valueOf(nextLine[0]),
                                nextLine[1],
                                Long.valueOf(nextLine[2]),
                                nextLine[3],
                                Long.valueOf(nextLine[4]),
                                nextLine[5],
                                nextLine[6],
                                Double.valueOf(nextLine[7])
                        );
                metrics.add(metric);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metrics;
    }

    private static void writeToFile(String json) {
        try {
            FileOutputStream fos = new FileOutputStream("output.json");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(json); // write MenuArray to ObjectOutputStream
            oos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
