package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.BankPaperContract;

public class BankPaperContractAction {
public static class InsertMapper extends InsertDao<BankPaperContract, InsertMapper> {
        public InsertMapper(FastMapperParam FastMapperParam){
        super(FastMapperParam,BankPaperContract.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<BankPaperContract, BaseSelectMapper> {
        public BaseSelectMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> globalId() {
        this.fieldName="global_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> no() {
        this.fieldName="no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> contractV2No() {
        this.fieldName="contract_v2_no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> contractV2LotId() {
        this.fieldName="contract_v2_lot_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperContract, BaseSelectMapper> deleted() {
        this.fieldName="deleted";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,BankPaperContract.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<BankPaperContract, BaseUpdateMapper> {

        public BaseUpdateMapper(FastMapperParam FastMapperParam) {
            super(FastMapperParam,BankPaperContract.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> globalId() {
        this.fieldName="global_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> no() {
        this.fieldName="no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> contractV2No() {
        this.fieldName="contract_v2_no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> contractV2LotId() {
        this.fieldName="contract_v2_lot_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperContract, BaseUpdateMapper> deleted() {
        this.fieldName="deleted";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<BankPaperContract, BaseDeletedMapper> {
        public BaseDeletedMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> globalId() {
        this.fieldName="global_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> no() {
        this.fieldName="no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> contractV2No() {
        this.fieldName="contract_v2_no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> contractV2LotId() {
        this.fieldName="contract_v2_lot_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperContract, BaseDeletedMapper> deleted() {
        this.fieldName="deleted";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,BankPaperContract.class, BaseDeletedMapper.class);
        }

    }
}
