package com.test.naceapi.domain.transaction;

import org.json.JSONObject;
import javax.persistence.*;

@Entity
@Table(name = "NACE")
public class NaceEntity {

    public NaceEntity() {

    }

    public NaceEntity(String order, String level, String code, String parent, String description, String thisItemIncludes, String thisItemAlsoIncludes, String rulings, String thisItemExcludes, String refernce) {
        naceOrder = order;
        this.level = level;
        this.code = code;
        this.description = description;
        this.parent = parent;
        this.includes = thisItemIncludes;
        alsoIncludes = thisItemAlsoIncludes;
        this.rulings = rulings;
        excludes = thisItemExcludes;
        this.refernce = refernce;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naceOrder;

    private String level;

    private String code;

    private String description;

    private String parent;

    @Column(columnDefinition = "text")
    private String includes;

    @Column(columnDefinition = "text")
    private String alsoIncludes;

    @Column(columnDefinition = "text")
    private String rulings;

    @Column(columnDefinition = "text")
    private String excludes;

    private String refernce;

    public String getNaceOrder() {
        return naceOrder;
    }

    public void setNaceOrder(String order) {
        this.naceOrder = order;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getAlsoIncludes() {
        return alsoIncludes;
    }

    public void setAlsoIncludes(String alsoIncludes) {
        this.alsoIncludes = alsoIncludes;
    }

    public String getRulings() {
        return rulings;
    }

    public void setRulings(String rulings) {
        this.rulings = rulings;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

    public String getRefernce() {
        return refernce;
    }

    public void setRefernce(String refernce) {
        this.refernce = refernce;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JSONObject translate() {

        JSONObject naceObject = new JSONObject();
        naceObject.put("Order", this.getNaceOrder());
        naceObject.put("Level", this.getLevel());
        naceObject.put("Code", this.getCode());
        naceObject.put("Parent", this.getParent());
        naceObject.put("Description", this.getDescription());
        naceObject.put("This item includes", this.getIncludes());
        naceObject.put("This item also includes", this.getAlsoIncludes());
        naceObject.put("Rulings", this.getRulings());
        naceObject.put("This item excludes", this.getExcludes());
        naceObject.put("Reference to ISIC Rev. 4", this.getRefernce());

        return naceObject;
    }
}
