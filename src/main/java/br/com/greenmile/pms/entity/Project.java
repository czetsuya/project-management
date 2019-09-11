package br.com.greenmile.pms.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private Date plannedStart;

    @Column(nullable = false)
    private Date plannedEnd;

    private boolean enabled;

    public Project() {
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

    public Date getPlannedStart() {
        return plannedStart;
    }

    public void setPlannedStart(Date plannedStart) {
        this.plannedStart = plannedStart;
    }

    public Date getPlannedEnd() {
        return plannedEnd;
    }

    public void setPlannedEnd(Date plannedEnd) {
        this.plannedEnd = plannedEnd;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;
        return Objects.equals(id, project.getId()) &&
                Objects.equals(name, project.getName()) &&
                Objects.equals(plannedStart, project.getPlannedStart()) &&
                Objects.equals(plannedEnd, project.getPlannedEnd()) &&
                Objects.equals(enabled, project.isEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, plannedStart, plannedEnd);
    }

    @Override
    public String toString() {
        return "Project {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plannedStart=" + plannedStart +
                ", plannedEnd=" + plannedEnd +
                ", enabled=" + enabled +
                '}';
    }
}
