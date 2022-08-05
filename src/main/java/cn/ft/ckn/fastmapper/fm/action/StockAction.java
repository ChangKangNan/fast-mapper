package cn.ft.ckn.fastmapper.fm.action;

import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.fastmapper.fm.Stock;

/**
 * @author ckn
 * @date 2022/8/2
 */
public class StockAction {
    public static class InsertMapper extends InsertDao<Stock,StockAction.InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
            super(splicingParam,Stock.class,StockAction.InsertMapper.class);
        }
    }

    public static class SelectMapper extends SelectPojo<Stock, SelectMapper> {
        public SelectMapper(SplicingParam splicingParam) {
            super(splicingParam,Stock.class,StockAction.SelectMapper.class);
        }
        public SelectCriteria<Stock,StockAction.SelectMapper> id() {
            this.fieldName="id";
            return new SelectCriteria<>(this.splicingParam,this.fieldName,Stock.class,StockAction.SelectMapper.class);
        }
        public SelectCriteria<Stock,StockAction.SelectMapper> stockName() {
            this.fieldName="stock_name";
            return new SelectCriteria<>(this.splicingParam, this.fieldName,Stock.class,StockAction.SelectMapper.class);
        }
    }

    public static class UpdateMapper extends UpdatePojo<Stock, SelectMapper> {
        public UpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,Stock.class,StockAction.SelectMapper.class);
        }
        public UpdateCriteria<Stock, UpdateMapper> id() {
            this.fieldName="id";
            return new UpdateCriteria<>(this.splicingParam,this.fieldName,Stock.class,StockAction.UpdateMapper.class);
        }
        public UpdateCriteria<Stock,StockAction.UpdateMapper> stockName() {
            this.fieldName="stock_name";
            return new UpdateCriteria<>(this.splicingParam, this.fieldName,Stock.class,StockAction.UpdateMapper.class);
        }
    }

    public static class DeletedMapper extends DeletedPojo<Stock, DeletedMapper> {
        public DeletedMapper(SplicingParam splicingParam) {
            super(splicingParam,Stock.class,StockAction.DeletedMapper.class);
        }
        public DeletedCriteria<Stock,StockAction.DeletedMapper> id() {
            this.fieldName="id";
            return new DeletedCriteria<>(this.splicingParam,this.fieldName,Stock.class,StockAction.DeletedMapper.class);
        }
        public DeletedCriteria<Stock,StockAction.DeletedMapper> stockName() {
            this.fieldName="stock_name";
            return new DeletedCriteria<>(this.splicingParam, this.fieldName,Stock.class,StockAction.DeletedMapper.class);
        }
    }
}
