package cn.ft.ckn.fastmapper_test.fm.action;

import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.fastmapper_test.fm.Stock;

/**
 * @author ckn
 * @date 2022/8/2
 */
public class StockAction {
    public static class InsertMapper extends InsertDao<Stock, InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
            super(splicingParam,Stock.class, InsertMapper.class);
        }
    }

    public static class BaseSelectMapper extends BaseSelectAction<Stock, BaseSelectMapper> {
        public BaseSelectMapper(SplicingParam splicingParam) {
            super(splicingParam,Stock.class, BaseSelectMapper.class);
        }
        public SelectCriteria<Stock, BaseSelectMapper> id() {
            this.fieldName="id";
            return new SelectCriteria<>(this.splicingParam,this.fieldName,Stock.class, BaseSelectMapper.class);
        }
        public SelectCriteria<Stock, BaseSelectMapper> stockName() {
            this.fieldName="stock_name";
            return new SelectCriteria<>(this.splicingParam, this.fieldName,Stock.class, BaseSelectMapper.class);
        }
    }

    public static class BaseUpdateMapper extends BaseUpdateAction<Stock, BaseUpdateMapper> {
        public BaseUpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,Stock.class, BaseUpdateMapper.class);
        }
        public UpdateCriteria<Stock, BaseUpdateMapper> id() {
            this.fieldName="id";
            return new UpdateCriteria<>(this.splicingParam,this.fieldName,Stock.class, BaseUpdateMapper.class);
        }
        public UpdateCriteria<Stock, BaseUpdateMapper> stockName() {
            this.fieldName="stock_name";
            return new UpdateCriteria<>(this.splicingParam, this.fieldName,Stock.class, BaseUpdateMapper.class);
        }
    }

    public static class BaseDeletedMapper extends BaseDeletedAction<Stock, BaseDeletedMapper> {
        public BaseDeletedMapper(SplicingParam splicingParam) {
            super(splicingParam,Stock.class, BaseDeletedMapper.class);
        }
        public DeletedCriteria<Stock, BaseDeletedMapper> id() {
            this.fieldName="id";
            return new DeletedCriteria<>(this.splicingParam,this.fieldName,Stock.class, BaseDeletedMapper.class);
        }
        public DeletedCriteria<Stock, BaseDeletedMapper> stockName() {
            this.fieldName="stock_name";
            return new DeletedCriteria<>(this.splicingParam, this.fieldName,Stock.class, BaseDeletedMapper.class);
        }
    }
}
