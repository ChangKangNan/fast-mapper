package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.BankPaperApply;

public class BankPaperApplyAction {
public static class InsertMapper extends InsertDao<BankPaperApply, InsertMapper> {
        public InsertMapper(SplicingParam splicingParam){
        super(splicingParam,BankPaperApply.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<BankPaperApply, BaseSelectMapper> {
        public BaseSelectMapper(SplicingParam splicingParam) {
        super(splicingParam,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> globalId() {
        this.fieldName="global_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> no() {
        this.fieldName="no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> paperType() {
        this.fieldName="paper_type";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> lianHangNo() {
        this.fieldName="lian_hang_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> acceptor() {
        this.fieldName="acceptor";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> noticeAccount() {
        this.fieldName="notice_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> noticeBank() {
        this.fieldName="notice_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> drawerCompany() {
        this.fieldName="drawer_company";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> drawerAccount() {
        this.fieldName="drawer_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> drawerBank() {
        this.fieldName="drawer_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> payeeRcmsKycId() {
        this.fieldName="payee_rcms_kyc_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> payeeRcmsKycCompany() {
        this.fieldName="payee_rcms_kyc_company";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> payeeAccount() {
        this.fieldName="payee_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> payeeBank() {
        this.fieldName="payee_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> openTime() {
        this.fieldName="open_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> limitDay() {
        this.fieldName="limit_day";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> adjustDay() {
        this.fieldName="adjust_day";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> lastTime() {
        this.fieldName="last_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> moneySubmitOrder() {
        this.fieldName="money_submit_order";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> moneySubmitOrderTime() {
        this.fieldName="money_submit_order_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> performSubmitOrder() {
        this.fieldName="perform_submit_order";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> performSubmitOrderTime() {
        this.fieldName="perform_submit_order_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> acceptanceTime() {
        this.fieldName="acceptance_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> confirmAcceptance() {
        this.fieldName="confirm_acceptance";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> currency() {
        this.fieldName="currency";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> canOpenAmount() {
        this.fieldName="can_open_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> poundageProportion() {
        this.fieldName="poundage_proportion";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> poundage() {
        this.fieldName="poundage";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> receiveBankNo() {
        this.fieldName="receive_bank_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> draftDate() {
        this.fieldName="draft_date";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> contractAmount() {
        this.fieldName="contract_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> alreadyOpenAmount() {
        this.fieldName="already_open_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> applyAmount() {
        this.fieldName="apply_amount";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> exposurePledge() {
        this.fieldName="exposure_pledge";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> billPledge() {
        this.fieldName="bill_pledge";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> depositPledge() {
        this.fieldName="deposit_pledge";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> creditNo() {
        this.fieldName="credit_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> receivePaperTime() {
        this.fieldName="receive_paper_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> confirmNegotiationBank() {
        this.fieldName="confirm_negotiation_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> confirmNegotiationAccount() {
        this.fieldName="confirm_negotiation_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> creditReceiveBank() {
        this.fieldName="credit_receive_bank";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> creditReceiveBankAccount() {
        this.fieldName="credit_receive_bank_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> kingdeeNo() {
        this.fieldName="kingdee_no";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> remark() {
        this.fieldName="remark";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> applyStatus() {
        this.fieldName="apply_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> openStatus() {
        this.fieldName="open_status";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> openType() {
        this.fieldName="open_type";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> salesman() {
        this.fieldName="salesman";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> departmentId() {
        this.fieldName="department_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> department() {
        this.fieldName="department";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> submitUserId() {
        this.fieldName="submit_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> submitUserName() {
        this.fieldName="submit_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> submitUserAccount() {
        this.fieldName="submit_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> submitTime() {
        this.fieldName="submit_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }


        public SelectCriteria<BankPaperApply, BaseSelectMapper> deleted() {
        this.fieldName="deleted";
        return new SelectCriteria<>(this.splicingParam, this.fieldName,BankPaperApply.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<BankPaperApply, BaseUpdateMapper> {

        public BaseUpdateMapper(SplicingParam splicingParam) {
            super(splicingParam,BankPaperApply.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> globalId() {
        this.fieldName="global_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> no() {
        this.fieldName="no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> paperType() {
        this.fieldName="paper_type";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> lianHangNo() {
        this.fieldName="lian_hang_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> acceptor() {
        this.fieldName="acceptor";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> noticeAccount() {
        this.fieldName="notice_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> noticeBank() {
        this.fieldName="notice_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> drawerCompany() {
        this.fieldName="drawer_company";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> drawerAccount() {
        this.fieldName="drawer_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> drawerBank() {
        this.fieldName="drawer_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> payeeRcmsKycId() {
        this.fieldName="payee_rcms_kyc_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> payeeRcmsKycCompany() {
        this.fieldName="payee_rcms_kyc_company";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> payeeAccount() {
        this.fieldName="payee_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> payeeBank() {
        this.fieldName="payee_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> openTime() {
        this.fieldName="open_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> limitDay() {
        this.fieldName="limit_day";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> adjustDay() {
        this.fieldName="adjust_day";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> lastTime() {
        this.fieldName="last_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> moneySubmitOrder() {
        this.fieldName="money_submit_order";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> moneySubmitOrderTime() {
        this.fieldName="money_submit_order_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> performSubmitOrder() {
        this.fieldName="perform_submit_order";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> performSubmitOrderTime() {
        this.fieldName="perform_submit_order_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> acceptanceTime() {
        this.fieldName="acceptance_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> confirmAcceptance() {
        this.fieldName="confirm_acceptance";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> currency() {
        this.fieldName="currency";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> canOpenAmount() {
        this.fieldName="can_open_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> poundageProportion() {
        this.fieldName="poundage_proportion";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> poundage() {
        this.fieldName="poundage";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> receiveBankNo() {
        this.fieldName="receive_bank_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> draftDate() {
        this.fieldName="draft_date";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> contractAmount() {
        this.fieldName="contract_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> alreadyOpenAmount() {
        this.fieldName="already_open_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> applyAmount() {
        this.fieldName="apply_amount";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> exposurePledge() {
        this.fieldName="exposure_pledge";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> billPledge() {
        this.fieldName="bill_pledge";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> depositPledge() {
        this.fieldName="deposit_pledge";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> creditNo() {
        this.fieldName="credit_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> receivePaperTime() {
        this.fieldName="receive_paper_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> confirmNegotiationBank() {
        this.fieldName="confirm_negotiation_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> confirmNegotiationAccount() {
        this.fieldName="confirm_negotiation_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> creditReceiveBank() {
        this.fieldName="credit_receive_bank";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> creditReceiveBankAccount() {
        this.fieldName="credit_receive_bank_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> kingdeeNo() {
        this.fieldName="kingdee_no";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> remark() {
        this.fieldName="remark";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> applyStatus() {
        this.fieldName="apply_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> openStatus() {
        this.fieldName="open_status";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> openType() {
        this.fieldName="open_type";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> salesman() {
        this.fieldName="salesman";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> departmentId() {
        this.fieldName="department_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> department() {
        this.fieldName="department";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> submitUserId() {
        this.fieldName="submit_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> submitUserName() {
        this.fieldName="submit_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> submitUserAccount() {
        this.fieldName="submit_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> submitTime() {
        this.fieldName="submit_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<BankPaperApply, BaseUpdateMapper> deleted() {
        this.fieldName="deleted";
        return new UpdateCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<BankPaperApply, BaseDeletedMapper> {
        public BaseDeletedMapper(SplicingParam splicingParam) {
        super(splicingParam,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> globalId() {
        this.fieldName="global_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> no() {
        this.fieldName="no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> paperType() {
        this.fieldName="paper_type";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> lianHangNo() {
        this.fieldName="lian_hang_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> acceptor() {
        this.fieldName="acceptor";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> noticeAccount() {
        this.fieldName="notice_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> noticeBank() {
        this.fieldName="notice_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> drawerCompany() {
        this.fieldName="drawer_company";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> drawerAccount() {
        this.fieldName="drawer_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> drawerBank() {
        this.fieldName="drawer_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> payeeRcmsKycId() {
        this.fieldName="payee_rcms_kyc_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> payeeRcmsKycCompany() {
        this.fieldName="payee_rcms_kyc_company";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> payeeAccount() {
        this.fieldName="payee_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> payeeBank() {
        this.fieldName="payee_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> openTime() {
        this.fieldName="open_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> billDueDate() {
        this.fieldName="bill_due_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> limitDay() {
        this.fieldName="limit_day";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> adjustDay() {
        this.fieldName="adjust_day";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> lastTime() {
        this.fieldName="last_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> moneySubmitOrder() {
        this.fieldName="money_submit_order";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> moneySubmitOrderTime() {
        this.fieldName="money_submit_order_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> performSubmitOrder() {
        this.fieldName="perform_submit_order";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> performSubmitOrderTime() {
        this.fieldName="perform_submit_order_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> acceptanceTime() {
        this.fieldName="acceptance_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> confirmAcceptance() {
        this.fieldName="confirm_acceptance";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> currency() {
        this.fieldName="currency";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> canOpenAmount() {
        this.fieldName="can_open_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> poundageProportion() {
        this.fieldName="poundage_proportion";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> poundage() {
        this.fieldName="poundage";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> receiveBankNo() {
        this.fieldName="receive_bank_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> draftDate() {
        this.fieldName="draft_date";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> contractAmount() {
        this.fieldName="contract_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> alreadyOpenAmount() {
        this.fieldName="already_open_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> applyAmount() {
        this.fieldName="apply_amount";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> exposurePledge() {
        this.fieldName="exposure_pledge";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> billPledge() {
        this.fieldName="bill_pledge";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> depositPledge() {
        this.fieldName="deposit_pledge";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> creditNo() {
        this.fieldName="credit_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> receivePaperTime() {
        this.fieldName="receive_paper_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> confirmNegotiationBank() {
        this.fieldName="confirm_negotiation_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> confirmNegotiationAccount() {
        this.fieldName="confirm_negotiation_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> creditReceiveBank() {
        this.fieldName="credit_receive_bank";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> creditReceiveBankAccount() {
        this.fieldName="credit_receive_bank_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> kingdeeSync() {
        this.fieldName="kingdee_sync";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> kingdeeNo() {
        this.fieldName="kingdee_no";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> kingdeeRemark() {
        this.fieldName="kingdee_remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> remark() {
        this.fieldName="remark";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> applyStatus() {
        this.fieldName="apply_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> openStatus() {
        this.fieldName="open_status";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> openType() {
        this.fieldName="open_type";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> salesman() {
        this.fieldName="salesman";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> departmentId() {
        this.fieldName="department_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> department() {
        this.fieldName="department";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> submitUserId() {
        this.fieldName="submit_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> submitUserName() {
        this.fieldName="submit_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> submitUserAccount() {
        this.fieldName="submit_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> submitTime() {
        this.fieldName="submit_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<BankPaperApply, BaseDeletedMapper> deleted() {
        this.fieldName="deleted";
        return new DeletedCriteria<>(this.splicingParam,this.fieldName,BankPaperApply.class, BaseDeletedMapper.class);
        }

    }
}
