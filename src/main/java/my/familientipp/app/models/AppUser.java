package my.familientipp.app.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
}
