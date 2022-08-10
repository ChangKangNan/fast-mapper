package cn.ft.ckn.fastmapper.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.fastmapper.fm.bean.OtherBill;

public class OtherBillAction {
public static class InsertMapper extends InsertDao<OtherBill, InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
        super(splicingParam,OtherBill.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<OtherBill, BaseSelectMapper> {
        public BaseSelectMapper(SplicingParam splicingParam) {
        super(splicingParam,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> institutionId() {
        this.fieldName="institution_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> otherOpenId() {
        this.fieldName="other_open_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> spNo() {
        this.fieldName="sp_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> bno() {
        this.fieldName="bno";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> amount() {
        this.fieldName="amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> uqFlag() {
        this.fieldName="uq_flag";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> billState() {
        this.fieldName="bill_state";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> claimState() {
        this.fieldName="claim_state";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> discountCashState() {
        this.fieldName="discount_cash_state";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> pledgeState() {
        this.fieldName="pledge_state";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> contractNo() {
        this.fieldName="contract_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> version() {
        this.fieldName="version";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> payState() {
        this.fieldName="pay_state";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> draftDate() {
        this.fieldName="draft_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherBill, BaseSelectMapper> expirationDate() {
        this.fieldName="expiration_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherBill.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<OtherBill, BaseUpdateMapper> {

        public BaseUpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,OtherBill.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<OtherBill, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> institutionId() {
        this.fieldName="institution_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> otherOpenId() {
        this.fieldName="other_open_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> spNo() {
        this.fieldName="sp_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> bno() {
        this.fieldName="bno";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> amount() {
        this.fieldName="amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> uqFlag() {
        this.fieldName="uq_flag";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> billState() {
        this.fieldName="bill_state";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> claimState() {
        this.fieldName="claim_state";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> discountCashState() {
        this.fieldName="discount_cash_state";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> pledgeState() {
        this.fieldName="pledge_state";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> contractNo() {
        this.fieldName="contract_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> version() {
        this.fieldName="version";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> payState() {
        this.fieldName="pay_state";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> draftDate() {
        this.fieldName="draft_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherBill, BaseUpdateMapper> expirationDate() {
        this.fieldName="expiration_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<OtherBill, BaseDeletedMapper> {
        public BaseDeletedMapper(SplicingParam splicingParam) {
        super(splicingParam,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> institutionId() {
        this.fieldName="institution_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> otherOpenId() {
        this.fieldName="other_open_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> spNo() {
        this.fieldName="sp_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> bno() {
        this.fieldName="bno";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> amount() {
        this.fieldName="amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> uqFlag() {
        this.fieldName="uq_flag";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> billState() {
        this.fieldName="bill_state";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> claimState() {
        this.fieldName="claim_state";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> discountCashState() {
        this.fieldName="discount_cash_state";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> pledgeState() {
        this.fieldName="pledge_state";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> contractNo() {
        this.fieldName="contract_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> version() {
        this.fieldName="version";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> payState() {
        this.fieldName="pay_state";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> draftDate() {
        this.fieldName="draft_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherBill, BaseDeletedMapper> expirationDate() {
        this.fieldName="expiration_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherBill.class, BaseDeletedMapper.class);
        }

    }
}
