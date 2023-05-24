package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.PayInterestDetail;

public class PayInterestDetailAction {
public static class InsertMapper extends InsertDao<PayInterestDetail, InsertMapper> {
        public InsertMapper(FastMapperParam FastMapperParam){
        super(FastMapperParam,PayInterestDetail.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<PayInterestDetail, BaseSelectMapper> {
        public BaseSelectMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> globalId() {
        this.fieldName="global_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> no() {
        this.fieldName="no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> fundingPayInterestId() {
        this.fieldName="funding_pay_interest_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> paperSnapshot() {
        this.fieldName="paper_snapshot";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> paperType() {
        this.fieldName="paper_type";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> paperNo() {
        this.fieldName="paper_no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> paperId() {
        this.fieldName="paper_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> adjustDays() {
        this.fieldName="adjust_days";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> interestRate() {
        this.fieldName="interest_rate";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> payInterestAmount() {
        this.fieldName="pay_interest_amount";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> payStatus() {
        this.fieldName="pay_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> payTime() {
        this.fieldName="pay_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> createUserId() {
        this.fieldName="create_user_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> createUserName() {
        this.fieldName="create_user_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }


        public SelectCriteria<PayInterestDetail, BaseSelectMapper> deleted() {
        this.fieldName="deleted";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,PayInterestDetail.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<PayInterestDetail, BaseUpdateMapper> {

        public BaseUpdateMapper(FastMapperParam FastMapperParam) {
            super(FastMapperParam,PayInterestDetail.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> globalId() {
        this.fieldName="global_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> no() {
        this.fieldName="no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> fundingPayInterestId() {
        this.fieldName="funding_pay_interest_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> paperSnapshot() {
        this.fieldName="paper_snapshot";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> paperType() {
        this.fieldName="paper_type";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> paperNo() {
        this.fieldName="paper_no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> paperId() {
        this.fieldName="paper_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> adjustDays() {
        this.fieldName="adjust_days";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> interestRate() {
        this.fieldName="interest_rate";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> payInterestAmount() {
        this.fieldName="pay_interest_amount";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> payStatus() {
        this.fieldName="pay_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> payTime() {
        this.fieldName="pay_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> createUserId() {
        this.fieldName="create_user_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> createUserName() {
        this.fieldName="create_user_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<PayInterestDetail, BaseUpdateMapper> deleted() {
        this.fieldName="deleted";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<PayInterestDetail, BaseDeletedMapper> {
        public BaseDeletedMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> globalId() {
        this.fieldName="global_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> no() {
        this.fieldName="no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> fundingPayInterestId() {
        this.fieldName="funding_pay_interest_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> paperSnapshot() {
        this.fieldName="paper_snapshot";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> paperType() {
        this.fieldName="paper_type";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> paperNo() {
        this.fieldName="paper_no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> paperId() {
        this.fieldName="paper_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> adjustDays() {
        this.fieldName="adjust_days";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> interestRate() {
        this.fieldName="interest_rate";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> payInterestAmount() {
        this.fieldName="pay_interest_amount";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> payStatus() {
        this.fieldName="pay_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> payTime() {
        this.fieldName="pay_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> createUserId() {
        this.fieldName="create_user_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> createUserName() {
        this.fieldName="create_user_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> createUserAccount() {
        this.fieldName="create_user_account";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> updateUserId() {
        this.fieldName="update_user_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> updateUserName() {
        this.fieldName="update_user_name";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> updateUserAccount() {
        this.fieldName="update_user_account";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<PayInterestDetail, BaseDeletedMapper> deleted() {
        this.fieldName="deleted";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,PayInterestDetail.class, BaseDeletedMapper.class);
        }

    }
}
