package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.BankPaperOpen;

public class BankPaperOpenAction {
public static class InsertMapper extends InsertDao<BankPaperOpen, InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
        super(splicingParam,BankPaperOpen.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<BankPaperOpen, BaseSelectMapper> {
        public BaseSelectMapper(SplicingParam splicingParam) {
        super(splicingParam,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> globalId() {
        this.fieldName="global_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> no() {
        this.fieldName="no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> openStatus() {
        this.fieldName="open_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> openAmount() {
        this.fieldName="open_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> remark() {
        this.fieldName="remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperOpen, BaseSelectMapper> deleted() {
        this.fieldName="deleted";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperOpen.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<BankPaperOpen, BaseUpdateMapper> {

        public BaseUpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,BankPaperOpen.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> globalId() {
        this.fieldName="global_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> no() {
        this.fieldName="no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> openStatus() {
        this.fieldName="open_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> openAmount() {
        this.fieldName="open_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> remark() {
        this.fieldName="remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperOpen, BaseUpdateMapper> deleted() {
        this.fieldName="deleted";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<BankPaperOpen, BaseDeletedMapper> {
        public BaseDeletedMapper(SplicingParam splicingParam) {
        super(splicingParam,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> globalId() {
        this.fieldName="global_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> no() {
        this.fieldName="no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> openStatus() {
        this.fieldName="open_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> openAmount() {
        this.fieldName="open_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> remark() {
        this.fieldName="remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperOpen, BaseDeletedMapper> deleted() {
        this.fieldName="deleted";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperOpen.class, BaseDeletedMapper.class);
        }

    }
}
