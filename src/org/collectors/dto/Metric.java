package org.collectors.dto;

public class Metric {
    private Long familyId;
    private String family;
    private Long categoryId;
    private String category;
    private Long productId;
    private String product;
    private String op;
    private Double volume;

    public Metric(Long familyId, String family, Long categoryId, String category, Long productId, String product, String op, Double volume) {
        this.familyId = familyId;
        this.family = family;
        this.categoryId = categoryId;
        this.category = category;
        this.productId = productId;
        this.product = product;
        this.op = op;
        this.volume = volume;
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
