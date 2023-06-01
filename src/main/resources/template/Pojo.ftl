package ${table.pojoPackagePath}.fm.bean;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;

<#list table.packages as package>
${package}
</#list>


@Accessors(chain=true)
@Table(name = "${table.tableName}")
@Entity
@Data
public class ${table.pojoName} {
<#list table.columns as p>

<#if p.key==true>
    @Id
</#if>
    @Column(name = "${p.columnName}")
    private ${p.propertyType} ${p.propertyName};

</#list>

}
