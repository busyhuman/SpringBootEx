package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotEmpty
    private String email;

    @Setter
    @NotEmpty
    private String name;

    @Setter
    @NotNull
    private Long level;

    private String password;

    private Long restaurantsId;

    public boolean isAdmin() {
        return level >= 100;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deactivate() {
        level = 0L;
    }

    public void setRestaurantsId(Long restaurantsId) {
        this.level = 50L;
        this.restaurantsId = restaurantsId;
    }

    public boolean isRestaurantOwner() {
        return level == 50L;
    }
}
