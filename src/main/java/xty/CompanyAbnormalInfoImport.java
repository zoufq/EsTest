package xty;

import bean.CompanyAbnormalInfo;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/1.
 */
public class CompanyAbnormalInfoImport {
    public static void main(String[] args) {
        String file = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));

            String line = null;
            while ((line = br.readLine()) != null) {
                String[] ts = line.split("\001");
                CompanyAbnormalInfo model = new CompanyAbnormalInfo();
                model.setId(Long.parseLong(ts[0]));
                if(StringUtils.isNotBlank(ts[1])){
                    model.setCompanyId(Long.parseLong(ts[1]));
                }
                if(StringUtils.isNotBlank(ts[2])){
                    model.setPutReason(ts[2]);
                }
                if(StringUtils.isNotBlank(ts[3])){
                    model.setPutDate(Long.parseLong(ts[3]));
                }
                if(StringUtils.isNotBlank(ts[4])){
                    model.setPutDepartment(ts[4]);
                }
                if(StringUtils.isNotBlank(ts[5])){
                    model.setRemoveReason(ts[5]);
                }

                if(StringUtils.isNotBlank(ts[6])){
                    model.setRemoveDate(Long.parseLong(ts[6]));
                }
                if(StringUtils.isNotBlank(ts[7])){
                    model.setRemoveDepartment(ts[7]);
                }

                model.setCreateTime(System.currentTimeMillis());

                System.out.println(model);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
