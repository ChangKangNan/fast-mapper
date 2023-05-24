package cn.ft.ckn.test.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.test.fm.bean.Contract;

public class ContractAction {
public static class InsertMapper extends InsertDao<Contract, InsertMapper> {
        public InsertMapper(FastMapperParam FastMapperParam){
        super(FastMapperParam,Contract.class, InsertMapper.class);
        }
}

public static class BaseSelectMapper extends BaseSelectAction<Contract, BaseSelectMapper> {
        public BaseSelectMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> id() {
        this.fieldName="id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> globalId() {
        this.fieldName="global_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> no() {
        this.fieldName="no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> externalNo() {
        this.fieldName="external_no";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> contractType() {
        this.fieldName="contract_type";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> kycIds() {
        this.fieldName="kyc_ids";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> contractYear() {
        this.fieldName="contract_year";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> signDate() {
        this.fieldName="sign_date";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> signPlace() {
        this.fieldName="sign_place";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> dealTemplateProvider() {
        this.fieldName="deal_template_provider";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> pid() {
        this.fieldName="pid";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> completeStatus() {
        this.fieldName="complete_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> contractDifference() {
        this.fieldName="contract_difference";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> status() {
        this.fieldName="status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> oldStatus() {
        this.fieldName="old_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> doubleSignStatus() {
        this.fieldName="double_sign_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> stopStatus() {
        this.fieldName="stop_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> closeStatus() {
        this.fieldName="close_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> cancelStatus() {
        this.fieldName="cancel_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> cancelDoubleSignStatus() {
        this.fieldName="cancel_double_sign_status";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> settled() {
        this.fieldName="settled";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> parentId() {
        this.fieldName="parent_id";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> remark() {
        this.fieldName="remark";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> changeDistinguish() {
        this.fieldName="change_distinguish";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> createOrg() {
        this.fieldName="create_org";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> createBy() {
        this.fieldName="create_by";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> updateBy() {
        this.fieldName="update_by";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> createTime() {
        this.fieldName="create_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> updateTime() {
        this.fieldName="update_time";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }


        public SelectCriteria<Contract, BaseSelectMapper> deleted() {
        this.fieldName="deleted";
        return new SelectCriteria<>(this.FastMapperParam, this.fieldName,Contract.class, BaseSelectMapper.class);
        }

}

public static class BaseUpdateMapper extends BaseUpdateAction<Contract, BaseUpdateMapper> {

        public BaseUpdateMapper(FastMapperParam FastMapperParam) {
            super(FastMapperParam,Contract.class, BaseUpdateMapper.class);
        }

        public UpdateCriteria<Contract, BaseUpdateMapper> id() {
        this.fieldName="id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> globalId() {
        this.fieldName="global_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> no() {
        this.fieldName="no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> externalNo() {
        this.fieldName="external_no";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> contractType() {
        this.fieldName="contract_type";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> kycIds() {
        this.fieldName="kyc_ids";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> contractYear() {
        this.fieldName="contract_year";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> signDate() {
        this.fieldName="sign_date";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> signPlace() {
        this.fieldName="sign_place";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> dealTemplateProvider() {
        this.fieldName="deal_template_provider";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> pid() {
        this.fieldName="pid";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> completeStatus() {
        this.fieldName="complete_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> contractDifference() {
        this.fieldName="contract_difference";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> status() {
        this.fieldName="status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> oldStatus() {
        this.fieldName="old_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> doubleSignStatus() {
        this.fieldName="double_sign_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> stopStatus() {
        this.fieldName="stop_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> closeStatus() {
        this.fieldName="close_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> cancelStatus() {
        this.fieldName="cancel_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> cancelDoubleSignStatus() {
        this.fieldName="cancel_double_sign_status";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> settled() {
        this.fieldName="settled";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> parentId() {
        this.fieldName="parent_id";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> remark() {
        this.fieldName="remark";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> changeDistinguish() {
        this.fieldName="change_distinguish";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> createOrg() {
        this.fieldName="create_org";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> createBy() {
        this.fieldName="create_by";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> updateBy() {
        this.fieldName="update_by";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> createTime() {
        this.fieldName="create_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> updateTime() {
        this.fieldName="update_time";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }


        public UpdateCriteria<Contract, BaseUpdateMapper> deleted() {
        this.fieldName="deleted";
        return new UpdateCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseUpdateMapper.class);
        }

}

public static class BaseDeletedMapper extends BaseDeletedAction<Contract, BaseDeletedMapper> {
        public BaseDeletedMapper(FastMapperParam FastMapperParam) {
        super(FastMapperParam,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> id() {
        this.fieldName="id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> globalId() {
        this.fieldName="global_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> no() {
        this.fieldName="no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> externalNo() {
        this.fieldName="external_no";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> contractType() {
        this.fieldName="contract_type";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> kycIds() {
        this.fieldName="kyc_ids";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> contractYear() {
        this.fieldName="contract_year";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> signDate() {
        this.fieldName="sign_date";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> signPlace() {
        this.fieldName="sign_place";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> dealTemplateProvider() {
        this.fieldName="deal_template_provider";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> pid() {
        this.fieldName="pid";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> completeStatus() {
        this.fieldName="complete_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> contractDifference() {
        this.fieldName="contract_difference";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> status() {
        this.fieldName="status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> oldStatus() {
        this.fieldName="old_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> doubleSignStatus() {
        this.fieldName="double_sign_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> stopStatus() {
        this.fieldName="stop_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> closeStatus() {
        this.fieldName="close_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> cancelStatus() {
        this.fieldName="cancel_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> cancelDoubleSignStatus() {
        this.fieldName="cancel_double_sign_status";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> settled() {
        this.fieldName="settled";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> parentId() {
        this.fieldName="parent_id";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> remark() {
        this.fieldName="remark";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> changeDistinguish() {
        this.fieldName="change_distinguish";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> useOrgs() {
        this.fieldName="use_orgs";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> createOrg() {
        this.fieldName="create_org";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> createBy() {
        this.fieldName="create_by";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> updateBy() {
        this.fieldName="update_by";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> createTime() {
        this.fieldName="create_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> updateTime() {
        this.fieldName="update_time";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }


        public DeletedCriteria<Contract, BaseDeletedMapper> deleted() {
        this.fieldName="deleted";
        return new DeletedCriteria<>(this.FastMapperParam,this.fieldName,Contract.class, BaseDeletedMapper.class);
        }

    }
}
