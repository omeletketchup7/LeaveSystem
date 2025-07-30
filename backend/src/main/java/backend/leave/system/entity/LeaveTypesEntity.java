package backend.leave.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_types")
public class LeaveTypesEntity {


        @Id
        @Column(length = 50)
        private String id;

        @Column(length = 50, nullable = false, unique = true)
        private String name;

        @Column(columnDefinition = "TEXT")
        private String description;

        @Column(name = "max_days", nullable = false)
        private Integer maxDays;

        public LeaveTypesEntity() {
        }

        public LeaveTypesEntity(String id, String name, String description, Integer maxDays) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.maxDays = maxDays;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getMaxDays() {
            return maxDays;
        }

        public void setMaxDays(Integer maxDays) {
            this.maxDays = maxDays;
        }
}