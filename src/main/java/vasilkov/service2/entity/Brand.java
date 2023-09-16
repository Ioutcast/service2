package vasilkov.service2.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
@Table(name = "brand")
public class Brand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;


    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private Set<Model> models;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private Set<Order> orders;


}