package ${table.pojoPackagePath}.fm.bean;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Id;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

<#list table.packages as package>
${package}
</#list>


@Accessors(chain=true)
@Table(name = "${table.tableName}")
@Entity
@Data
@ApiModel(value="${table.tableDesc}", description="${table.tableDesc}")
public class ${table.pojoName} {
<#list table.columns as p>

<#if p.key==true>
    @Id
</#if>
    @ApiModelProperty(value = "${p.columnRemarks}")
    @Column(name = "${p.columnName}")
    private ${p.propertyType} ${p.propertyName};

</#list>

}
