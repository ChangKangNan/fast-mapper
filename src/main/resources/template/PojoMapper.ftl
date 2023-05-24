package ${table.pojoPackagePath}.fm.dao;
import cn.ft.ckn.fastmapper.bean.FastMapperParam;
import ${table.pojoPackagePath}.fm.action.${table.pojoActionName};

public class ${table.pojoMapperName} {
    private ${table.pojoMapperName}() {}

    public static ${table.pojoActionName}.InsertMapper lambdaInsert() {
    return new ${table.pojoActionName}.InsertMapper(new FastMapperParam());
  }
    public static ${table.pojoActionName}.BaseSelectMapper lambdaQuery() {
        return new ${table.pojoActionName}.BaseSelectMapper(new FastMapperParam());
  }
    public static ${table.pojoActionName}.BaseUpdateMapper lambdaUpdate() {
        return new ${table.pojoActionName}.BaseUpdateMapper(new FastMapperParam());
  }
    public static ${table.pojoActionName}.BaseDeletedMapper lambdaDelete() {
        return new ${table.pojoActionName}.BaseDeletedMapper(new FastMapperParam());
  }
}
