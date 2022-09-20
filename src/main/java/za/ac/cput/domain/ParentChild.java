package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/* Author : Karl Haupt
*  Student Number: 220236585
*/
@Entity
@IdClass(ParentChild.ParentChildID.class)
public class ParentChild implements Serializable{
    @Id
    @NotNull
    private String parentID, childID;

    private ParentChild(Builder builder) {
        this.parentID = builder.parentID;
        this.childID = builder.childID;
    }

    protected ParentChild() {}

    public String getParentID() {
        return parentID;
    }

    public String getChildID() {
        return childID;
    }

    public static class Builder {
        private String parentID, childID;

        public Builder setParentID(String parentID) {
            this.parentID = parentID;
            return this;
        }

        public Builder setChildID(String childID) {
            this.childID = childID;
            return this;
        }

        public Builder copy(ParentChild parentChild) {
            this.parentID = parentChild.parentID;
            this.childID = parentChild.childID;
            return this;
        }

        public ParentChild build() {
            return new ParentChild(this);
        }
    }

    public static class ParentChildID implements Serializable {
        private String parentID, childID;

        public ParentChildID(String parentID, String childID) {
            this.parentID = parentID;
            this.childID = childID;
        }

        protected ParentChildID() {
        }

        public String getParentID() {
            return parentID;
        }

        public String getChildID() {
            return childID;
        }
    }
}
