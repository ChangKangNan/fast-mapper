package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.OpenBill;

public class OpenBillAction {
public static class InsertMapper extends InsertDao<OpenBill, InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
        super(splicingParam,OpenBill.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<OpenBill, BaseSelectMapper> {
        public BaseSelectMapper(SplicingParam splicingParam) {
        super(splicingParam,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> orgId() {
        this.fieldName="org_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> applyNo() {
        this.fieldName="apply_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> billNo() {
        this.fieldName="bill_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> billType() {
        this.fieldName="bill_type";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> process() {
        this.fieldName="process";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> contractNo() {
        this.fieldName="contract_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> ourAccountId() {
        this.fieldName="our_account_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> ourAccountRemark() {
        this.fieldName="our_account_remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> kycId() {
        this.fieldName="kyc_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> kycAccountName() {
        this.fieldName="kyc_account_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> currency() {
        this.fieldName="currency";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> applyAmount() {
        this.fieldName="apply_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> leaveAmount() {
        this.fieldName="leave_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> staffAccount() {
        this.fieldName="staff_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> staffName() {
        this.fieldName="staff_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> applyDate() {
        this.fieldName="apply_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> remark() {
        this.fieldName="remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> feeDeductionNo() {
        this.fieldName="fee_deduction_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> feeDeductionId() {
        this.fieldName="fee_deduction_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> impawn() {
        this.fieldName="impawn";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> version() {
        this.fieldName="version";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }


        public SelectCriteria<OpenBill, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,OpenBill.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<OpenBill, BaseUpdateMapper> {

        public BaseUpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,OpenBill.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<OpenBill, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> orgId() {
        this.fieldName="org_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> applyNo() {
        this.fieldName="apply_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> billNo() {
        this.fieldName="bill_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> billType() {
        this.fieldName="bill_type";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> process() {
        this.fieldName="process";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> contractNo() {
        this.fieldName="contract_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> ourAccountId() {
        this.fieldName="our_account_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> ourAccountRemark() {
        this.fieldName="our_account_remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> kycId() {
        this.fieldName="kyc_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> kycAccountName() {
        this.fieldName="kyc_account_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> currency() {
        this.fieldName="currency";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> applyAmount() {
        this.fieldName="apply_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> leaveAmount() {
        this.fieldName="leave_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> staffAccount() {
        this.fieldName="staff_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> staffName() {
        this.fieldName="staff_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> applyDate() {
        this.fieldName="apply_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> remark() {
        this.fieldName="remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> feeDeductionNo() {
        this.fieldName="fee_deduction_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> feeDeductionId() {
        this.fieldName="fee_deduction_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> impawn() {
        this.fieldName="impawn";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> version() {
        this.fieldName="version";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<OpenBill, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<OpenBill, BaseDeletedMapper> {
        public BaseDeletedMapper(SplicingParam splicingParam) {
        super(splicingParam,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> orgId() {
        this.fieldName="org_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> applyNo() {
        this.fieldName="apply_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> billNo() {
        this.fieldName="bill_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> billType() {
        this.fieldName="bill_type";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> process() {
        this.fieldName="process";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> contractNo() {
        this.fieldName="contract_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> ourAccountId() {
        this.fieldName="our_account_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> ourAccountRemark() {
        this.fieldName="our_account_remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> kycId() {
        this.fieldName="kyc_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> kycAccountName() {
        this.fieldName="kyc_account_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> currency() {
        this.fieldName="currency";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> applyAmount() {
        this.fieldName="apply_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> leaveAmount() {
        this.fieldName="leave_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> staffAccount() {
        this.fieldName="staff_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> staffName() {
        this.fieldName="staff_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> applyDate() {
        this.fieldName="apply_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> remark() {
        this.fieldName="remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> feeDeductionNo() {
        this.fieldName="fee_deduction_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> feeDeductionId() {
        this.fieldName="fee_deduction_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> impawn() {
        this.fieldName="impawn";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> deleteFlag() {
        this.fieldName="delete_flag";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> version() {
        this.fieldName="version";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<OpenBill, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,OpenBill.class, BaseDeletedMapper.class);
        }

    }
}
