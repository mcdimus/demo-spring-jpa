@TypeDefs({
  @TypeDef(name = "json", typeClass = JsonStringType.class),
  @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
package ee.maksimov.demo.spring.jpa.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;