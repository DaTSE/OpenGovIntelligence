package eu.opengovintelligence.admin.opengovintelligence;

/**
 * Created by Admin on 29/6/2017.
 *
 * Parameter: dataset (required)

 Example request:

 GET http://wapps.islab.uom.gr:8084/JSON-QB-REST-API/dataset-metadata?dataset=http://statistics.gov.scot/data/economic-activity-benefits-and-tax-credits/employment
 */

public class DatasetMetadata extends BasicVariables {

    String description,comment,issued,modified,license;


    public DatasetMetadata(String id, String label, String description, String comment, String issued, String modified, String license) {
        super(id, label);
        this.description = description;
        this.comment = comment;
        this.issued = issued;
        this.modified = modified;
        this.license = license;
    }

    public DatasetMetadata(String id, String label) {
        super(id, label);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
