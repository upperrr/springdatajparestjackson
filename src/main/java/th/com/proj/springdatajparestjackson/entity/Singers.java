package th.com.proj.springdatajparestjackson.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Table
@Entity
@Data
@NoArgsConstructor
@XmlRootElement //Jackson dependency 주입 후 작성
public class Singers {
    @Id
    @Column(name = "singerpos", length = 3)
    @SequenceGenerator(sequenceName = "singer_gen1", allocationSize = 20, initialValue = 100, name="gen1") //initial value
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen1") //PK properties
    private Integer singersPosition;
    @Column(name = "singername", length = 15)
    private String singersName;
    @Column(name = "singerremuneration", length = 15)
    private Double singersRemuneration;
}
