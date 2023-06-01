package ${table.pojoPackagePath}.fm.action;
import cn.ft.ckn.fastmapper.component.action.*;
import cn.ft.ckn.fastmapper.component.criteria.*;
import cn.ft.ckn.fastmapper.component.dao.*;
import ${table.pojoPackagePath}.fm.bean.${table.pojoName};

public class ${table.pojoActionName} {
public static class InsertMapper extends InsertDao<${table.pojoName}, InsertMapper> {
        public InsertMapper(){
        super(InsertMapper.class,${table.pojoName}.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<${table.pojoName}, BaseSelectMapper> {
        public BaseSelectMapper() {
        super(BaseSelectMapper.class,${table.pojoName}.class);
        }

<#list table.columns as p>

        public SelectCriteria<${table.pojoName}, BaseSelectMapper> ${p.propertyName}() {
        return new SelectCriteria<>(this,"${p.columnName}",${table.pojoName}.class);
        }

</#list>
}

public static class BaseUpdateMapper extends BaseUpdateAction<${table.pojoName}, BaseUpdateMapper> {

        public BaseUpdateMapper() {
        super(BaseUpdateMapper.class,${table.pojoName}.class);
        }
<#list table.columns as p>

        public UpdateCriteria<${table.pojoName}, BaseUpdateMapper> ${p.propertyName}() {
        return new UpdateCriteria<>(this,"${p.columnName}",${table.pojoName}.class);
        }

</#list>
}

public static class BaseDeletedMapper extends BaseDeletedAction<${table.pojoName}, BaseDeletedMapper> {
        public BaseDeletedMapper() {
        super(BaseDeletedMapper.class,${table.pojoName}.class);
        }

<#list table.columns as p>

        public DeletedCriteria<${table.pojoName}, BaseDeletedMapper> ${p.propertyName}() {
        return new DeletedCriteria<>(this,"${p.columnName}",${table.pojoName}.class);
        }

</#list>
    }
}
