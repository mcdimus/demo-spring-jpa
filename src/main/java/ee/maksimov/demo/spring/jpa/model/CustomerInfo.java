package ee.maksimov.demo.spring.jpa.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo implements Serializable {

  @Min(0)
  @Max(120)
  private Integer age;

  private boolean premium;

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private List<String> list;

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private Map<String, String> map;

  public List<String> getList() {
    if (list == null) {
      list = new ArrayList<>();
    }
    return list;
  }

  public Map<String, String> getMap() {
    if (map == null) {
      map = new HashMap<>();
    }
    return map;
  }

}
