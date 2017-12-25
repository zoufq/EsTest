package bean;

import com.alibaba.fastjson.JSON;

/**
 * 经营异常
 */
public class CompanyAbnormalInfo {
    private Long id;

    /** 
     * 公司id
     */
    private Long companyId;

    /** 
     * 列入异常名录原因
     */
    private String putReason;

    /** 
     * 列入异常名录日期
     */
    private Long putDate;

    /** 
     * 决定列入异常名录部门(作出决定机关)
     */
    private String putDepartment;

    /** 
     * 移除异常名录原因
     */
    private String removeReason;

    /** 
     * 移除异常名录日期
     */
    private Long removeDate;

    /** 
     * 决定移除异常名利部门
     */
    private String removeDepartment;

    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPutReason() {
        return putReason;
    }

    public void setPutReason(String putReason) {
        this.putReason = putReason;
    }

    public Long getPutDate() {
        return putDate;
    }

    public void setPutDate(Long putDate) {
        this.putDate = putDate;
    }

    public String getPutDepartment() {
        return putDepartment;
    }

    public void setPutDepartment(String putDepartment) {
        this.putDepartment = putDepartment;
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }

    public Long getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Long removeDate) {
        this.removeDate = removeDate;
    }

    public String getRemoveDepartment() {
        return removeDepartment;
    }

    public void setRemoveDepartment(String removeDepartment) {
        this.removeDepartment = removeDepartment;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString(){return JSON.toJSONString(this);}
}