package cn.ft.ckn.fastmapper.fm.action;
import cn.ft.ckn.fastmapper.component.*;
import cn.ft.ckn.fastmapper.fm.OtherOpen;

public class OtherOpenAction {
public static class InsertMapper extends InsertDao<OtherOpen, InsertMapper> {
public InsertMapper(SplicingParam splicingParam){
super(splicingParam,OtherOpen.class, InsertMapper.class);
}
}

public static class BaseSelectMapper extends BaseSelectAction<OtherOpen, BaseSelectMapper> {
public BaseSelectMapper(SplicingParam splicingParam) {
super(splicingParam,OtherOpen.class, BaseSelectMapper.class);
}


    public SelectCriteria<OtherOpen, BaseSelectMapper> id() {
    this.fieldName="id";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> institutionId() {
    this.fieldName="institutionId";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> orgName() {
    this.fieldName="orgName";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> spNo() {
    this.fieldName="spNo";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> billType() {
    this.fieldName="billType";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> loggingState() {
    this.fieldName="loggingState";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> currency() {
    this.fieldName="currency";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> kycId() {
    this.fieldName="kycId";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> kycAccountName() {
    this.fieldName="kycAccountName";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> spAccountId() {
    this.fieldName="spAccountId";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> spAccountRemark() {
    this.fieldName="spAccountRemark";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> spAmount() {
    this.fieldName="spAmount";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> spQuantity() {
    this.fieldName="spQuantity";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> acceptor() {
    this.fieldName="acceptor";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> draftDate() {
    this.fieldName="draftDate";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> receiveDate() {
    this.fieldName="receiveDate";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> billDueDate() {
    this.fieldName="billDueDate";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> remark() {
    this.fieldName="remark";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> deleteFlag() {
    this.fieldName="deleteFlag";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> createUserAccount() {
    this.fieldName="createUserAccount";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> createUserName() {
    this.fieldName="createUserName";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> createUserId() {
    this.fieldName="createUserId";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> updateUserId() {
    this.fieldName="updateUserId";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> version() {
    this.fieldName="version";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> createTime() {
    this.fieldName="createTime";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }


    public SelectCriteria<OtherOpen, BaseSelectMapper> updateTime() {
    this.fieldName="updateTime";
    return new SelectCriteria<>(this.splicingParam, this.fieldName,OtherOpen.class, BaseSelectMapper.class);
    }

}

public static class BaseUpdateMapper extends BaseUpdateAction<OtherOpen, BaseUpdateMapper> {
public BaseUpdateMapper(SplicingParam splicingParam) {
super(splicingParam,OtherOpen.class, BaseUpdateMapper.class);
}

    public UpdateCriteria<OtherOpen, BaseUpdateMapper> id() {
    this.fieldName="id";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> institutionId() {
    this.fieldName="institutionId";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> orgName() {
    this.fieldName="orgName";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> spNo() {
    this.fieldName="spNo";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> billType() {
    this.fieldName="billType";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> loggingState() {
    this.fieldName="loggingState";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> currency() {
    this.fieldName="currency";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> kycId() {
    this.fieldName="kycId";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> kycAccountName() {
    this.fieldName="kycAccountName";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> spAccountId() {
    this.fieldName="spAccountId";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> spAccountRemark() {
    this.fieldName="spAccountRemark";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> spAmount() {
    this.fieldName="spAmount";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> spQuantity() {
    this.fieldName="spQuantity";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> acceptor() {
    this.fieldName="acceptor";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> draftDate() {
    this.fieldName="draftDate";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> receiveDate() {
    this.fieldName="receiveDate";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> billDueDate() {
    this.fieldName="billDueDate";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> remark() {
    this.fieldName="remark";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> deleteFlag() {
    this.fieldName="deleteFlag";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> createUserAccount() {
    this.fieldName="createUserAccount";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> createUserName() {
    this.fieldName="createUserName";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> createUserId() {
    this.fieldName="createUserId";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> updateUserId() {
    this.fieldName="updateUserId";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> version() {
    this.fieldName="version";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> createTime() {
    this.fieldName="createTime";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }


    public UpdateCriteria<OtherOpen, BaseUpdateMapper> updateTime() {
    this.fieldName="updateTime";
    return new UpdateCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseUpdateMapper.class);
    }

}

public static class BaseDeletedMapper extends BaseDeletedAction<OtherOpen, BaseDeletedMapper> {
public BaseDeletedMapper(SplicingParam splicingParam) {
super(splicingParam,OtherOpen.class, BaseDeletedMapper.class);
}


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> id() {
    this.fieldName="id";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> institutionId() {
    this.fieldName="institutionId";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> orgName() {
    this.fieldName="orgName";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> spNo() {
    this.fieldName="spNo";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> billType() {
    this.fieldName="billType";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> loggingState() {
    this.fieldName="loggingState";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> currency() {
    this.fieldName="currency";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> kycId() {
    this.fieldName="kycId";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> kycAccountName() {
    this.fieldName="kycAccountName";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> spAccountId() {
    this.fieldName="spAccountId";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> spAccountRemark() {
    this.fieldName="spAccountRemark";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> spAmount() {
    this.fieldName="spAmount";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> spQuantity() {
    this.fieldName="spQuantity";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> acceptor() {
    this.fieldName="acceptor";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> draftDate() {
    this.fieldName="draftDate";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> receiveDate() {
    this.fieldName="receiveDate";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> billDueDate() {
    this.fieldName="billDueDate";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> remark() {
    this.fieldName="remark";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> deleteFlag() {
    this.fieldName="deleteFlag";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> createUserAccount() {
    this.fieldName="createUserAccount";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> createUserName() {
    this.fieldName="createUserName";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> createUserId() {
    this.fieldName="createUserId";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> updateUserId() {
    this.fieldName="updateUserId";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> version() {
    this.fieldName="version";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> createTime() {
    this.fieldName="createTime";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }


    public DeletedCriteria<OtherOpen, BaseDeletedMapper> updateTime() {
    this.fieldName="updateTime";
    return new DeletedCriteria<>(this.splicingParam,this.fieldName,OtherOpen.class, BaseDeletedMapper.class);
    }

}
}
