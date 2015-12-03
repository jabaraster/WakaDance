/**
 *
 */
package info.jabara.wakadance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jabara.jpa.entity.EntityBase;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jabaraster
 */
@Entity
@Getter
@Setter
public class EEmployee extends EntityBase<EEmployee> {
    private static final long serialVersionUID    = 3354865862673044654L;

    /**
     *
     */
    public static final int   MAX_CHAR_COUNT_NAME = 50;

    @Column(length = MAX_CHAR_COUNT_NAME * 3, unique = true)
    @NotNull
    @Size(min = 1, max = MAX_CHAR_COUNT_NAME)
    String                    name;
}
