package org.sarath.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="birds")
public class Birds implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    public Birds(Long id, String name, String location, String type){
        this.name = name;
        this.location =location;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bird [id=" + id + ", Name=" + name + ", Location=" + location + ", Type=" + type + "]";
    }
}
