package vasilkov.service2.entity;




import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import vasilkov.service2.entity.Brand;



@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;


    @Column(name = "userEmail")
    private String userEmail;


    @Column(name = "description")
    private String description;


    @Column(name = "color")
    private String color;


    @Column(name = "material")
    private String material;


    @Column(name = "number_of_pieces_in_a_package")
    private Integer number_of_pieces_in_a_package;


    @Column(name = "country_of_origin")
    private String country_of_origin;

    @Column(name = "status")
    private Boolean status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Brand brand;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Model model;


    @Column(name = "guarantee_period")
    private Integer guarantee_period;

}
