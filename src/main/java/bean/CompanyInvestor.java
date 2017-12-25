package bean;

import com.alibaba.fastjson.JSON;

/**
 *  公司投资信息
 */
public class CompanyInvestor {
    private Long id;

    /** 
     * 公司id
     */
    private Long companyId;

    /** 
     * 股东类型，1代表自然人，2代表非自然人
     */
    private Short investorType;

    /** 
     * 投资人id，根据投资人类型的不同分别对应human_id或者company_id
     */
    private Long investorId;

    /** 
     * 认缴金额
     */
    private String capital;

    /** 
     * 实缴金额
     */
    private String capitalactl;

    /** 
     * 投资金额
     */
    private Double amount;

    /** 
     * 证照/证件类型
     */
    private String certname;

    /** 
     * 证照/证件号码
     */
    private String certno;

    /** 
     * 控股比例
     */
    private String kgbl;

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

    public Short getInvestorType() {
        return investorType;
    }

    public void setInvestorType(Short investorType) {
        this.investorType = investorType;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCapitalactl() {
        return capitalactl;
    }

    public void setCapitalactl(String capitalactl) {
        this.capitalactl = capitalactl;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCertname() {
        return certname;
    }

    public void setCertname(String certname) {
        this.certname = certname;
    }

    public String getCertno() {
        return certno;
    }

    public void setCertno(String certno) {
        this.certno = certno;
    }

    public String getKgbl() {
        return kgbl;
    }

    public void setKgbl(String kgbl) {
        this.kgbl = kgbl;
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