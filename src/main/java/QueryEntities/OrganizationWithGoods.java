package QueryEntities;

import org.jetbrains.annotations.NotNull;

public class OrganizationWithGoods {
    private long organizationID;
    @NotNull
    private String organizationName;


    private long nomenclatureID;
    @NotNull
    private String nomenclatureName;


    public OrganizationWithGoods(long organizationID, @NotNull String organizationName, long nomenclatureID,
                                 @NotNull String nomenclatureName) {
        this.organizationID = organizationID;
        this.organizationName = organizationName;

        this.nomenclatureID = nomenclatureID;
        this.nomenclatureName = nomenclatureName;
    }

    @Override
    public String toString() {
        return "\norganization ID: " + organizationID + " Name: " + organizationName + " nomenclature ID: " +
                nomenclatureID + " Name: " + nomenclatureName;
    }
}
