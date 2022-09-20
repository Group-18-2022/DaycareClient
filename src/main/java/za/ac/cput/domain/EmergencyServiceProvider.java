package za.ac.cput.domain;

/* EmergencyServiceProvider.java
   Entity for the EmergencyServiceProvider
   Author: Joshua Daniel Jonkers(215162668)
   Date: 22/05/2022
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class EmergencyServiceProvider {
    @Id
    @NotNull
    private String serviceID;
    @NotNull
    private String serviceName, type, phoneNum;

    protected EmergencyServiceProvider() {}

    private EmergencyServiceProvider(Builder builder) {
        this.serviceID =builder.serviceID;
        this.serviceName = builder.serviceName;
        this.type = builder.type;
        this.phoneNum = builder.phoneNum;
    }

    public String getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getType() {
        return type;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public String toString() {
        return "EmergencyServiceProvider{" +
                "serviceID='" + serviceID + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", type='" + type + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }

    public static class Builder {
        private String serviceID;
        private String serviceName;
        private String type;
        private String phoneNum;

        public Builder setServiceID(String serviceID) {
            this.serviceID = serviceID;
            return this;
        }

        public Builder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return  this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        public Builder copy(EmergencyServiceProvider esp) {
            this.serviceID = esp.serviceID;
            this.serviceName = esp.serviceName;
            this.type = esp.type;
            this.phoneNum = esp.phoneNum;
            return this;
        }

        public EmergencyServiceProvider build() {
            return new EmergencyServiceProvider(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmergencyServiceProvider)) return false;
        EmergencyServiceProvider that = (EmergencyServiceProvider) o;
        return serviceID.equals(that.serviceID) && serviceName.equals(that.serviceName) && type.equals(that.type) && phoneNum.equals(that.phoneNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceID, serviceName, type, phoneNum);
    }
}
