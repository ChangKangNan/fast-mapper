package ${table.pojoPackagePath}.fm.dao;
import ${table.pojoPackagePath}.fm.action.${table.pojoActionName};

public class ${table.pojoMapperName} {
    private ${table.pojoMapperName}() {}

    public static ${table.pojoActionName}.InsertMapper lambdaInsert() {
    return new ${table.pojoActionName}.InsertMapper();
  }
    public static ${table.pojoActionName}.BaseSelectMapper lambdaQuery() {
        return new ${table.pojoActionName}.BaseSelectMapper();
  }
    public static ${table.pojoActionName}.BaseUpdateMapper lambdaUpdate() {
        return new ${table.pojoActionName}.BaseUpdateMapper();
  }
    public static ${table.pojoActionName}.BaseDeletedMapper lambdaDelete() {
        return new ${table.pojoActionName}.BaseDeletedMapper();
  }
}
