package org.collectors.dto;

public class Entity {
    private Long id;
    private String name;
    private Double loaded;
    private Double underLoading;
    private Double anchored;
    private Double nominated;
    private Double scheduled;
    private Double confirmed;
    private Double underDiscussion;
    private Double forecastAdHoc;

    public Entity() {
    }

    public Entity(Entity entity) {
    }
    
    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Entity(Long id, String name, Entity entity) {
        this.id = id;
        this.name = name;
        this.loaded = entity.getLoaded();
        this.underLoading = entity.getUnderLoading();
        this.anchored = entity.getAnchored();
        this.nominated = entity.getNominated();
        this.scheduled = entity.getScheduled();
        this.confirmed = entity.getConfirmed();
        this.underDiscussion = entity.getUnderDiscussion();
        this.forecastAdHoc = entity.getForecastAdHoc();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLoaded() {
        return loaded;
    }

    public void setLoaded(Double loaded) {
        this.loaded = loaded;
    }

    public Double getUnderLoading() {
        return underLoading;
    }

    public void setUnderLoading(Double underLoading) {
        this.underLoading = underLoading;
    }

    public Double getAnchored() {
        return anchored;
    }

    public void setAnchored(Double anchored) {
        this.anchored = anchored;
    }

    public Double getNominated() {
        return nominated;
    }

    public void setNominated(Double nominated) {
        this.nominated = nominated;
    }

    public Double getScheduled() {
        return scheduled;
    }

    public void setScheduled(Double scheduled) {
        this.scheduled = scheduled;
    }

    public Double getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Double confirmed) {
        this.confirmed = confirmed;
    }

    public Double getUnderDiscussion() {
        return underDiscussion;
    }

    public void setUnderDiscussion(Double underDiscussion) {
        this.underDiscussion = underDiscussion;
    }

    public Double getForecastAdHoc() {
        return forecastAdHoc;
    }

    public void setForecastAdHoc(Double forecastAdHoc) {
        this.forecastAdHoc = forecastAdHoc;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loaded=" + loaded +
                ", underLoading=" + underLoading +
                ", anchored=" + anchored +
                ", nominated=" + nominated +
                ", scheduled=" + scheduled +
                ", confirmed=" + confirmed +
                ", underDiscussion=" + underDiscussion +
                ", forecastAdHoc=" + forecastAdHoc +
                '}';
    }
}
