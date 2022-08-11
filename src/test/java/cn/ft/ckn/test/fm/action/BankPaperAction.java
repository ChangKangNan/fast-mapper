package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.BankPaper;

public class BankPaperAction {
public static class InsertMapper extends InsertDao<BankPaper, InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
        super(splicingParam,BankPaper.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<BankPaper, BaseSelectMapper> {
        public BaseSelectMapper(SplicingParam splicingParam) {
        super(splicingParam,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> globalId() {
        this.fieldName="global_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> no() {
        this.fieldName="no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> fundingBankPaperOpenId() {
        this.fieldName="funding_bank_paper_open_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> fundingBankPaperId() {
        this.fieldName="funding_bank_paper_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> paperType() {
        this.fieldName="paper_type";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> paperNo() {
        this.fieldName="paper_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> paperAmount() {
        this.fieldName="paper_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> acceptor() {
        this.fieldName="acceptor";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> noticeAccount() {
        this.fieldName="notice_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> noticeBank() {
        this.fieldName="notice_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> drawerCompany() {
        this.fieldName="drawer_company";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> drawerAccount() {
        this.fieldName="drawer_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> drawerBank() {
        this.fieldName="drawer_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payeeRcmsKycId() {
        this.fieldName="payee_rcms_kyc_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payeeRcmsKycCompany() {
        this.fieldName="payee_rcms_kyc_company";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payeeAccount() {
        this.fieldName="payee_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payeeBank() {
        this.fieldName="payee_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> openTime() {
        this.fieldName="open_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> limitDay() {
        this.fieldName="limit_day";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> lastTime() {
        this.fieldName="last_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> currency() {
        this.fieldName="currency";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> poundageProportion() {
        this.fieldName="poundage_proportion";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> poundage() {
        this.fieldName="poundage";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> receiveBankNo() {
        this.fieldName="receive_bank_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> draftDate() {
        this.fieldName="draft_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> submitOrder() {
        this.fieldName="submit_order";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> submitOrderTime() {
        this.fieldName="submit_order_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> acceptanceTime() {
        this.fieldName="acceptance_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> confirmAcceptance() {
        this.fieldName="confirm_acceptance";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> remark() {
        this.fieldName="remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> openType() {
        this.fieldName="open_type";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> isHavePledges() {
        this.fieldName="is_have_pledges";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> differDay() {
        this.fieldName="differ_day";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> submitUserId() {
        this.fieldName="submit_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> submitUserName() {
        this.fieldName="submit_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> submitUserAccount() {
        this.fieldName="submit_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> submitTime() {
        this.fieldName="submit_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> isExist() {
        this.fieldName="is_exist";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payInterestStatus() {
        this.fieldName="pay_interest_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> honourStatus() {
        this.fieldName="honour_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payInterestAmount() {
        this.fieldName="pay_interest_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> payInterestTime() {
        this.fieldName="pay_interest_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> honourTime() {
        this.fieldName="honour_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> discountStatus() {
        this.fieldName="discount_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> pledgeStatus() {
        this.fieldName="pledge_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> receivePaperTime() {
        this.fieldName="receive_paper_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> haveBeenUsedAmount() {
        this.fieldName="have_been_used_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> remainingAmount() {
        this.fieldName="remaining_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> claimLotId() {
        this.fieldName="claim_lot_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> salesman() {
        this.fieldName="salesman";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> departmentId() {
        this.fieldName="department_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> department() {
        this.fieldName="department";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaper, BaseSelectMapper> deleted() {
        this.fieldName="deleted";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaper.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<BankPaper, BaseUpdateMapper> {

        public BaseUpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,BankPaper.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<BankPaper, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> globalId() {
        this.fieldName="global_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> no() {
        this.fieldName="no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> fundingBankPaperOpenId() {
        this.fieldName="funding_bank_paper_open_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> fundingBankPaperId() {
        this.fieldName="funding_bank_paper_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> paperType() {
        this.fieldName="paper_type";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> paperNo() {
        this.fieldName="paper_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> paperAmount() {
        this.fieldName="paper_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> acceptor() {
        this.fieldName="acceptor";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> noticeAccount() {
        this.fieldName="notice_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> noticeBank() {
        this.fieldName="notice_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> drawerCompany() {
        this.fieldName="drawer_company";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> drawerAccount() {
        this.fieldName="drawer_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> drawerBank() {
        this.fieldName="drawer_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payeeRcmsKycId() {
        this.fieldName="payee_rcms_kyc_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payeeRcmsKycCompany() {
        this.fieldName="payee_rcms_kyc_company";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payeeAccount() {
        this.fieldName="payee_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payeeBank() {
        this.fieldName="payee_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> openTime() {
        this.fieldName="open_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> limitDay() {
        this.fieldName="limit_day";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> lastTime() {
        this.fieldName="last_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> currency() {
        this.fieldName="currency";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> poundageProportion() {
        this.fieldName="poundage_proportion";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> poundage() {
        this.fieldName="poundage";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> receiveBankNo() {
        this.fieldName="receive_bank_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> draftDate() {
        this.fieldName="draft_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> submitOrder() {
        this.fieldName="submit_order";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> submitOrderTime() {
        this.fieldName="submit_order_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> acceptanceTime() {
        this.fieldName="acceptance_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> confirmAcceptance() {
        this.fieldName="confirm_acceptance";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> remark() {
        this.fieldName="remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> openType() {
        this.fieldName="open_type";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> isHavePledges() {
        this.fieldName="is_have_pledges";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> differDay() {
        this.fieldName="differ_day";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> submitUserId() {
        this.fieldName="submit_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> submitUserName() {
        this.fieldName="submit_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> submitUserAccount() {
        this.fieldName="submit_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> submitTime() {
        this.fieldName="submit_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> isExist() {
        this.fieldName="is_exist";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payInterestStatus() {
        this.fieldName="pay_interest_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> honourStatus() {
        this.fieldName="honour_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payInterestAmount() {
        this.fieldName="pay_interest_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> payInterestTime() {
        this.fieldName="pay_interest_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> honourTime() {
        this.fieldName="honour_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> discountStatus() {
        this.fieldName="discount_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> pledgeStatus() {
        this.fieldName="pledge_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> receivePaperTime() {
        this.fieldName="receive_paper_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> haveBeenUsedAmount() {
        this.fieldName="have_been_used_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> remainingAmount() {
        this.fieldName="remaining_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> claimLotId() {
        this.fieldName="claim_lot_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> salesman() {
        this.fieldName="salesman";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> departmentId() {
        this.fieldName="department_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> department() {
        this.fieldName="department";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaper, BaseUpdateMapper> deleted() {
        this.fieldName="deleted";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<BankPaper, BaseDeletedMapper> {
        public BaseDeletedMapper(SplicingParam splicingParam) {
        super(splicingParam,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> globalId() {
        this.fieldName="global_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> no() {
        this.fieldName="no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> fundingBankPaperApplyId() {
        this.fieldName="funding_bank_paper_apply_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> fundingBankPaperOpenId() {
        this.fieldName="funding_bank_paper_open_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> fundingBankPaperId() {
        this.fieldName="funding_bank_paper_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> paperType() {
        this.fieldName="paper_type";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> paperNo() {
        this.fieldName="paper_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> paperAmount() {
        this.fieldName="paper_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> acceptor() {
        this.fieldName="acceptor";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> noticeAccount() {
        this.fieldName="notice_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> noticeBank() {
        this.fieldName="notice_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> drawerCompany() {
        this.fieldName="drawer_company";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> drawerAccount() {
        this.fieldName="drawer_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> drawerBank() {
        this.fieldName="drawer_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payeeRcmsKycId() {
        this.fieldName="payee_rcms_kyc_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payeeRcmsKycCompany() {
        this.fieldName="payee_rcms_kyc_company";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payeeAccount() {
        this.fieldName="payee_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payeeBank() {
        this.fieldName="payee_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> openTime() {
        this.fieldName="open_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> limitDay() {
        this.fieldName="limit_day";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> lastTime() {
        this.fieldName="last_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> currency() {
        this.fieldName="currency";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> poundageProportion() {
        this.fieldName="poundage_proportion";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> poundage() {
        this.fieldName="poundage";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> receiveBankNo() {
        this.fieldName="receive_bank_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> draftDate() {
        this.fieldName="draft_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> submitOrder() {
        this.fieldName="submit_order";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> submitOrderTime() {
        this.fieldName="submit_order_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> acceptanceTime() {
        this.fieldName="acceptance_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> confirmAcceptance() {
        this.fieldName="confirm_acceptance";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> remark() {
        this.fieldName="remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> openType() {
        this.fieldName="open_type";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> isHavePledges() {
        this.fieldName="is_have_pledges";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> differDay() {
        this.fieldName="differ_day";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> submitUserId() {
        this.fieldName="submit_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> submitUserName() {
        this.fieldName="submit_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> submitUserAccount() {
        this.fieldName="submit_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> submitTime() {
        this.fieldName="submit_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> isExist() {
        this.fieldName="is_exist";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payInterestStatus() {
        this.fieldName="pay_interest_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> honourStatus() {
        this.fieldName="honour_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payInterestAmount() {
        this.fieldName="pay_interest_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> payInterestTime() {
        this.fieldName="pay_interest_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> honourTime() {
        this.fieldName="honour_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> discountStatus() {
        this.fieldName="discount_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> pledgeStatus() {
        this.fieldName="pledge_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> receivePaperTime() {
        this.fieldName="receive_paper_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> haveBeenUsedAmount() {
        this.fieldName="have_been_used_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> remainingAmount() {
        this.fieldName="remaining_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> claimLotId() {
        this.fieldName="claim_lot_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> salesman() {
        this.fieldName="salesman";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> departmentId() {
        this.fieldName="department_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> department() {
        this.fieldName="department";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaper, BaseDeletedMapper> deleted() {
        this.fieldName="deleted";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaper.class, BaseDeletedMapper.class);
        }

    }
}
