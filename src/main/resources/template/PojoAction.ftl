package ${table.pojoPackagePath}.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import ${table.pojoPackagePath}.fm.${table.pojoName};

public class ${table.pojoActionName} {
public static class InsertMapper extends InsertDao<${table.pojoName}, InsertMapper> {
public InsertMapper(SplicingParam splicingParam){
super(splicingParam,${table.pojoName}.class, InsertMapper.class);
}
}

public static class BaseSelectMapper extends BaseSelectAction<${table.pojoName}, BaseSelectMapper> {
public BaseSelectMapper(SplicingParam splicingParam) {
super(splicingParam,${table.pojoName}.class, BaseSelectMapper.class);
}

<#list table.columns as p>

    public SelectCriteria<${table.pojoName}, BaseSelectMapper> ${p.propertyName}() {
    this.fieldName="${p.propertyName}";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,${table.pojoName}.class, BaseSelectMapper.class);
    }

</#list>
}

public static class BaseUpdateMapper extends BaseUpdateAction<${table.pojoName}, BaseUpdateMapper> {
public BaseUpdateMapper(SplicingParam splicingParam) {
super(splicingParam,${table.pojoName}.class, BaseUpdateMapper.class);
}
<#list table.columns as p>

    public UpdateCriteria<${table.pojoName}, BaseUpdateMapper> ${p.propertyName}() {
    this.fieldName="${p.propertyName}";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,${table.pojoName}.class, BaseUpdateMapper.class);
    }

</#list>
}

public static class BaseDeletedMapper extends BaseDeletedAction<${table.pojoName}, BaseDeletedMapper> {
public BaseDeletedMapper(SplicingParam splicingParam) {
super(splicingParam,${table.pojoName}.class, BaseDeletedMapper.class);
}

<#list table.columns as p>

    public DeletedCriteria<${table.pojoName}, BaseDeletedMapper> ${p.propertyName}() {
    this.fieldName="${p.propertyName}";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,${table.pojoName}.class, BaseDeletedMapper.class);
    }

</#list>
}
}
