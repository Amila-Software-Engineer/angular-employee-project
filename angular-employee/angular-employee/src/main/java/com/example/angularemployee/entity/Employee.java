package com.example.angularemployee.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(min = 3, max = 255, message = "Employee Calling Name should contain at least 3 characters. ")
    private String callingname;

    @NotNull(message = "fullname is required.")
    @Size(min = 3, max = 255, message = "Employee FullName should contain at least 3 characters. ")
    private String fullname;

    @Past(message = "Date of Birth should be a past date.")
    private LocalDate dobirth;

    @NotNull(message = "Employee NIC is required.")
    @Pattern(regexp = "^([0-9]{9}[vVxX])|([0-9]{12})$", message = "Invalid NIC")
    private String nic;

    @Lob
    @NotNull(message = "Address is required.")
    @Size(min = 5, max = 1000, message = "Employee Address should contain at least 5 characters.")
    private String address;

    @NotNull(message = "Employee Mobile number is required.")
    @Pattern(regexp ="^(07[0-9]{8})$", message = "Mobile Number is incorrect. Mobile Phone pattern is 07X XXXXXXX .")
    private String mobile;

    @Pattern(regexp ="^(0[0-9]{9})$", message = "Mobile Number is incorrect. Mobile Phone pattern is 07X XXXXXXX .")
    private String land;

    @NotNull(message = "Email is required.")
    @Pattern(regexp = "^([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})$", message = "Invalid email please enter valid email address.")
    private String email;

    private Byte[] photo;

    @ManyToOne
    private Gender gender;

    @ManyToOne
    private Nametitle nametitle;

    @ManyToMany
    @JoinTable(
            name = "employeeskill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skillList;

    public Employee(Integer id) {
        this.id = id;
    }
}
