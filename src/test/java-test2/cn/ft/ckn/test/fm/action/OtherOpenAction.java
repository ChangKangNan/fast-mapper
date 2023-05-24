package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.OtherOpen;

public class OtherOpenAction {
public static class InsertMapper extends InsertDao<OtherOpen, InsertMapper> {
        public InsertMapper(FastMapperParam FastMapperParam){
        super(FastMapperParam,OtherOpen.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<OtherOpen, BaseSelectMapper> {
        public BaseSelectMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> institutionId() {
        this.fieldName="institution_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> orgName() {
        this.fieldName="org_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> spNo() {
        this.fieldName="sp_no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> billType() {
        this.fieldName="bill_type";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> loggingState() {
        this.fieldName="logging_state";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> currency() {
        this.fieldName="currency";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> kycId() {
        this.fieldName="kyc_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> kycAccountName() {
        this.fieldName="kyc_account_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> spAccountId() {
        this.fieldName="sp_account_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> spAccountRemark() {
        this.fieldName="sp_account_remark";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> spAmount() {
        this.fieldName="sp_amount";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> spQuantity() {
        this.fieldName="sp_quantity";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> acceptor() {
        this.fieldName="acceptor";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> draftDate() {
        this.fieldName="draft_date";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> receiveDate() {
        this.fieldName="receive_date";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> remark() {
        this.fieldName="remark";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> version() {
        this.fieldName="version";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OtherOpen, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<OtherOpen, BaseUpdateMapper> {

        public BaseUpdateMapper(FastMapperParam FastMapperParam) {
            super(FastMapperParam,OtherOpen.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<OtherOpen, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> institutionId() {
        this.fieldName="institution_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> orgName() {
        this.fieldName="org_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> spNo() {
        this.fieldName="sp_no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> billType() {
        this.fieldName="bill_type";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> loggingState() {
        this.fieldName="logging_state";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> currency() {
        this.fieldName="currency";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> kycId() {
        this.fieldName="kyc_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> kycAccountName() {
        this.fieldName="kyc_account_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> spAccountId() {
        this.fieldName="sp_account_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> spAccountRemark() {
        this.fieldName="sp_account_remark";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> spAmount() {
        this.fieldName="sp_amount";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> spQuantity() {
        this.fieldName="sp_quantity";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> acceptor() {
        this.fieldName="acceptor";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> draftDate() {
        this.fieldName="draft_date";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> receiveDate() {
        this.fieldName="receive_date";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> remark() {
        this.fieldName="remark";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> version() {
        this.fieldName="version";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OtherOpen, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<OtherOpen, BaseDeletedMapper> {
        public BaseDeletedMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> institutionId() {
        this.fieldName="institution_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> orgName() {
        this.fieldName="org_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> spNo() {
        this.fieldName="sp_no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> billType() {
        this.fieldName="bill_type";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> loggingState() {
        this.fieldName="logging_state";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> currency() {
        this.fieldName="currency";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> kycId() {
        this.fieldName="kyc_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> kycAccountName() {
        this.fieldName="kyc_account_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> spAccountId() {
        this.fieldName="sp_account_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> spAccountRemark() {
        this.fieldName="sp_account_remark";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> spAmount() {
        this.fieldName="sp_amount";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> spQuantity() {
        this.fieldName="sp_quantity";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> acceptor() {
        this.fieldName="acceptor";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> draftDate() {
        this.fieldName="draft_date";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> receiveDate() {
        this.fieldName="receive_date";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> remark() {
        this.fieldName="remark";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> version() {
        this.fieldName="version";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OtherOpen, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
        }

    }
}
