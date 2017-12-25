package xty;

import bean.CompanyInvestor;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/1.
 */
public class CompanyInvestorImport {
    public static void main(String[] args) {
        String file = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] ts = line.split("\001");
                CompanyInvestor model = new CompanyInvestor();
                model.setId(Long.parseLong(ts[0]));
                if(StringUtils.isNotBlank(ts[1])){
                    model.setCompanyId(Long.parseLong(ts[1]));
                }
                if(StringUtils.isNotBlank(ts[2])){
                    model.setInvestorType(Short.parseShort(ts[2]));
                }
                if(StringUtils.isNotBlank(ts[3])){
                    model.setInvestorId(Long.parseLong(ts[3]));
                }
                if(StringUtils.isNotBlank(ts[4])){
                    model.setCapital(ts[4]);
                }
                if(StringUtils.isNotBlank(ts[5])){
                    model.setCapitalactl(ts[5]);
                }

                if(StringUtils.isNotBlank(ts[6])){
                    model.setAmount(Double.parseDouble(ts[6]));
                }
                if(StringUtils.isNotBlank(ts[7])){
                    model.setCertname(ts[7]);
                }
                if(StringUtils.isNotBlank(ts[8])){
                    model.setCertno(ts[8]);
                }
                model.setCreateTime(System.currentTimeMillis());
                if(StringUtils.isNotBlank(ts[10])){
                    model.setKgbl(ts[10]);
                }

                System.out.println(model);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
