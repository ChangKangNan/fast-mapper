package ${table.pojoPackagePath}.fm.dao;
import cn.ft.ckn.fastmapper.component.SplicingParam;
import ${table.pojoPackagePath}.fm.action.${table.pojoActionName};

public class ${table.pojoMapperName} {
    private ${table.pojoMapperName}() {}

    public static ${table.pojoActionName}.InsertMapper INSERT() {
    return new ${table.pojoActionName}.InsertMapper(new SplicingParam());
  }
    public static ${table.pojoActionName}.BaseSelectMapper SELECT() {
        return new ${table.pojoActionName}.BaseSelectMapper(new SplicingParam());
  }
    public static ${table.pojoActionName}.BaseUpdateMapper UPDATE() {
        return new ${table.pojoActionName}.BaseUpdateMapper(new SplicingParam());
  }
    public static ${table.pojoActionName}.BaseDeletedMapper DELETE() {
        return new ${table.pojoActionName}.BaseDeletedMapper(new SplicingParam());
  }
}
